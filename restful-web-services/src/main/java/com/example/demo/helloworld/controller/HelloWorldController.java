package com.example.demo.helloworld.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helloworld.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource msgSrc;
	
//	@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping(path = "/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/helloworldbean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "/helloworld/pathvariable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	@GetMapping(path = "/helloworldinternationalized")
	public String helloWorldInternationalized(
//			@RequestHeader(name = "Accept-Language", required = false) Locale locale
			) {
		// English : en : Hello World
		// Dutch (NetherLands) : nl : Goede Morgen
		// French : fr : Bonjour
		return msgSrc.getMessage("good.morning.message", null, "Not found for your locale", 
				//locale
				LocaleContextHolder.getLocale());
//		return "Hello World";
	}

}
