package com.cts.insurance.claim.repository;

import com.cts.insurance.claim.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * ClaimRepository is a JPA repository interface for managing Claim entities.
 * It provides methods for performing CRUD operations on Claim data.
 */

public interface ClaimRepository extends JpaRepository<Claim, Long> {
	List<Claim> findByPolicyId(Long policyId);
}