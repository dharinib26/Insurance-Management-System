package com.cts.insurance.claim.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Policy ID is required")
	private Long policyId;

	@NotNull(message = "Claim amount is required")
	@DecimalMin(value = "20000", inclusive = false, message = "Claim amount must be positive")
	private Double claimAmount;

	@NotBlank(message = "Claim reason is required")
	private String claimReason;

	@NotNull(message = "Claim date is required")
	private LocalDate claimDate;

	@NotBlank(message = "Claim status is required")
	private String claimStatus;
}