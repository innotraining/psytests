package com.congnitive.test.mmpitest.domainObjects;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
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
