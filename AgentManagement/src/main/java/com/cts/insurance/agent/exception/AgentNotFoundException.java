package com.cts.insurance.agent.exception;
 
public class AgentNotFoundException extends RuntimeException {
    public AgentNotFoundException(String msg) {
        super(msg);
    }
}