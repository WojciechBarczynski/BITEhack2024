package com.example.backend.predictions.dtos;

import com.example.backend.report.dtos.MilestoneDto;

public record PredictDto(MilestoneDto achievedMilestone, MilestoneDto nextMilestone) {
}