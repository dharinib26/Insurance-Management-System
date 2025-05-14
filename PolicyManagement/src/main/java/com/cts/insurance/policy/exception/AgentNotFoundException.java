package com.cts.insurance.policy.exception;

public class AgentNotFoundException extends RuntimeException{
	public AgentNotFoundException(String message) {
        super(message);
    }
}
