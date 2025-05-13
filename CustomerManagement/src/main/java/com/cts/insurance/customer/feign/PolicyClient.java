package com.cts.insurance.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.insurance.customer.customerDTO.PolicyDTO;

import java.util.List;

@FeignClient(name = "policy-service")
public interface PolicyClient {
    @GetMapping("/policies/customer/{customerId}")
    List<PolicyDTO> getPoliciesByCustomerId(@PathVariable Long customerId);
}