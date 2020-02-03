package com.springboot.restservices.rest_services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.restservices.rest_services.entities.User;
import com.springboot.restservices.rest_services.exceptions.UserExistsException;
import com.springboot.restservices.rest_services.exceptions.UserNotFoundException;
import com.springboot.restservices.rest_services.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUserName(user.getUserName());
		if (existingUser != null)
			throw new UserExistsException("User already exists!");
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			throw new UserNotFoundException("User not found!");
		return user;
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent())
			throw new UserNotFoundException("User not found!Check the user id...");

		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found!Check the user id...");
		userRepository.deleteById(id);
	}

	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
