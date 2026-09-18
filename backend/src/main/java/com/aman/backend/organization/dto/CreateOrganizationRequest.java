package com.aman.backend.organization.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public record CreateOrganizationRequest(

		@NotBlank(message = "Organization name is required")
		@Size(max = 150, message = "Organization name cannot exceed 150 characters")
		String name,

		@NotBlank(message = "Organization slug is required")
		@Size(max = 100, message = "Organization slug cannot exceed 100 characters")
		String slug
) {
}
