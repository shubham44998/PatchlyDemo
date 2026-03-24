package com.patchly.repository;

import com.patchly.entity.IncidentKB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<IncidentKB, String> {

    //  Find by service (useful for matching)
    List<IncidentKB> findByServiceEndpoint(String serviceEndpoint);

    //  Find by status
    List<IncidentKB> findByStatus(String status);
}