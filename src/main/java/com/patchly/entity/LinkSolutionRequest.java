package com.patchly.entity;

import lombok.Data;

@Data
public class LinkSolutionRequest {
    private String rootCause;
    private String solution;
}