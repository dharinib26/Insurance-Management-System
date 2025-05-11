package com.cts.insurance.customer.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice

public class GlobalExceptionHandler {

 @ExceptionHandler(CustomerNotFoundException.class)

 @ResponseStatus(HttpStatus.NOT_FOUND)

 public String handleCustomerNotFound(CustomerNotFoundException ex) {

     return ex.getMessage();

 }

 @ExceptionHandler(Exception.class)

 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

 public String handleGenericException(Exception ex) {

     return "An unexpected error occurred: " + ex.getMessage();

 }

}

