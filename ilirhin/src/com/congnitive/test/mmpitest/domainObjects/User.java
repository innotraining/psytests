package com.congnitive.test.mmpitest.domainObjects;

public class User {
	private String name;
	private boolean gender;

	public User(String name, boolean gender) {
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return true if man, false else
	 */
	public boolean getGender() {
		return gender;
	}

	public String toString() {
		return name;
	}
}
