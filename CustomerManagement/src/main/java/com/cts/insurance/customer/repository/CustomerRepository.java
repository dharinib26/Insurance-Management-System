package com.cts.insurance.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.insurance.customer.model.Customer;

import java.util.List;
import java.util.Optional;

/*
 * CustomerRepository is a JPA repository interface for managing Customer entities.
 * It provides methods for performing CRUD operations on Customer data.
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByAssignedPolicyId(Long policyId);
}