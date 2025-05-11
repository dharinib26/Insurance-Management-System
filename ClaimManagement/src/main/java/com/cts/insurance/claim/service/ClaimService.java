package com.cts.insurance.claim.service;

import com.cts.insurance.claim.model.Claim;
import com.cts.insurance.claim.dto.PolicyDTO;

import java.util.List;

public interface ClaimService {
	Claim createClaim(Claim claim);

	Claim getClaimById(Long id);

	List<Claim> getAllClaims();

	Claim updateClaim(Long id, Claim claim);

	void deleteClaim(Long id);

	List<Claim> getClaimsByPolicyId(Long policyId);

}