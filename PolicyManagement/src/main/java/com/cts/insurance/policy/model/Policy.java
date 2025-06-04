package com.cts.insurance.policy.model;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "policy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank(message = "Policy name cannot be blank")
    private String name;
 
    private String policyType;
 
    @NotBlank(message = "Policy description cannot be blank")
    private String description;
 
    @NotNull(message = "Premium amount cannot be null")
    private Double premium;
 
    private long coverageAmount;
    private long customerId;
    private long agentId;
}
 