package com.aman.backend.organization.dto;


import java.time.Instant;
import java.util.UUID;

import com.aman.backend.organization.entity.OrganizationStatus;

public record OrganizationResponse(
		UUID id,
		String name,
		String slug,
		OrganizationStatus status,
		Instant createdAt,
		Instant updatedAt
) {
}
