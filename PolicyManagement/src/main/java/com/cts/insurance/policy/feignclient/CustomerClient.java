package com.cts.insurance.policy.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.insurance.policy.dto.CustomerDTO;

@FeignClient(name = "CustomerManagement",url="http://localhost:8081")
public interface CustomerClient {
 
	@GetMapping("/api/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") Long id);
}
