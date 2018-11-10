/**
 * 
 */
package com.crossover.techtrial.service;

import java.util.List;

import com.crossover.techtrial.model.Person;

/**
 * PersonService interface for Persons.
 * 
 * @author cossover
 *
 */
public interface PersonService {
	public List<Person> getAll();

	public Person save(Person p) throws Exception;

	public Person findById(Long personId);

	public Person getByRegistrationNumber(String registrationNumber);

	public Person getByEmail(String email);

}
