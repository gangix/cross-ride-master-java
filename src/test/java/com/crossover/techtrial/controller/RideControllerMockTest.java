/**
 * 
 */
package com.crossover.techtrial.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.service.PersonService;
import com.crossover.techtrial.service.RideService;
import com.crossover.techtrial.service.TopDriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

/**
 * @author olcay
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RideController.class)
public class RideControllerMockTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	PersonService personobjectMapperService;
	@MockBean
	TopDriverService topDriveService;
	@MockBean
	RideService rideService;

	@Before
	public void setObjectMapper() {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
		objectMapper.registerModule(javaTimeModule);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	@Test
	public void testRideShareCreateMock() throws Exception {
		Person person = new Person();
		person.setEmail("olcay.bilir@gmail.com");
		person.setName("Olcay");
		person.setRegistrationNumber("332424");
		Ride ride = new Ride();
		ride.setDistance(3L);
		ride.setDriver(person);
		ride.setStartTime(LocalDateTime.now().minusDays(40));
		ride.setEndTime(LocalDateTime.now().minusDays(39));
		String jSon = objectMapper.writeValueAsString(ride);
		when(rideService.save(ride)).thenReturn(ride);

		RequestBuilder request = MockMvcRequestBuilders.post("/api/ride").content(jSon)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.distance", Matchers.is(3)));
	}
	@Test
	public void getRideByIdMock() throws Exception {
		Person person = new Person();
		person.setEmail("olcay.bilir@gmail.com");
		person.setName("Olcay");
		person.setRegistrationNumber("332424");
		Ride ride = new Ride();
		ride.setDistance(3L);
		ride.setDriver(person);
		ride.setStartTime(LocalDateTime.now().minusDays(40));
		ride.setEndTime(LocalDateTime.now().minusDays(39));
		when(rideService.findById(3L)).thenReturn(ride);

		RequestBuilder request = MockMvcRequestBuilders.get("/api/ride/3").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("$.distance", Matchers.is(3)));
	}

}
