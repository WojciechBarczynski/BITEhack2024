package com.example.backend.report;

import com.example.backend.report.dtos.ReportDto;
import com.example.backend.report.requests.AddReportRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<String> addReport(@RequestHeader("UserID") int friendId, @RequestBody AddReportRequest request){
        reportService.addReport(friendId, request.addictId(), request.addictionId(), request.postContent());
        return ResponseEntity.ok("Friend reported succesfully");
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> getReportsForAddiction(@RequestHeader("UserID") int userId, @RequestParam int addictionId){
        var reports = reportService.getReportsForAddiction(userId, addictionId);
        var response = reports
                .stream()
                .map(report -> new ReportDto(report.getRelation().getFriend().getNick(),
                report.getPostContent(), report.getReportTime().toString()))
                .toList();
        return ResponseEntity.ok(response);
    }
}
