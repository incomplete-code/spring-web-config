package de.incompleteco.spring.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import de.incompleteco.spring.RepositoryConfig;

@Configuration
@EnableWebMvc
@Import(RepositoryConfig.class)
@ComponentScan("de.incompleteco.spring.web")
public class WebAppConfig {
	
}
