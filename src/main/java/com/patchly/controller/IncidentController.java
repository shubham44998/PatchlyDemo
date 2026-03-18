package com.patchly.controller;

import com.patchly.entity.IncidentKB;
import com.patchly.service.IncidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping
    public ResponseEntity<List<IncidentKB>> getAllIncidents() {
        List<IncidentKB> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents);
    }

    @PostMapping
    public ResponseEntity<IncidentKB> createIncident(
            @Valid @RequestBody IncidentKB incident) {
        IncidentKB savedIncident = incidentService.createIncident(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIncident);
    }
}

