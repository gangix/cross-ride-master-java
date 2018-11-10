/**
 * 
 */
package com.crossover.techtrial.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.exceptions.EndTimeInAppropriateException;
import com.crossover.techtrial.exceptions.EndTimeIsGreaterThanCurrentException;
import com.crossover.techtrial.exceptions.PersonIsNotRegisteredException;
import com.crossover.techtrial.exceptions.TimeOverlappingException;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.RideRepository;

/**
 * @author crossover
 *
 */
@Service
public class RideServiceImpl implements RideService {

	private final RideRepository rideRepository;
	private final PersonService personService;

	@Autowired
	public RideServiceImpl(RideRepository rideRepository, PersonService personService) {
		this.rideRepository = rideRepository;
		this.personService = personService;
	}

	public Ride save(Ride ride) throws Exception {
		validateRegistration(ride);
		Ride savedRide = rideRepository.save(ride);

		return savedRide;
	}

	public Ride findById(Long rideId) {
		Optional<Ride> optionalRide = rideRepository.findById(rideId);
		return optionalRide.orElse(null);
	}

	public void validateRegistration(Ride ride) throws Exception {
		isEndTimeAppropriate(ride);
		isPersonRegistered(ride);
		isStartEndTimeAppropriate(ride);
		isStartEndTimeOverlapping(ride);
	}

	private void isEndTimeAppropriate(Ride ride) throws Exception {
		if (ride.getEndTime().compareTo(LocalDateTime.now()) > 0) {
			throw new EndTimeIsGreaterThanCurrentException();
		}

	}

	private void isStartEndTimeOverlapping(Ride ride) throws Exception {
		long personId = getPersonId(ride);
		List<Ride> rideList = rideRepository.getTimedRide(ride.getStartTime(), ride.getEndTime(), personId);
		if (rideList.size() > 0) {
			throw new TimeOverlappingException();
		}

	}

	private Long getPersonId(Ride ride) {
		return ride.getDriver() != null ? ride.getDriver().getId() : ride.getRider().getId();
	}

	private void isStartEndTimeAppropriate(Ride ride) throws Exception {
		if (!(ride.getEndTime().compareTo(ride.getStartTime()) > 0)) {
			throw new EndTimeInAppropriateException();
		}

	}

	private void isPersonRegistered(Ride ride) throws Exception {
		if (ride.getDriver() == null && ride.getRider() == null) {
			throw new Exception();
		}
		Person driver = ride.getDriver();
		if (driver != null && driver.getRegistrationNumber() != null) {
			Person foundPerson = personService.getByRegistrationNumber(driver.getRegistrationNumber());
			if (foundPerson == null) {
				throw new PersonIsNotRegisteredException();
			}
			ride.setDriver(foundPerson);
		}
		Person rider = ride.getRider();
		if (rider != null && rider.getRegistrationNumber() != null) {
			Person foundPerson = personService.getByRegistrationNumber(rider.getRegistrationNumber());
			if (foundPerson == null) {
				throw new PersonIsNotRegisteredException();
			}
			ride.setRider(foundPerson);
		}
	}

}
