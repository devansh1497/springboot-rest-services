package com.springboot.restservices.rest_services.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.rest_services.entities.Order;
import com.springboot.restservices.rest_services.entities.User;
import com.springboot.restservices.rest_services.exceptions.UserNotFoundException;
import com.springboot.restservices.rest_services.repositories.OrderRepository;
import com.springboot.restservices.rest_services.repositories.UserRepository;
import com.springboot.restservices.rest_services.services.UserService;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userId}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found!");
		List<Order> allOrders = userOptional.get().getOrders();
		CollectionModel<Order> model = new CollectionModel<Order>(allOrders);
		return model;
	}
}
