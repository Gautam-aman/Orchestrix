package com.aman.backend.organization.controller;

import java.util.UUID;

import com.aman.backend.organization.dto.CreateOrganizationRequest;
import com.aman.backend.organization.dto.OrganizationResponse;
import com.aman.backend.organization.service.OrganizationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/organisations")
public class OrganizationController {

	private final OrganizationService organizationService;

	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	@PostMapping
	public ResponseEntity<OrganizationResponse> create(@Valid @RequestBody CreateOrganizationRequest request) {
		OrganizationResponse response = organizationService.create(request);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrganizationResponse> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(organizationService.getById(id));
	}

}
