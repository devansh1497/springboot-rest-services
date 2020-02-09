package com.springboot.restservices.rest_services.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.restservices.rest_services.entities.User;
import com.springboot.restservices.rest_services.exceptions.UserExistsException;
import com.springboot.restservices.rest_services.exceptions.UserNameNotFoundException;
import com.springboot.restservices.rest_services.exceptions.UserNotFoundException;
import com.springboot.restservices.rest_services.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Validated
@RequestMapping("/users")
@Api(tags = "User Manangement RESTful Services", value = "UserController", description = "Controller for User Manegement Servive")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	@ApiOperation(value = " Retrieve list of users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	@ApiOperation(value = "Creates a new user")
	public ResponseEntity<Void> createUser(@ApiParam("User information for a new user to be created") @Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			return user;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	@GetMapping("/username/{userName}")
	public User findUserByUserName(@PathVariable("userName") String userName) throws UserNameNotFoundException {
		User user = userService.findUserByUserName(userName);
		if (user == null)
			throw new UserNameNotFoundException("Username: '" + userName + "' not found!");
		return user;
	}
}
