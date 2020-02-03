package com.springboot.restservices.rest_services.exceptions;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
	
}
