package com.example.backend.spotted;

import com.example.backend.report.ReportService;
import com.example.backend.spotted.dtos.SpottedDays;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("spotted")
public class SpottedController {
    private final ReportService reportService;

    public SpottedController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(path = "")
    public ResponseEntity<SpottedDays> getAddictionSpottedDays(@RequestHeader("UserID") int userId, @RequestParam int addictionId) {
        System.out.println("dupa");
        List<Date> dates = reportService.addictionReportDates(userId, addictionId);
        System.out.println("dupa1");
        List<Long> spottedDates = dates.stream()
                .map(date -> Duration.between(Instant.now(), date.toInstant()).toDays())
                .toList();

        var spottedDays = new SpottedDays(userId,addictionId, spottedDates);
        return ResponseEntity.ok(spottedDays);
    }
}
