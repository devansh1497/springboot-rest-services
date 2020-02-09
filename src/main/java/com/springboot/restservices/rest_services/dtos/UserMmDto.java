package com.springboot.restservices.rest_services.dtos;

import java.util.List;

import com.springboot.restservices.rest_services.entities.Order;

public class UserMmDto {

	private Long userId;
	private String userName;
	private String firstName;
	private List<Order> orders;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
