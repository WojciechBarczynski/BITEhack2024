package com.example.backend.friend.requests;

import java.util.Objects;

public record AddRelationRequest(Integer addictId, Integer addictionId) {
    public AddRelationRequest{
        Objects.requireNonNull(addictId, "Addict ID not provided");
        Objects.requireNonNull(addictionId, "Addiction ID not provided");
    }
}