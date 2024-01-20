package com.example.backend.user.requests;

import java.util.Objects;

public record LoginRequest(String nick, String password){
    public LoginRequest{
        Objects.requireNonNull(nick, "Nick not provided");
        Objects.requireNonNull(password, "password not provided");
    }
}