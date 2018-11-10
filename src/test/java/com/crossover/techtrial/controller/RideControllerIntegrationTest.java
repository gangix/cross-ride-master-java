/**
 * 
 */
package com.crossover.techtrial.controller;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.service.PersonService;
import com.crossover.techtrial.service.RideService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author kshah
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RideControllerIntegrationTest {

	@Mock
	private RideController rideController;

	@Autowired
	private TestRestTemplate template;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	PersonService personService;
	@Autowired
	RideService rideService;

	@Test
	public void testRideShareCreateSuccess() throws Exception {
		Person person = new Person();
		person.setEmail("olcay.bilir@gmail.com");
		person.setName("Olcay");
		person.setRegistrationNumber("332424");
		Ride ride = new Ride();
		ride.setDistance(3L);
		ride.setDriver(person);
		ride.setStartTime(LocalDateTime.now().minusDays(46));
		ride.setEndTime(LocalDateTime.now().minusDays(45));

		String jSon = objectMapper.writeValueAsString(ride);
		HttpEntity<Object> rideEntity = getHttpEntity(jSon);
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", rideEntity, Ride.class);
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void testRideShareFailedOverlap() throws Exception {
		Person person = new Person();
		person.setEmail("olcay.bilir@gmail.com");
		person.setName("Olcay");
		person.setRegistrationNumber("332424");
		Ride ride = new Ride();
		ride.setDistance(3L);
		ride.setDriver(person);
		ride.setStartTime(LocalDateTime.now().minusDays(46));
		ride.setEndTime(LocalDateTime.now().minusDays(45));

		String jSon = objectMapper.writeValueAsString(ride);
		HttpEntity<Object> rideEntity = getHttpEntity(jSon);
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", rideEntity, Ride.class);

		Assert.assertEquals(400, response.getStatusCode().value());
	}

	@Test
	public void testRideShareFailedWrongEndTime() throws Exception {
		Person person = new Person();
		person.setEmail("olcay.bilir@gmail.com");
		person.setName("Olcay");
		person.setRegistrationNumber("332424");
		Ride ride = new Ride();
		ride.setDistance(3L);
		ride.setDriver(person);
		ride.setStartTime(LocalDateTime.now().minusDays(8));
		ride.setEndTime(LocalDateTime.now().minusDays(8));

		String jSon = objectMapper.writeValueAsString(ride);
		HttpEntity<Object> rideEntity = getHttpEntity(jSon);
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", rideEntity, Ride.class);

		Assert.assertEquals(400, response.getStatusCode().value());
	}

	@Test
	public void testRideShareFailedPersonNotRegistered() throws Exception {
		Person person = new Person();
		person.setEmail("olcay.bilir@gmail.com");
		person.setName("Olcay");
		person.setRegistrationNumber("3324246");
		Ride ride = new Ride();
		ride.setDistance(3L);
		ride.setDriver(person);
		ride.setStartTime(LocalDateTime.now().minusDays(8));
		ride.setEndTime(LocalDateTime.now().minusDays(5));

		String jSon = objectMapper.writeValueAsString(ride);
		HttpEntity<Object> rideEntity = getHttpEntity(jSon);
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", rideEntity, Ride.class);
		Assert.assertEquals(400, response.getStatusCode().value());
	}

	@Test
	public void testRideShareFailedEndTimeGreaterThanCurrent() throws Exception {
		Person person = new Person();
		person.setEmail("olcay.bilir@gmail.com");
		person.setName("Olcay");
		person.setRegistrationNumber("3324246");
		Ride ride = new Ride();
		ride.setDistance(3L);
		ride.setDriver(person);
		ride.setStartTime(LocalDateTime.now().minusDays(4));
		ride.setEndTime(LocalDateTime.now().plusDays(5));

		String jSon = objectMapper.writeValueAsString(ride);
		HttpEntity<Object> rideEntity = getHttpEntity(jSon);
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", rideEntity, Ride.class);

		Assert.assertEquals(400, response.getStatusCode().value());
	}

	@Test
	public void testGetTopRatedDriversSuccess() throws Exception {
		String startDate = "2016-09-28T10:00:05";
		String endDate = "2020-10-02T10:00:05";
		Long max = 4L;
		ResponseEntity<TopDriverDTO[]> response = template.getForEntity(
				"/api/top-rides?" + "startTime=" + startDate + "&" + "endTime=" + endDate+ "&" + "max=" + max, TopDriverDTO[].class);

		Assert.assertEquals(200, response.getStatusCode().value());
	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}

}
