package com.aman.backend.organization.service;

import java.util.Locale;
import java.util.UUID;

import javax.print.attribute.standard.OrientationRequested;

import com.aman.backend.organization.dto.CreateOrganizationRequest;
import com.aman.backend.organization.dto.OrganizationResponse;
import com.aman.backend.organization.entity.Organization;
import com.aman.backend.organization.entity.OrganizationStatus;
import com.aman.backend.organization.repository.OrganizationRepository;
import com.aman.backend.shared.exception.DuplicateOrganizationSlugException;
import com.aman.backend.shared.exception.OrganizationNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationService {

	private final OrganizationRepository organizationRepository;

	public OrganizationResponse create(CreateOrganizationRequest request) {
		String normalSlug = request.slug()
				.trim()
				.toLowerCase(Locale.ROOT);

		if(organizationRepository.existsBySlug(normalSlug)) {
			throw new DuplicateOrganizationSlugException(normalSlug);
		}

		Organization organization = new Organization();
		organization.setName(request.name());
		organization.setSlug(normalSlug);
		organization.setStatus(OrganizationStatus.ACTIVE);
		Organization savedOrganisation = organizationRepository.save(organization);

		return toResponse(savedOrganisation);

	}

	@Transactional
	public OrganizationResponse getById(UUID id) {
		Organization organization = organizationRepository.findById(id)
				.orElseThrow(() -> new OrganizationNotFoundException(id));

		return toResponse(organization);
	}


	private OrganizationResponse toResponse(Organization organization) {
		return new OrganizationResponse(
				organization.getId(),
				organization.getName(),
				organization.getSlug(),
				organization.getStatus(),
				organization.getCreatedAt(),
				organization.getUpdatedAt()
		);
	}

}
