package com.patchly.service;

import com.patchly.entity.IncidentKB;
import com.patchly.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository repository;

    public List<IncidentKB> getAllIncidents() {
        return repository.findAll();
    }

    public IncidentKB createIncident(IncidentKB incident) {
        return repository.save(incident);
    }
}
