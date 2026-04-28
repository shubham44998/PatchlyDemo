package com.patchly.repository;

import com.patchly.entity.IncidentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<IncidentRequest, String> {

    //  Find by service (useful for matching)
    List<IncidentRequest> findByServiceEndpoint(String serviceEndpoint);

    //  Find by status
    List<IncidentRequest> findByStatus(String status);
}