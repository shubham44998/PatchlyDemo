package com.patchly.repository;

import com.patchly.entity.IncidentKB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<IncidentKB, String> {

}