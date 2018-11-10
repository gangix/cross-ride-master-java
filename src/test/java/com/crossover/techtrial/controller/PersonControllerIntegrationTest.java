/**
 * 
 */
package com.crossover.techtrial.controller;

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

import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author olcay
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	@Autowired
	ObjectMapper objectMapper;

	@Mock
	PersonService personService;

	@Test
	public void testPersonSave() throws Exception {
		Person person = new Person();
		person.setEmail("cerentutku.bilir@gmail.com");
		person.setName("ayten");
		person.setRegistrationNumber("549158");

		String jSon = objectMapper.writeValueAsString(person);
		HttpEntity<Object> rideEntity = getHttpEntity(jSon);
		ResponseEntity<Person> response = template.postForEntity("/api/person", rideEntity, Person.class);
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(200, response.getStatusCode().value());
		Person personGet = personService.getByEmail(response.getBody().getEmail());
		Assert.assertNotNull(personGet.getId());
	}

	@Test
	public void testPersonGetById() throws Exception {
		Long id = 8L;
		ResponseEntity<Person> response = template.getForEntity("/api/person/{person-id}", Person.class, id);
		Assert.assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void testPersonGetAll() throws Exception {
		ResponseEntity<Person[]> response = template.getForEntity("/api/person", Person[].class);
		Assert.assertEquals(200, response.getStatusCode().value());
	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}

}
