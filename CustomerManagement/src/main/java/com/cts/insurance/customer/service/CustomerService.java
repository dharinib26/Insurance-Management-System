package com.cts.insurance.customer.service;

import java.util.List;
import java.util.Optional;

import com.cts.insurance.customer.customerDTO.PolicyDTO;
import com.cts.insurance.customer.exception.CustomerNotFoundException;
import com.cts.insurance.customer.model.Customer;

//CustomerService interface provides methods for managing customers
public interface CustomerService {
	Customer createCustomer(Customer customer);

	List<Customer> getAllCustomers();

	Customer getCustomerById(Long id) throws CustomerNotFoundException;

	Customer updateCustomer(Long id, Customer customerDetails);

	void deleteCustomer(Long id);

	List<PolicyDTO> getCustomerPolicies(Long customerId);
	Customer assignPolicyToCustomer(Long customerId, Long policyId);
	 
	List<Customer> getCustomersByPolicyId(Long policyId) throws CustomerNotFoundException;
}