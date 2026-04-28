package com.patchly.controller;

import com.patchly.entity.IncidentRequest;
import com.patchly.entity.LinkSolutionRequest;
import com.patchly.entity.SolutionRegistry;
import com.patchly.service.IncidentService;
import com.patchly.utils.ApiResponse;
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
    public ResponseEntity<List<IncidentRequest>> getAllIncidents() {
        List<IncidentRequest> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents);
    }

    // Create new incident
    @PostMapping
    public ResponseEntity<IncidentRequest> createIncident(
            @Valid @RequestBody IncidentRequest incident) {
        IncidentRequest savedIncident = incidentService.createIncident(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIncident);
    }

    // Link solution + root cause
    @PostMapping("/{incidentId}/link")
    public ResponseEntity<?> linkSolution(
            @PathVariable String incidentId,
            @RequestBody LinkSolutionRequest request) {

        return ResponseEntity.ok(
                incidentService.linkSolution(
                        incidentId,
                        request.getRootCause(),
                        request.getSolution()
                )
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
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck() {
        return ResponseEntity.ok(ApiResponse.ok("Incident Service is healthy"));
    }
}

