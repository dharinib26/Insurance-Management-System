package com.cts.insurance.customer.feign;

import org.springframework.cloud.openfeign.FeignClient; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.insurance.customer.customerDTO.PolicyDTO;


import java.util.List;

/*
 * PolicyClient is a Feign client interface for communicating with the policy-service.
 * It provides methods to interact with policy-related endpoints.
 */

@FeignClient(name = "policy-service")
public interface PolicyClient {
	@GetMapping("/policies/customer/{customerId}")
	List<PolicyDTO> getPoliciesByCustomerId(@PathVariable Long customerId);
	
	@GetMapping("/{id}")
	public PolicyDTO getById(@PathVariable Long id);
}