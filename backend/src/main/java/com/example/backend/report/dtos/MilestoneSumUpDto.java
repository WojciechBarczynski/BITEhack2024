package com.example.backend.report.dtos;

import java.util.Optional;

public record MilestoneSumUpDto(MilestoneDto archived, MilestoneDto next, Optional<String> predictionMsg) {
}
