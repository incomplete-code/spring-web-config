package de.incompleteco.spring.web.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.incompleteco.spring.domain.SimpleEntity;
import de.incompleteco.spring.domain.SimpleEntityRepository;
import de.incompleteco.spring.web.WebAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={WebAppConfig.class})
public class SimpleControllerTest {

	@Resource
	private WebApplicationContext context;
	
	@Resource
	private SimpleEntityRepository repository;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void before() throws Exception {
		objectMapper = new ObjectMapper();
		//add a row
		SimpleEntity entity = new SimpleEntity();
		entity.setStuff("hello world");
		//save
		repository.save(entity);
	}
	
	@After
	public void after() throws Exception {
		repository.deleteAll();
	}
	
	@Test
	public void testGet() throws Exception {
		//setup
		MockMvc mvc = webAppContextSetup(context).build();
		//execute
		MvcResult result = mvc.perform(get("/simple").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		//return and parse
		SimpleEntity[] entities = objectMapper.readValue(result.getResponse().getContentAsByteArray(), SimpleEntity[].class);
		//check length
		assertTrue(entities.length >= 1);
	}
	
	@Test
	public void testPut() throws Exception {
		//setup
		MockMvc mvc = webAppContextSetup(context).build();
		//create an entity
		SimpleEntity entity = new SimpleEntity();
		entity.setStuff("put object");
		//convert to json
		byte[] json = objectMapper.writeValueAsBytes(entity);
		//execute
		MvcResult result = mvc.perform(put("/simple").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk())
				.andReturn();
		//return and parse
		SimpleEntity responseEntity = objectMapper.readValue(result.getResponse().getContentAsByteArray(), SimpleEntity.class);
		//check length
		assertTrue(responseEntity.getId() > 0);
	}	

}
