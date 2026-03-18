package com.patchly.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "incident_kb")
@Data
@NoArgsConstructor
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

    @Column(name = "root_cause")
    private String rootCause;

    @Column(name = "resolution", length = 2000)
    private String resolution;

    @Column(name = "preventive_actions")
    private String preventiveActions;

    @Column(name = "reference_links")
    private String referenceLinks;

    @Column(name = "status")
    private String status;


}