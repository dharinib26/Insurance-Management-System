package com.cts.insurance.claim.exception;

public class InvalidClaimException extends RuntimeException {
	public InvalidClaimException(String message) {
		super(message);
	}
}