package com.example.backend.report.requests;

import java.util.Objects;

public record AddReportRequest(Integer addictId, Integer addictionId, String postContent) {
    public AddReportRequest{
        Objects.requireNonNull(addictId, "Reported friend ID not provided");
        Objects.requireNonNull(addictionId, "Reported friend ID not provided");
        Objects.requireNonNull(postContent, "Reported friend ID not provided");
    }
}
