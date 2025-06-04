package com.cts.insurance.claim.service;

import com.cts.insurance.claim.exception.ClaimNotFoundException; 
import com.cts.insurance.claim.exception.InvalidClaimException;
import com.cts.insurance.claim.model.Claim;
import com.cts.insurance.claim.dto.PolicyDTO;
import com.cts.insurance.claim.feign.PolicyClient;
import com.cts.insurance.claim.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ClaimServiceImpl implements ClaimService {

	private static final Logger logger = LoggerFactory.getLogger(ClaimServiceImpl.class);

	private final ClaimRepository claimRepository;
	private final PolicyClient policyClient;

	@Override
	public Claim createClaim(Claim claim) {
		logger.info("Creating new claim for policy ID: {}", claim.getPolicyId());

		// Fetch the policy details using Feign client
		PolicyDTO policy = policyClient.getPolicyById(claim.getPolicyId());

		// Validate claim amount against policy coverage amount
		if (claim.getClaimAmount() > policy.getCoverageAmount()) {
			throw new InvalidClaimException("Claim amount exceeds coverage amount.");
		}
		claim.setClaimStatus("FILED");
		return claimRepository.save(claim);
	}

	// Retrieves a claim by its ID
	@Override
	public Claim getClaimById(Long id) {
		logger.info("Fetching claim with ID: {}", id);
		return claimRepository.findById(id)
				.orElseThrow(() -> new ClaimNotFoundException("Claim not found with ID: " + id));
	}

	// Retrieves all claims
	@Override
	public List<Claim> getAllClaims() {
		logger.info("Fetching all claims");
		return claimRepository.findAll();
	}

	@Override
    public Claim updateClaim(Long id, Claim updated) {
        Claim existing = claimRepository.findById(id)
                .orElseThrow(() -> new ClaimNotFoundException("Claim not found with id: " + id));
        existing.setClaimStatus(updated.getClaimStatus());
        return claimRepository.save(existing);
    }

	// Deletes a claim by its ID
	@Override
	public void deleteClaim(Long id) {
		logger.info("Deleting claim with ID: {}", id);
		Claim existingClaim = getClaimById(id);
		claimRepository.delete(existingClaim);
	}

	// Retrieves claims by the given policy ID
	@Override
	public List<Claim> getClaimsByPolicyId(Long policyId) {
		logger.info("Fetching claims for policy ID: {}", policyId);
		return claimRepository.findByPolicyId(policyId);
	}
}
