package com.insurance.policyservice.exception;

public class PolicyNotFoundException extends RuntimeException {
    public PolicyNotFoundException(String message) {
        super(message);
    }
}
