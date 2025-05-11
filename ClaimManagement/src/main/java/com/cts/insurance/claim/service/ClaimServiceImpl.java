package com.cts.insurance.claim.service;

import com.cts.insurance.claim.exception.ClaimNotFoundException;
import com.cts.insurance.claim.model.Claim;
import com.cts.insurance.claim.dto.PolicyDTO;
import com.cts.insurance.claim.feign.PolicyClient;
import com.cts.insurance.claim.repository.ClaimRepository;

import jakarta.transaction.Transactional;
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
		return claimRepository.save(claim);
	}

	@Override
	public Claim getClaimById(Long id) {
		logger.info("Fetching claim with ID: {}", id);
		return claimRepository.findById(id)
				.orElseThrow(() -> new ClaimNotFoundException("Claim not found with ID: " + id));
	}

	@Override
	public List<Claim> getAllClaims() {
		logger.info("Fetching all claims");
		return claimRepository.findAll();
	}

	@Override
    @Transactional
    public Claim updateClaim(Long id, Claim updatedClaim) {
        logger.info("Updating claim with ID: {}", id);
 
        // Fetch the existing claim from the database
        Claim existingClaim = getClaimById(id);
 
        // Update only the claimStatus if it is provided in the request body
        if (updatedClaim.getClaimStatus() != null) {
            existingClaim.setClaimStatus(updatedClaim.getClaimStatus());
            logger.info("Claim status updated for ID: {} to {}", id, updatedClaim.getClaimStatus());
        } else {
            logger.warn("Claim status not provided for ID: {}", id);
        }
 
        // Save and return the updated claim
        return claimRepository.save(existingClaim);
	}

	@Override
	public void deleteClaim(Long id) {
		logger.info("Deleting claim with ID: {}", id);
		Claim existingClaim = getClaimById(id);
		claimRepository.delete(existingClaim);
	}

	@Override
	public List<Claim> getClaimsByPolicyId(Long policyId) {
		logger.info("Fetching claims for policy ID: {}", policyId);
		return claimRepository.findByPolicyId(policyId);
	}

	
}