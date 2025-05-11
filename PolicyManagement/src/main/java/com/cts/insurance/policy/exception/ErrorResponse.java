package com.cts.insurance.policy.exception;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class ErrorResponse {
    private String error;
    private String message;
}