package com.insurance.agentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice

public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)

	@ExceptionHandler(MethodArgumentNotValidException.class)

	public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {

			errors.put(error.getField(), error.getDefaultMessage());

			LOGGER.error("Validation Error - Field: {} | Message: {}", error.getField(), error.getDefaultMessage()); // ✅LOGGINGHERE

		});

		return errors;

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)

	@ExceptionHandler(ConstraintViolationException.class)

	public Map<String, String> handleConstraintViolations(ConstraintViolationException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getConstraintViolations().forEach(violation -> {

			String field = violation.getPropertyPath().toString();

			String message = violation.getMessage();

			errors.put(field, message);

			LOGGER.error("Validation Error - Field: {} | Message: {}", field, message); // ✅ LOGGING HERE

		});

		return errors;

	}

	/** Handles Customer Not Found */

	@ResponseStatus(HttpStatus.NOT_FOUND) //

	@ExceptionHandler(AgentNotFoundException.class)

	public Map<String, String> handleAgent(AgentNotFoundException ex) {

		LOGGER.error(" Customer Id : {}", ex.getMessage());

		return Map.of("error", ex.getMessage());

	}

}
