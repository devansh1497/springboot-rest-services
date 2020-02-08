package com.springboot.restservices.rest_services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restservices.rest_services.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
