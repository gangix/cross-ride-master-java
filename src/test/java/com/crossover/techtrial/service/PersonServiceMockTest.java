package com.crossover.techtrial.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.repositories.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceMockTest {
	@InjectMocks
	PersonServiceImpl personService;
	@Mock
	PersonRepository personRepository;

	@Test
	public void getAllTest() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("ayten", "eytoy.bilir@gmail.com", "549109"));
		personList.add(new Person("Olcay", "olcay.bilir@gmail.com", "332430"));
		when(personRepository.findAll()).thenReturn(personList);

		List<Person> all = personService.getAll();
		Assert.assertNotNull(all);
		Assert.assertEquals(2, all.size());
	}

	@Test
	public void save() throws Exception {
		Person person = new Person("Olcay", "olcay.bilir@gmail.com", "332430");
		when(personRepository.save(person)).thenReturn(person);

		Person p1 = personService.save(person);
		Assert.assertNotNull(p1);
		Assert.assertEquals(p1.getEmail(), "olcay.bilir@gmail.com");
		Assert.assertEquals(p1.getName(), "Olcay");
		Assert.assertEquals(p1.getRegistrationNumber(), "332430");
	}

	@Test
	public void findById() {
		Person person = new Person("Olcay", "olcay.bilir@gmail.com", "332430");
		when(personRepository.findById(3L)).thenReturn(Optional.of(person));

		Person p1 = personService.findById(3L);
		Assert.assertNotNull(p1);
		Assert.assertEquals(p1.getEmail(), "olcay.bilir@gmail.com");
		Assert.assertEquals(p1.getName(), "Olcay");
		Assert.assertEquals(p1.getRegistrationNumber(), "332430");
	}

	@Test
	public void getByRegistrationNumber() {
		Person person = new Person("Olcay", "olcay.bilir@gmail.com", "332430");
		when(personRepository.getByRegistrationNumber("332430")).thenReturn(Optional.of(person));

		Person p1 = personService.getByRegistrationNumber("332430");
		Assert.assertNotNull(p1);
		Assert.assertEquals(p1.getEmail(), "olcay.bilir@gmail.com");
		Assert.assertEquals(p1.getName(), "Olcay");
		Assert.assertEquals(p1.getRegistrationNumber(), "332430");
	}

	@Test
	public void getByEmail() {
		Person person = new Person("Olcay", "olcay.bilir@gmail.com", "332430");
		when(personRepository.getByEmail("olcay.bilir@gmail.com")).thenReturn(Optional.of(person));

		Person p1 = personService.getByEmail("olcay.bilir@gmail.com");
		Assert.assertNotNull(p1);
		Assert.assertEquals(p1.getEmail(), "olcay.bilir@gmail.com");
		Assert.assertEquals(p1.getName(), "Olcay");
		Assert.assertEquals(p1.getRegistrationNumber(), "332430");
	}
}
