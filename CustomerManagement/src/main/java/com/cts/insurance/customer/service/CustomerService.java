package com.cts.insurance.customer.service;

import java.util.List;
import java.util.Optional;

import com.cts.insurance.customer.customerDTO.PolicyDTO;
import com.cts.insurance.customer.exception.CustomerNotFoundException;
import com.cts.insurance.customer.model.Customer;

public interface CustomerService {
	Customer createCustomer(Customer customer);

	List<Customer> getAllCustomers();

	Customer getCustomerById(Long id) throws CustomerNotFoundException;

	Customer updateCustomer(Long id, Customer customerDetails);

	void deleteCustomer(Long id);

	List<Customer> searchCustomersByName(String name);

	Optional<Customer> getCustomerByEmail(String email);

	List<PolicyDTO> getCustomerPolicies(Long customerId);
}