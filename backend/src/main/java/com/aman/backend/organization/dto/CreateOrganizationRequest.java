package com.aman.backend.organization.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateOrganizationRequest(

		@NotBlank(message = "Organization name is required")
		@Size(max = 150, message = "Organization name cannot exceed 150 characters")
		String name,

		@NotBlank(message = "Organization slug is required")
		@Size(max = 100, message = "Organization slug cannot exceed 100 characters")
		@Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Slug must contain lowercase letters, numbers, and hyphens")
		String slug
) {
}
