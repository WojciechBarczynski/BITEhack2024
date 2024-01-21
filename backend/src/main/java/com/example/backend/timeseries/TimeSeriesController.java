package com.example.backend.timeseries;

import com.example.backend.report.ReportService;
import com.example.backend.timeseries.dtos.TimeSeries;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping("timeseries")
public class TimeSeriesController {
    private final ReportService reportService;

    public TimeSeriesController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(path = "")
    public ResponseEntity<TimeSeries> getAddictionSpottedDays(@RequestHeader("UserID") int userId, @RequestParam int addictionId) {
        ArrayList<Integer> timeSeries = new ArrayList<>(Collections.nCopies(30, 0));

        reportService.addictionReportDates(userId, addictionId)
                .stream()
                .map(date -> Duration.between(Instant.now(), date.toInstant()).toDays())
                .filter(day -> day <= 30)
                .forEach(day -> timeSeries.set(day.intValue(), timeSeries.get(day.intValue()) + 1));

        var res = new TimeSeries(timeSeries);
        return ResponseEntity.ok(res);
    }
}
