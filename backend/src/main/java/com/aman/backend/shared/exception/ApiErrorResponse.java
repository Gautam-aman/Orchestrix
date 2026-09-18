package com.aman.backend.shared.exception;

import java.time.Instant;
import java.util.List;

public record ApiErrorResponse(Instant timestamp, int status, String error, String message, String path, List<FieldValidationError> validationErrors
) {
	public record FieldValidationError(
			String field,
			String message
	) {
	}
}
