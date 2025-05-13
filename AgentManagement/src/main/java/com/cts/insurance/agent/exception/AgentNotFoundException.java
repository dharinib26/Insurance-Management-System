package com.cts.insurance.agent.exception;

// Custom exception for agent not found scenarios
public class AgentNotFoundException extends RuntimeException {

	// Constructor with error message
	public AgentNotFoundException(String msg) {
		super(msg);
	}
}
