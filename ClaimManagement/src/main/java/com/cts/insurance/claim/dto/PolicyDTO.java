package com.cts.insurance.claim.dto;

import lombok.*;

@Data // Lombok annotation to generate getters, setters, toString, equals, and
		// hashCode methods

@NoArgsConstructor

@AllArgsConstructor // Lombok annotation to generate a constructor with all fields

@Builder

public class PolicyDTO {

	private Long id;

	private String policyNumber;

	private String policyType;

	private Double coverageAmount;

	private String policyStatus;

}
