/**
 * 
 */
package com.crossover.techtrial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.exceptions.PersonIsAlreadyRegisteredException;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.repositories.PersonRepository;

/**
 * @author crossover
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crossover.techtrial.service.PersonService#getAll()
	 */
	@Override
	public List<Person> getAll() {
		List<Person> personList = new ArrayList<>();
		personRepository.findAll().forEach(personList::add);
		return personList;

	}

	public Person save(Person p) throws Exception {
		validatePerson(p);
		return personRepository.save(p);
	}

	private void validatePerson(Person p) throws Exception {
		Person personRegister = getByRegistrationNumber(p.getRegistrationNumber());
		if (personRegister != null) {
			throw new PersonIsAlreadyRegisteredException();
		}
		Person personEmail = getByEmail(p.getEmail());
		if (personEmail != null) {
			throw new PersonIsAlreadyRegisteredException();
		}

	}

	@Override
	public Person findById(Long personId) {
		Optional<Person> dbPerson = personRepository.findById(personId);
		return dbPerson.orElse(null);
	}

	@Override
	public Person getByRegistrationNumber(String registrationNumber) {
		Optional<Person> dbPerson = personRepository.getByRegistrationNumber(registrationNumber);
		return dbPerson.orElse(null);
	}

	@Override
	public Person getByEmail(String email) {
		Optional<Person> dbPerson = personRepository.getByEmail(email);
		return dbPerson.orElse(null);
	}

}
