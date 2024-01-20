package com.example.backend.addiction.dtos;

import java.util.Optional;

public record UserAddictionDto(int id, String name, Optional<Long> daysClean) {
}
