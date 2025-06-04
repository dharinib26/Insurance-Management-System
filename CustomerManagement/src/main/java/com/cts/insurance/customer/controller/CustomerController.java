package com.cts.insurance.customer.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.cts.insurance.customer.customerDTO.PolicyDTO;
import com.cts.insurance.customer.exception.CustomerNotFoundException;
import com.cts.insurance.customer.model.Customer;
import com.cts.insurance.customer.service.CustomerServiceImpl;

import java.util.List;

//CustomerController handles HTTP requests related to customer operations
@RestController
@RequestMapping("/customers")
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerController {

	private CustomerServiceImpl customerService;

	// Creates a new customer
	@PostMapping("/create")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	// Retrieves all customers
	@GetMapping("/all")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	// Retrieves a customer by its ID
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable("id") Long id) throws CustomerNotFoundException {
		System.out.println("customer Id :" + id);
		return customerService.getCustomerById(id);
	}

	// Updates an existing customer
	@PutMapping("/update/{id}")
	public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customerDetails) {
		return customerService.updateCustomer(id, customerDetails);
	}

	// Deletes a customer by its ID
	@DeleteMapping("/delete/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}

	// Retrieves policies associated with a customer by their ID
	@GetMapping("/{id}/policies")
	public List<PolicyDTO> getCustomerPolicies(@PathVariable Long id) {
		return customerService.getCustomerPolicies(id);
	}
}