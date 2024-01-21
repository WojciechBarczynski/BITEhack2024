package com.example.backend.report;

import com.example.backend.report.dtos.RecordsForAddictionDto;
import com.example.backend.report.requests.AddReportRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<String> addReport(@RequestHeader("UserID") int addictId, @RequestBody AddReportRequest request) {
        reportService.addReport(request.friendId(), addictId, request.addictionId(), request.postContent());
        return ResponseEntity.ok("Friend reported successfully");
    }

    @GetMapping
    public ResponseEntity<RecordsForAddictionDto> getReportsForAddiction(@RequestHeader("UserID") int userId, @RequestParam int addictionId) {
        var response = reportService.getReportsForAddiction(userId, addictionId);
        return ResponseEntity.ok(response);
    }
}
