package com.patchly.repository;

import com.patchly.entity.SolutionRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<SolutionRegistry, Long> {
}