package com.cts.insurance.policy.controller;
 
import com.cts.insurance.policy.dto.PolicyDTO;
import com.cts.insurance.policy.model.Policy;
import com.cts.insurance.policy.service.PolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/policies")
@RequiredArgsConstructor
@Slf4j
public class PolicyController {
 
    private final PolicyService service;
 
    // Create policy
    @PostMapping("/create")
    public Policy create(@RequestBody Policy policy) {
        return service.createPolicy(policy);
    }
 
    // Get all policies
    @GetMapping("/all")
    public List<Policy> getAll() {
        return service.getAllPolicies();
    }
 
    // Get by ID
    @GetMapping("/{id}")
    public Policy getById(@PathVariable Long id) {
        return service.getPolicyById(id);
    }
 
    // Update policy
    @PutMapping("/update/{id}")
    public Policy update(@PathVariable Long id, @RequestBody Policy policy) {
        return service.updatePolicy(id, policy);
    }
 
    // Delete policy
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deletePolicy(id);
    }
 
    // Get policy with customer details
    @GetMapping("/with-customer/{policyId}/{customerId}")
    public PolicyDTO getPolicyWithCustomer(@PathVariable("policyId") Long policyId, @PathVariable("customerId") Long customerId) {
        return service.getPolicyWithCustomer(policyId, customerId);
    }
 
    // Get policy with agent details
    @GetMapping("/with-agent/{policyId}/{agentId}")
    public PolicyDTO getWithAgent(@PathVariable("policyId") Long policyId, @PathVariable("agentId") Long agentId) {
        return service.getPolicyWithAgent(policyId, agentId);
    }
}