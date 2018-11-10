package com.crossover.techtrial.service;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.RideRepository;

@RunWith(MockitoJUnitRunner.class)
public class RideServiceMockTest {
	@InjectMocks
	RideServiceImpl rideService;
	@Mock
	RideRepository rideRepository;
	
	@Test
	public void save() throws Exception {
		Person person = new Person();
		person.setEmail("olcay.bilir@gmail.com");
		person.setName("Olcay");
		person.setRegistrationNumber("332424");
		Ride ride = new Ride();
		ride.setDistance(3L);
		ride.setDriver(person);
		ride.setStartTime(LocalDateTime.now().minusDays(100));
		ride.setEndTime(LocalDateTime.now().minusDays(101));
		when(rideRepository.save(ride)).thenReturn(ride);

		Ride r = rideService.save(ride);
		Assert.assertNotNull(r);
		Assert.assertEquals(r.getDistance(), Long.valueOf(3));
	}

	@Test
	public void findById() {
		Person person = new Person();
		person.setEmail("olcay.bilir@gmail.com");
		person.setName("Olcay");
		person.setRegistrationNumber("332424");
		Ride ride = new Ride();
		ride.setDistance(3L);
		ride.setDriver(person);
		ride.setStartTime(LocalDateTime.now().minusDays(40));
		ride.setEndTime(LocalDateTime.now().minusDays(39));
		when(rideRepository.findById(1L)).thenReturn(Optional.of(ride));

		Ride r = rideService.findById(1L);
		Assert.assertNotNull(r);
		Assert.assertEquals(r.getDistance(), Long.valueOf(3));
	}

}
