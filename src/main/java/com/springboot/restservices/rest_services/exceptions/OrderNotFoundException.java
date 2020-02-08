package com.springboot.restservices.rest_services.exceptions;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = -2630121737258155747L;

	public OrderNotFoundException(String message) {
		super(message);
	}

}
