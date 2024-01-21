package com.example.backend.spotted.dtos;

import java.util.List;

public record SpottedDays(int userId, int addictionId, List<Long> spottedDays) {
}
