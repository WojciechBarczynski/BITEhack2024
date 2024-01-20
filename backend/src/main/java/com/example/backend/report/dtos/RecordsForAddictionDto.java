package com.example.backend.report.dtos;

import java.util.List;

public record RecordsForAddictionDto(String name, List<ReportDto> reports) {
}
