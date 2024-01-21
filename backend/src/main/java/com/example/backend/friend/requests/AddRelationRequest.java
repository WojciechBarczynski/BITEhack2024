package com.example.backend.friend.requests;

import java.util.Objects;

public record AddRelationRequest(Integer friendId, Integer addictionId) {
    public AddRelationRequest{
        Objects.requireNonNull(friendId, "Addict ID not provided");
        Objects.requireNonNull(addictionId, "Addiction ID not provided");
    }
}