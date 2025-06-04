package com.cts.insurance.claim.feign;

import com.cts.insurance.claim.dto.PolicyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * PolicyClient is a Feign client interface for communicating with the Policy Management service.
 * It provides methods to interact with policy-related endpoints.
 */

@FeignClient(name = "PolicyManagement")
public interface PolicyClient {

	@GetMapping("/policies/{id}")
	PolicyDTO getPolicyById(@PathVariable("id") Long id);
}
