package com.springboot.restservices.rest_services.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.restservices.rest_services.dtos.UserDtoV1;
import com.springboot.restservices.rest_services.dtos.UserDtoV2;
import com.springboot.restservices.rest_services.dtos.UserMmDto;
import com.springboot.restservices.rest_services.entities.User;
import com.springboot.restservices.rest_services.exceptions.UserNotFoundException;
import com.springboot.restservices.rest_services.services.UserService;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserUriVersioningController {

	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;

	//URI based versioning 1 -> V1
	@GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
	public UserDtoV1 getUserById1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found!");
		User user = userOptional.get();

		UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
		return userDtoV1;
	}
	
	//URI based versioning 2 -> V2
	@GetMapping("/v2.0/{id}")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found!");
		User user = userOptional.get();

		UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
		return userDtoV2;
	}
}
