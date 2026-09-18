package com.aman.backend.shared.exception;

import java.util.UUID;

public class OrganizationNotFoundException extends RuntimeException {

	public OrganizationNotFoundException(UUID message) {
		super(message);
	}

}
