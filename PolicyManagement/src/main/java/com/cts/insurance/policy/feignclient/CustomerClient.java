package com.cts.insurance.policy.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.insurance.policy.dto.Customer;
import com.cts.insurance.policy.exception.CustomerNotFoundException;

@FeignClient(name = "CustomerManagement", url = "http://localhost:8081")
public interface CustomerClient {

	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable("id") long customerId);

}
