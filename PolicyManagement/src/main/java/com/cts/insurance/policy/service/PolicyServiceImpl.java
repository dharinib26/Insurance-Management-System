package com.cts.insurance.policy.service;

import com.cts.insurance.policy.dto.AgentDTO;
import com.cts.insurance.policy.dto.Customer;
import com.cts.insurance.policy.dto.PolicyDTO;
import com.cts.insurance.policy.exception.AgentNotFoundException;
import com.cts.insurance.policy.exception.CustomerNotFoundException;
import com.cts.insurance.policy.exception.PolicyNotFoundException;
import com.cts.insurance.policy.feignclient.AgentClient;
import com.cts.insurance.policy.feignclient.CustomerClient;
import com.cts.insurance.policy.model.Policy;
import com.cts.insurance.policy.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PolicyServiceImpl implements PolicyService {

	private final PolicyRepository repository;
	private final CustomerClient customerClient;
	private final AgentClient agentClient;

	@Override
	public Policy createPolicy(Policy policy) throws CustomerNotFoundException {
		log.info("Creating policy: {}", policy);
		//Validate Customer
        try {
			customerClient.getCustomerById(policy.getCustomerId());
		} catch (Exception e) {
			log.error("Invalid Customer ID: {}", policy.getCustomerId(), e);
			throw new CustomerNotFoundException("Invalid Customer ID: " + policy.getCustomerId());
		}
 
		// Validate agent
        try {
			agentClient.getAgentById(policy.getAgentId());
		} catch (Exception e) {
			log.error("Invalid Agent ID: {}", policy.getAgentId(), e);
			throw new AgentNotFoundException("Invalid Agent ID: " + policy.getAgentId());
		}
        return repository.save(policy);
	}

	@Override
	public List<Policy> getAllPolicies() {
		log.info("Retrieving all policies");
		return repository.findAll();
	}

	@Override
	public Policy getPolicyById(Long id) {
		log.info("Retrieving policy by ID: {}", id);
		return repository.findById(id)
				.orElseThrow(() -> new PolicyNotFoundException("Policy not found with ID: " + id));
	}

	@Override
	public Policy updatePolicy(Long id, Policy updatedPolicy) {
		log.info("Updating policy with ID: {}", id);
		Policy existing = getPolicyById(id);
		existing.setName(updatedPolicy.getName());
		existing.setPolicyType(updatedPolicy.getPolicyType());
		existing.setDescription(updatedPolicy.getDescription());
		existing.setPremium(updatedPolicy.getPremium());
		existing.setCoverageAmount(updatedPolicy.getCoverageAmount());
		return repository.save(existing);
	}

	@Override
	public void deletePolicy(Long id) {
		log.info("Deleting policy with ID: {}", id);
		repository.deleteById(id);
	}

	@Override
	public PolicyDTO getPolicyWithCustomer(Long policyId, Long customerId) throws CustomerNotFoundException {
		log.info("Fetching policy {} with customer {}", policyId, customerId);
		Policy policy = getPolicyById(policyId);
		if (policy == null) {
			throw new PolicyNotFoundException("Policy not found with ID: " + policyId);
		}
		Customer customer = customerClient.getCustomerById(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
		}
		PolicyDTO dto = new PolicyDTO();
		dto.setId(policy.getId());
		dto.setPolicyName(policy.getName());
		dto.setPolicyType(policy.getPolicyType());
		dto.setDescription(policy.getDescription());
		dto.setPremium(policy.getPremium());
		dto.setCoverageAmount(policy.getCoverageAmount());
		dto.setCustomerId(policy.getCustomerId());
		return dto;
	}

	@Override
	public PolicyDTO getPolicyWithAgent(Long policyId, Long agentId) {
		log.info("Fetching policy {} with agent {}", policyId, agentId);
		Policy policy = getPolicyById(policyId);
		AgentDTO agent = agentClient.getAgentById(agentId);

		PolicyDTO dto = new PolicyDTO();
		dto.setId(policy.getId());
		dto.setPolicyName(policy.getName());
		dto.setPolicyType(policy.getPolicyType());
		dto.setDescription(policy.getDescription());
		dto.setPremium(policy.getPremium());
		dto.setCoverageAmount(policy.getCoverageAmount());
		dto.setAgentId(policy.getAgentId());
		return dto;
	}
}