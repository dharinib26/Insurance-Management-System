package com.cts.insurance.policy.service;

import com.cts.insurance.policy.dto.PolicyDTO;
import com.cts.insurance.policy.exception.CustomerNotFoundException;
import com.cts.insurance.policy.model.Policy;

import java.util.List;

public interface PolicyService {
 
    Policy createPolicy(Policy policy) throws CustomerNotFoundException;
 
    List<Policy> getAllPolicies();
 
    Policy getPolicyById(Long id);
 
    Policy updatePolicy(Long id, Policy updatedPolicy);
 
    void deletePolicy(Long id);  

	List<Policy> getPoliciesByCustomerId(Long id);

}