package com.cts.insurance.claim.dto;

import lombok.*;

@Data

@NoArgsConstructor

@AllArgsConstructor

@Builder

public class PolicyDTO {

	private Long id;

	private String policyNumber;

	private String policyType;

	private Double coverageAmount;

	private String policyStatus;

}
