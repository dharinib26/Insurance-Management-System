package com.cts.insurance.notification.feign;

import com.cts.insurance.notification.dto.PolicyDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
@FeignClient(name = "PolicyManagement", path = "/policies")
public interface PolicyClient {
 
    // Fetch policy details by ID from policy module
	@GetMapping("/{id}")
    public PolicyDTO getById(@PathVariable Long id);
}
