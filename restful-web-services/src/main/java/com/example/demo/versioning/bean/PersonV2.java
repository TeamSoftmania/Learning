package com.example.demo.versioning.bean;

public class PersonV2 {
	private Name name;

	/**
	 * @param name
	 */
	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}
	
}
