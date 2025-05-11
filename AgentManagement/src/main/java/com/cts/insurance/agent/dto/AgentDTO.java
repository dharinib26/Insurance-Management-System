package com.cts.insurance.agent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {
 
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String region;
 
    // Constructors, Getters, Setters
  
}
