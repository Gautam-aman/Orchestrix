package com.aman.backend.shared.exception;

import java.time.Instant;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(OrganizationNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFound(OrganizationNotFoundException exception, HttpServletRequest request) {
		return buildResponse(
				HttpStatus.NOT_FOUND,
				exception.getMessage(),
				request.getRequestURI(),
				List.of()
		);
	}

	@ExceptionHandler(DuplicateOrganizationSlugException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateSlug(DuplicateOrganizationSlugException exception, HttpServletRequest request) {
		return buildResponse(
				HttpStatus.CONFLICT,
				exception.getMessage(),
				request.getRequestURI(),
				List.of()
		);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException exception, HttpServletRequest request) {
		List<ApiErrorResponse.FieldValidationError> errors =
				exception.getBindingResult()
						.getFieldErrors()
						.stream()
						.map(error ->
								new ApiErrorResponse.FieldValidationError(
										error.getField(),
										error.getDefaultMessage()
								)
						)
						.toList();

		return buildResponse(
				HttpStatus.BAD_REQUEST,
				"Validation failed",
				request.getRequestURI(),
				errors
		);
	}

	private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus status, String message, String path, List<ApiErrorResponse.FieldValidationError> errors
	) {
		ApiErrorResponse response = new ApiErrorResponse(
				Instant.now(),
				status.value(),
				status.getReasonPhrase(),
				message,
				path,
				errors
		);

		return ResponseEntity.status(status).body(response);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleDatabaseConstraint(DataIntegrityViolationException exception, HttpServletRequest request) {
		return buildResponse(
				HttpStatus.CONFLICT,
				"Database constraint violation",
				request.getRequestURI(),
				List.of()
		);
	}

}
