package org.sid.security;

public enum Role {

	USER("User"), ADMIN("Admin");

	private final String name;

	private Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
