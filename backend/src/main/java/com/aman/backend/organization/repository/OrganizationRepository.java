package com.aman.backend.organization.repository;

import java.util.UUID;

import com.aman.backend.organization.entity.Organization;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
	boolean existsBySlug(String slug);
}
