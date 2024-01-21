package com.example.backend.user.requests;

import java.util.Objects;

public record RegisterRequest(String nick, String password, Integer weight, Integer height, Integer birthyear) {
    public RegisterRequest {
        Objects.requireNonNull(nick, "Nick not provided");
        Objects.requireNonNull(password, "password not provided");
        Objects.requireNonNull(weight, "weight not provided");
        Objects.requireNonNull(height, "height not provided");
        Objects.requireNonNull(birthyear, "birthyear not provided");
    }
}