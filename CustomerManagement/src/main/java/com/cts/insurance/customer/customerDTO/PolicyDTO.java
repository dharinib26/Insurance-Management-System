package com.cts.insurance.customer.customerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//PolicyDTO is a Data Transfer Object representing policy details
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDTO {
	private Long policyId;
	private String policyName;
	private Double premiumAmount;
}