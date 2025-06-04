package com.cts.insurance.claim.exception;

/*
 * InvalidClaimException is a custom exception class that extends RuntimeException.
 * This exception is thrown when a claim is deemed invalid.
 */

public class InvalidClaimException extends RuntimeException {
	public InvalidClaimException(String message) {
		super(message);
	}
}