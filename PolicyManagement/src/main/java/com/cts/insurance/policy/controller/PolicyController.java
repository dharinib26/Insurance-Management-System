package com.cts.insurance.policy.controller;

import com.cts.insurance.policy.dto.PolicyDTO;
import com.cts.insurance.policy.exception.CustomerNotFoundException;
import com.cts.insurance.policy.model.Policy;
import com.cts.insurance.policy.service.PolicyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class PolicyController {

	private final PolicyService service;

	// Create policy
//	@PostMapping("/create")
//	public Policy create(@RequestBody Policy policy) throws CustomerNotFoundException {
//		return service.createPolicy(policy);
//	}
	
	@PostMapping("/create")
	public Policy create(@RequestBody Policy policy) throws CustomerNotFoundException {
	    return service.createPolicy(policy);
	}

	// Get all policies
	@GetMapping("/all")
	public List<Policy> getAll() {
		return service.getAllPolicies();
	}

	// Get by ID
	@GetMapping("/{id}")
	public Policy getById(@PathVariable Long id) {
		return service.getPolicyById(id);
	}

	// Update policy
	@PutMapping("/update/{id}")
	public Policy update(@PathVariable("id") Long id, @RequestBody Policy policy) {
		return service.updatePolicy(id, policy);
	}

	// Delete policy
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		service.deletePolicy(id);
	}
	
	// Get policies by Customer ID
	@GetMapping("/getPoliciesByCustomerId/{customerId}")
	public List<Policy> getPoliciesByCustomerId(@PathVariable Long customerId) {
	    return service.getPoliciesByCustomerId(customerId);
	}

	
}