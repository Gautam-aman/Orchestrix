package com.aman.backend.shared.exception;

public class DuplicateOrganizationSlugException extends RuntimeException {

	public DuplicateOrganizationSlugException(String message) {
		super(message);
	}

}
