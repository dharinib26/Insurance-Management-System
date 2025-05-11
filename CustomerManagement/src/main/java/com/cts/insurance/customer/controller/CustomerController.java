package com.cts.insurance.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cts.insurance.customer.customerDTO.PolicyDTO;
import com.cts.insurance.customer.exception.CustomerNotFoundException;
import com.cts.insurance.customer.model.Customer;
import com.cts.insurance.customer.service.CustomerServiceImpl;

import java.util.List;
 
@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {
 
    @Autowired
    private CustomerServiceImpl customerService;
 
    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
 
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
 
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
        		.orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
    }
 
    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerService.updateCustomer(id, customerDetails);
    }
 
    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
 
    @GetMapping("/search/name")
    public List<Customer> searchByName(@RequestParam String name) {
        return customerService.searchCustomersByName(name);
    }
 
    @GetMapping("/search/email")
    public Customer searchByEmail(@RequestParam String email) {
        return customerService.getCustomerByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
    }
 
    @GetMapping("/{id}/policies")
    public List<PolicyDTO> getCustomerPolicies(@PathVariable Long id) {
        return customerService.getCustomerPolicies(id);
    }
}