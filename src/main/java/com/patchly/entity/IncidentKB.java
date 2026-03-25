package com.patchly.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "incident_kb")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class IncidentKB {


    @Id
    @Column(name = "incident_id")
    private String incidentId;

    @Column(name = "incident_title")
    private String incidentTitle;

    @Column(name = "incident_datetime")
    private LocalDateTime incidentDatetime ;

    @Column(name = "service_endpoint")
    private String serviceEndpoint;

    @Column(name = "impact")
    private String impact;

    @Column(name = "error_snapshot", length = 2000)
    private String errorSnapshot;

    @Column(name = "resolution", length = 2000)
    private String resolution;

    @Column(name = "preventive_actions")
    private String preventiveActions;

    @Column(name = "reference_links")
    private String referenceLinks;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solution_id")
    private SolutionRegistry solution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root_cause_id")
    private RootCause rootCause;
}