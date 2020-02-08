package com.springboot.restservices.rest_services.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.rest_services.entities.Order;
import com.springboot.restservices.rest_services.entities.User;
import com.springboot.restservices.rest_services.exceptions.OrderNotFoundException;
import com.springboot.restservices.rest_services.exceptions.UserNotFoundException;
import com.springboot.restservices.rest_services.repositories.OrderRepository;
import com.springboot.restservices.rest_services.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;

	// get all orders for a user
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found!");
		return userOptional.get().getOrders();
	}

	// Create order
	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable() Long userId, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found!");
		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
	}

	// Get order by order id
	@GetMapping("/{userId}/orders/{orderId}")
	public Order getOrderById(@PathVariable() Long userId, @PathVariable Long orderId)
			throws UserNotFoundException, OrderNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found!");
		User user = userOptional.get();
		Optional<Order> orderOptional = orderRepository.findById(orderId);
		if (!orderOptional.isPresent())
			throw new OrderNotFoundException("Order not found!");
		Order order = orderOptional.get();
		if (user.getId() != order.getUser().getId())
			throw new OrderNotFoundException("Order not found under the required user");
		return orderOptional.get();
	}
}
