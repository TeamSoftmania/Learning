package com.example.demo.versioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.versioning.bean.Name;
import com.example.demo.versioning.bean.PersonV1;
import com.example.demo.versioning.bean.PersonV2;

@RestController
public class PersonVersioningController {

	//URI Approach
	@GetMapping(path = "/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Samridhh Chaudhary");
	}

	@GetMapping(path = "/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Samridhh", "Chaudhary"));
	}
	
	//Request Parameter Approach
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Samridhh Chaudhary");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Samridhh", "Chaudhary"));
	}
	
	//Request Header Approach
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Samridhh Chaudhary");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Samridhh", "Chaudhary"));
	}
	
	//Produces Approach
	@GetMapping(value = "/person/produces", produces = "application/test.api.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Samridhh Chaudhary");
	}

	@GetMapping(value = "/person/produces", produces = "application/test.api.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Samridhh", "Chaudhary"));
	}
}
