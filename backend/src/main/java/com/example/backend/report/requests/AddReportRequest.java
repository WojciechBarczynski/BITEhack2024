package com.example.backend.report.requests;

import java.util.Objects;

public record AddReportRequest(Integer addictId, Integer addictionId, String postContent) {
    public AddReportRequest {
        Objects.requireNonNull(addictId, "Reported addict ID not provided");
        Objects.requireNonNull(addictionId, "Reported addiction ID not provided");
    }
}
