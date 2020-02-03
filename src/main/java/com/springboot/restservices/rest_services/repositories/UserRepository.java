package com.springboot.restservices.rest_services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restservices.rest_services.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);
}
