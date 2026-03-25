package com.patchly.service;

import com.patchly.entity.*;
import com.patchly.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final SolutionRepository solutionRepository;
    private final RootCauseRepository rootCauseRepository;
    private final SolutionVerificationRepository verificationRepository;

    // Get all incidents
    public List<IncidentKB> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Create new incident
    public IncidentKB createIncident(IncidentKB incident) {
        incident.setIncidentDatetime(LocalDateTime.now());
        incident.setStatus("NEW");
        return incidentRepository.save(incident);
    }

    // Link solution + root cause (Agent will call this)
    public IncidentKB linkSolution(String incidentId, String rootCauseText, String solutionText) {

        IncidentKB incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident not found"));

        // Save root cause
        RootCause rootCause = new RootCause();
        rootCause.setDescription(rootCauseText);
        rootCause.setUpdatedBy("AGENT");
        rootCause.setChangeReason("Auto RCA");
        rootCauseRepository.save(rootCause);

        // Save solution
        SolutionRegistry solution = new SolutionRegistry();
        solution.setFixSteps(solutionText);
        solution.setRiskLevel("MEDIUM");
        solution.setUpdatedBy("AGENT");
        solution.setChangeReason("Auto Solution");
        solutionRepository.save(solution);

        // 🔥 CORRECT LINKING
        incident.setRootCause(rootCause);
        incident.setSolution(solution);
        incident.setStatus("SOLUTION_LINKED");

        return incidentRepository.save(incident);
    }

    // Get fix for incident
    public SolutionRegistry getFix(String incidentId) {

        IncidentKB incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident not found"));

        return incident.getSolution();
    }

    // Check if solution is verified
    public boolean isSolutionVerified(Long solutionId) {
        return verificationRepository.findBySolution_SolutionId(solutionId)
                .stream()
                .anyMatch(v -> Boolean.TRUE.equals(v.getIsVerified()));
    }

    // Apply fix (basic version)
    public String applyFix(String incidentId) {

        IncidentKB incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident not found"));

        SolutionRegistry solution = incident.getSolution();

        if (solution == null) {
            throw new RuntimeException("No solution linked");
        }

        boolean verified = isSolutionVerified(solution.getSolutionId());

        if (!verified) {
            incident.setStatus("AWAITING_REVIEW");
            incidentRepository.save(incident);
            return "Solution not verified";
        }

        // Execute fix
        runFixSteps(solution.getFixSteps());

        incident.setStatus("ROLLED_OUT_PENDING_QA");
        incidentRepository.save(incident);

        return "Fix applied successfully";
    }

    //  Execute commands
    private void runFixSteps(String fixSteps) {
        try {
            ProcessBuilder builder = new ProcessBuilder("bash", "-c", fixSteps);
            Process process = builder.start();
            process.waitFor();
        } catch (Exception e) {
            throw new RuntimeException("Fix execution failed");
        }
    }
}