package com.cts.insurance.policy.feignclient;

import com.cts.insurance.policy.dto.AgentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AgentManagement", url="http://localhost:8084")
public interface AgentClient {

	@GetMapping("/agents/{id}")
	AgentDTO getAgentById(@PathVariable Long agentId);
}
