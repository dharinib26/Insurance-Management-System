package com.insurance.policyservice.controller;

import com.insurance.policyservice.dto.AgentDTO; 
import com.insurance.policyservice.dto.CustomerDTO;
import com.insurance.policyservice.model.Policy;
import com.insurance.policyservice.service.PolicyService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService service;

    @PostMapping("/savePolicy")
    public Policy addPolicy(@RequestBody Policy policy) {
        return service.addPolicy(policy);
    }

    @GetMapping("/getAllPolicies")
    public List<Policy> getAllPolicies() {
        return service.getAllPolicies();
    }

    @GetMapping("/getPolicyById/{id}")
    public Policy getPolicyById(@PathVariable Long id) {
        return service.getPolicyById(id);
    }

    @PutMapping("/updatePolicy/{id}")
    public Policy updatePolicy(@PathVariable Long id, @RequestBody Policy policy) {
        return service.updatePolicy(id, policy);
    }

    @DeleteMapping("/deletePolicy/{id}")
    public String deletePolicy(@PathVariable Long id) {
        return service.deletePolicy(id);
    }

   
    
  
    
}