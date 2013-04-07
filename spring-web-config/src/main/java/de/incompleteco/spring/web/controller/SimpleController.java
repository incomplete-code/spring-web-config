package de.incompleteco.spring.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.incompleteco.spring.domain.SimpleEntity;
import de.incompleteco.spring.domain.SimpleEntityRepository;

@Controller
@RequestMapping("/simple")
public class SimpleController {

	@Resource
	private SimpleEntityRepository repository;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<SimpleEntity> get() throws Exception {
		return repository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public @ResponseBody SimpleEntity put(@RequestBody SimpleEntity entity) throws Exception {
		//persist the entity
		repository.save(entity);
		//return it
		return entity;
	}
	
}
