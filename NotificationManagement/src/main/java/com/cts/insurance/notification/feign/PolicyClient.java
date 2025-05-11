package com.cts.insurance.notification.feign;

import com.cts.insurance.notification.dto.PolicyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
@FeignClient(name = "PolicyManagement", url = "http://localhost:8081/policy")
public interface PolicyClient {
 
    // Fetch policy details by ID from policy microservice
    @GetMapping("/get/{id}")
    PolicyDTO getPolicyById(@PathVariable("id") Long id);
}
