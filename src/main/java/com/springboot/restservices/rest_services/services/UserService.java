package com.springboot.restservices.rest_services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.restservices.rest_services.entities.User;
import com.springboot.restservices.rest_services.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long userId) {
		Optional<User> user= userRepository.findById(userId);
		return user;
	}
	
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
