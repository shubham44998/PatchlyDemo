package com.patchly.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "solution_verification")
@Data
public class SolutionVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Many verification rows → one solution
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solution_id", nullable = false)
    private SolutionRegistry solution;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "verified_by")
    private String verifiedBy;

    @Column(name = "verified_on")
    private LocalDateTime verifiedOn;

    @Column(name = "verification_notes", columnDefinition = "TEXT")
    private String verificationNotes;

    @Column(name = "applicability_criteria", columnDefinition = "TEXT")
    private String applicabilityCriteria;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "change_reason")
    private String changeReason;
}