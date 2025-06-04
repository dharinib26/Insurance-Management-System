package com.cts.insurance.policy.dto;
 
import lombok.Data;
 
@Data
public class PolicyDTO {
    private Long id;
    private String policyName;
    private String policyType;
    private String description;
    private Double premium;
    private long coverageAmount;
    private long customerId;
    private long agentId;
}