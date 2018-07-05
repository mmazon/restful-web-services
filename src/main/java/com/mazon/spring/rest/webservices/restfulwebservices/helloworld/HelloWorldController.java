package com.mazon.spring.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path="/hello-world")
	public String heloWorld(){
		return "Hello World";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean heloWorldBean(){
		return new HelloWorldBean("Hello World Bean");
	}
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean heloWorldPathVariable(@PathVariable String name){
		return new HelloWorldBean(String.format("Hello World Bean, %s", name));
	}
	
	@GetMapping(path="/hello-world-internationalized")
	public String heloWorldInternationalized(){
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
