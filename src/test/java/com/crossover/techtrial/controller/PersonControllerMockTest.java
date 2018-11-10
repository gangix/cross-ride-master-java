/**
 * 
 */
package com.crossover.techtrial.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
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
import com.crossover.techtrial.service.PersonService;

/**
 * @author kshah
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerMockTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	PersonService personService;
	@Test
	public void testPersonGetAllMock() throws Exception {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("Olcay", "olcay.bilir@gmail.com", "332430"));
		personList.add(new Person("Ceren", "ceren.tutku@gmail.com", "332439"));
		when(personService.getAll()).thenReturn(personList);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/api/person").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
								.andExpect(jsonPath("$.size()",Matchers.is(2)));
	}
	@Test
	public void testPersonGetByIdMock() throws Exception {
		when(personService.findById(3L)).thenReturn(new Person("Olcay", "olcay.bilir@gmail.com", "332430"));

		RequestBuilder request = MockMvcRequestBuilders.get("/api/person/3").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Matchers.is("Olcay")))
				.andExpect(jsonPath("$.email", Matchers.is("olcay.bilir@gmail.com")))
				.andExpect(jsonPath("$.registrationNumber", Matchers.is("332430")));
	}

}
