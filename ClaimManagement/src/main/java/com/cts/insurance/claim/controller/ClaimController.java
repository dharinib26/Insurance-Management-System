package com.cts.insurance.claim.controller;

import com.cts.insurance.claim.model.Claim;

import com.cts.insurance.claim.service.ClaimService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/claims")

@RequiredArgsConstructor

@CrossOrigin("*")

public class ClaimController {

	private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

	private final ClaimService claimService;

	// Create a new claim

	@PostMapping("/save")

	public Claim createClaim(@Valid @RequestBody Claim claim) {

		logger.info("Received request to create claim");

		return claimService.createClaim(claim);

	}

	// Get claim by ID

	@GetMapping("/{id}")

	public Claim getClaimById(@PathVariable Long id) {

		logger.info("Received request to get claim with ID: {}", id);

		return claimService.getClaimById(id);

	}

	// Get all claims

	@GetMapping("/getAllClaims")

	public List<Claim> getAllClaims() {

		logger.info("Received request to get all claims");

		return claimService.getAllClaims();

	}

	// Update claim

	@PutMapping("/update-status/{id}")

	public Claim updateClaim(@PathVariable("id") Long id,@RequestBody Claim claim) {

		logger.info("Received request to update claim with ID: {}", id);

		return claimService.updateClaim(id, claim);

	}

	// Delete claim

	@DeleteMapping("/{id}")

	public void deleteClaim(@PathVariable Long id) {

		logger.info("Received request to delete claim with ID: {}", id);

		claimService.deleteClaim(id);

	}

	// Get claims by policy ID

	@GetMapping("/policy/{policyId}")

	public List<Claim> getClaimsByPolicyId(@PathVariable Long policyId) {

		logger.info("Received request to get claims for policy ID: {}", policyId);

		return claimService.getClaimsByPolicyId(policyId);

	}

}
