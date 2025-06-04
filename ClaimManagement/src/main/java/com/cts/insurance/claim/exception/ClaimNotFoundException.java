package com.cts.insurance.claim.exception;

//Custom exception for handling cases where a claim is not found

public class ClaimNotFoundException extends RuntimeException {

	public ClaimNotFoundException(String message) {

		super(message);

	}

}
