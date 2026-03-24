package com.patchly.controller;

import com.patchly.entity.IncidentKB;
import com.patchly.entity.SolutionRegistry;
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

//get all incidents
    @GetMapping
    public ResponseEntity<List<IncidentKB>> getAllIncidents() {
        List<IncidentKB> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents);
    }

    // Create new incident
    @PostMapping
    public ResponseEntity<IncidentKB> createIncident(
            @Valid @RequestBody IncidentKB incident) {
        IncidentKB savedIncident = incidentService.createIncident(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIncident);
    }

    // Link solution + root cause
    @PostMapping("/{incidentId}/link")
    public ResponseEntity<?> linkSolution(
            @PathVariable String incidentId,
            @RequestParam Long solutionId,
            @RequestParam Long rootCauseId) {

        return ResponseEntity.ok(
                incidentService.linkSolution(incidentId, solutionId, rootCauseId)
        );
    }

    // Get fix
    @GetMapping("/{incidentId}/fix")
    public ResponseEntity<SolutionRegistry> getFix(@PathVariable String incidentId) {
        return ResponseEntity.ok(incidentService.getFix(incidentId));
    }

    // Apply fix
    @PostMapping("/{incidentId}/apply-fix")
    public ResponseEntity<?> applyFix(@PathVariable String incidentId) {
        return ResponseEntity.ok(incidentService.applyFix(incidentId));
    }
}

