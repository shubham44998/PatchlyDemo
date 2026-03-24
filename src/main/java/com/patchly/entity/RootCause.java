package com.patchly.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "root_cause")
@Data
@NoArgsConstructor
public class RootCause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "root_cause_id")
    private Long rootCauseId;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "common_signals", columnDefinition = "TEXT")
    private String commonSignals;

    @Column(name = "affected_components")
    private String affectedComponents;

    @Column(name = "detection_heuristics", columnDefinition = "TEXT")
    private String detectionHeuristics;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "change_reason")
    private String changeReason;
}