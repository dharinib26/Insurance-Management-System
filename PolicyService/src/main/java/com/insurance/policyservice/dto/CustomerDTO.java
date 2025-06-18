package com.insurance.policyservice.dto;

import lombok.*; 
import jakarta.persistence.Embedded;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String phone;
    private String email;
    @Embedded
    private AddressDTO address;
    
    private Long assignedPolicyId;
}