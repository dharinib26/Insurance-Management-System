package com.cts.insurance.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.insurance.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByNameContainingIgnoreCase(String name);

	Optional<Customer> findByEmail(String email);
}