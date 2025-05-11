package com.cts.insurance.customer.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cts.insurance.customer.customerDTO.PolicyDTO;
import com.cts.insurance.customer.feign.PolicyClient;
import com.cts.insurance.customer.model.Customer;
import com.cts.insurance.customer.repository.CustomerRepository;

import java.util.List;

import java.util.Optional;

@Service

@Slf4j

public class CustomerServiceImpl implements CustomerService {

	@Autowired

	private CustomerRepository customerRepository;

	
	  @Autowired
	  
	  private PolicyClient policyClient;
	 

	@Override

	public Customer createCustomer(Customer customer) {

		log.info("Creating new customer: {}", customer.getName());

		return customerRepository.save(customer);

	}

	@Override

	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();

	}

	@Override

	public Optional<Customer> getCustomerById(Long id) {

		return customerRepository.findById(id);

	}

	@Override

	public Customer updateCustomer(Long id, Customer customerDetails) {

		Customer customer = customerRepository.findById(id)

				.orElseThrow(() -> new RuntimeException("Customer not found"));

		customer.setName(customerDetails.getName());

		customer.setEmail(customerDetails.getEmail());

		customer.setPhoneNumber(customerDetails.getPhoneNumber());

		customer.setAddress(customerDetails.getAddress());

		log.info("Updating customer with ID: {}", id);

		return customerRepository.save(customer);

	}

	@Override

	public void deleteCustomer(Long id) {

		log.warn("Deleting customer with ID: {}", id);

		customerRepository.deleteById(id);

	}

	@Override

	public List<Customer> searchCustomersByName(String name) {

		return customerRepository.findByNameContainingIgnoreCase(name);

	}

	@Override

	public Optional<Customer> getCustomerByEmail(String email) {

		return customerRepository.findByEmail(email);

	}

	@Override

	public List<PolicyDTO> getCustomerPolicies(Long customerId) {

		return policyClient.getPoliciesByCustomerId(customerId);
		//return List.of();

	}

}
