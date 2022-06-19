package com.example.demo.filtering.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.filtering.bean.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	//StaticFiltering
//	@GetMapping(path = "/filtering")
//	public SomeBean retrieveSomeBean() {
//		return new SomeBean("Value1", "Value2", "Value3");
//	}
//
//	@GetMapping(path = "/filtering-list")
//	public List<SomeBean> retrieveListOfSomeBean() {
//		return Arrays.asList(new SomeBean("Value12", "Value23", "Value34"),
//				new SomeBean("Value11", "Value22", "Value33"));
//	}
	
	
	//DynamicFiltering
	@GetMapping(path = "/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean somebean = new SomeBean("Value1", "Value2", "Value3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value2");
		FilterProvider fp = new SimpleFilterProvider().addFilter("SomeBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(somebean);
		mapping.setFilters(fp);
		return mapping;
	}

	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> lst = Arrays.asList(new SomeBean("Value12", "Value23", "Value34"),
				new SomeBean("Value11", "Value22", "Value33"));
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value3");
		FilterProvider fp = new SimpleFilterProvider().addFilter("SomeBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(lst);
		mapping.setFilters(fp);
		return mapping;
	}
}
