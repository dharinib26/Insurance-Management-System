package com.cts.insurance.claim.repository;

import com.cts.insurance.claim.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
	List<Claim> findByPolicyId(Long policyId);
}