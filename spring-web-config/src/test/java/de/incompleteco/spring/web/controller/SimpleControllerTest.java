package de.incompleteco.spring.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import de.incompleteco.spring.web.WebAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={WebAppConfig.class})
public class SimpleControllerTest {

	@Resource
	private WebApplicationContext context;
	
	@Test
	public void testGet() throws Exception {
		//setup
		MockMvc mvc = webAppContextSetup(context).build();
		//execute
		mvc.perform(get("/simple")).andExpect(status().isOk()).andExpect(content().string("hello world"));
	}

}
