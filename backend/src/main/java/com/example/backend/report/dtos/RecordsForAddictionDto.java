package com.example.backend.report.dtos;

import java.util.List;
import java.util.Optional;

public record RecordsForAddictionDto(String name, MilestoneSumUpDto milestones, List<ReportDto> reports) {
}
