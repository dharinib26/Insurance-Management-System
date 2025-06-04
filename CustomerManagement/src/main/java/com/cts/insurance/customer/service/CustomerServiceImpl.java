package com.cts.insurance.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.insurance.customer.customerDTO.PolicyDTO;
import com.cts.insurance.customer.exception.CustomerNotFoundException;
import com.cts.insurance.customer.exception.PolicyNotFoundException;
import com.cts.insurance.customer.feign.PolicyClient;
import com.cts.insurance.customer.model.Customer;
import com.cts.insurance.customer.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service

@Slf4j

public class CustomerServiceImpl implements CustomerService {

	@Autowired

	private CustomerRepository customerRepository;

	@Autowired

	private PolicyClient policyClient;

	@Override
	// Creates a new customer
	public Customer createCustomer(Customer customer) {

		log.info("Creating new customer: {}", customer.getName());

		return customerRepository.save(customer);

	}

	@Override
	// Retrieves all customers
	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();

	}

	@Override
	// Retrieves a customer by its ID
	public Customer getCustomerById(Long id) throws CustomerNotFoundException {

		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new CustomerNotFoundException("Customer not found with given id.");
	}

	@Override
	// Updates an existing customer
	public Customer updateCustomer(Long id, Customer customerDetails) {

		Customer customer = customerRepository.findById(id)

				.orElseThrow(() -> new RuntimeException("Customer not found"));

		customer.setName(customerDetails.getName());

		customer.setEmail(customerDetails.getEmail());

		customer.setPhoneNumber(customerDetails.getPhoneNumber());

		customer.setAddress(customerDetails.getAddress());
		
		customer.setAssignedPolicyId(customerDetails.getAssignedPolicyId());

		log.info("Updating customer with ID: {}", id);

		return customerRepository.save(customer);

	}

	@Override
	// Deletes a customer by its ID
	public void deleteCustomer(Long id) {

		log.warn("Deleting customer with ID: {}", id);

		customerRepository.deleteById(id);

	}

	@Override
	// Retrieves policies associated with a customer by their ID
	public List<PolicyDTO> getCustomerPolicies(Long customerId) {

		return policyClient.getPoliciesByCustomerId(customerId);

	}
	
	@Override
    public Customer assignPolicyToCustomer(Long customerId, Long policyId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
 
        try {
            PolicyDTO policy = policyClient.getById(policyId);
            if (policy == null || policy.getPolicyId() == null) {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }
        } catch (Exception e) {
            throw new PolicyNotFoundException("Policy service unavailable or policy not found.");
        }
 
        customer.setAssignedPolicyId(policyId);
        return customerRepository.save(customer);
    }
 
	@Override
	public List<Customer> getCustomersByPolicyId(Long policyId) throws CustomerNotFoundException {
		List<Customer> customers = customerRepository.findByAssignedPolicyId(policyId);
		
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers found for Policy ID: " + policyId);
        }
 
        return customers;
    
	}

}
