package com.example.backend.report.requests;

import java.util.Objects;

public record AddReportRequest(Integer friendId, Integer addictionId, String postContent) {
    public AddReportRequest{
        Objects.requireNonNull(friendId, "Reported friend ID not provided");
        Objects.requireNonNull(addictionId, "Reported addiction ID not provided");
    }
}
