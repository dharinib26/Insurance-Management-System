package com.cts.insurance.agent.model;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "agent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-generated unique identifier for each agent
 
    @NotBlank(message = "Agent name is required")
    private String name; // Name of the agent, must not be blank
 
    private String contactInfo;
 
    private String assignedPolicies; // Policies assigned to the agent
}
 
