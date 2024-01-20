package com.example.backend.predictions;

import com.example.backend.addiction.AddictionService;
import com.example.backend.predictions.dtos.PredictDto;
import com.example.backend.report.ReportService;
import com.example.backend.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Optional;

import static java.time.ZoneId.systemDefault;

@RestController
@RequestMapping("/predict")
public class PredictController {
    private final UserService userService;
    private final AddictionService addictionService;
    private final ReportService reportService;
    private final MessagesRepository messagesRepository;

    public PredictController(UserService userService, AddictionService addictionService, ReportService reportService) {
        this.userService = userService;
        this.addictionService = addictionService;
        this.reportService = reportService;
        this.messagesRepository = new MessagesRepository();
    }

    @GetMapping
    public ResponseEntity<PredictDto> getReportsForAddiction(@RequestHeader("UserID") int userId, @RequestParam int addictionId) {
        var addiction = addictionService.getAddiction(addictionId);
        var cleanFor = Period.ofDays(1);
        var lastReport = reportService.getLastUserReport(userId, addictionId);
        if (lastReport.isPresent()) {
            var lastReportedDate = LocalDate.ofInstant(lastReport.get().getReportTime().toInstant(), ZoneId.systemDefault());
            cleanFor = Period.between(LocalDate.now(), lastReportedDate);
        };

        if (addiction.getName().toLowerCase().contains("smoking")) {
            var user = userService.getUser(userId);
            var predictedMsg = PredictMicroserviceController.predictLungCancer(user, cleanFor);
            if (predictedMsg.isPresent()) {
                return ResponseEntity.ok(new PredictDto(predictedMsg.get()));
            }
        }

        var msg = messagesRepository.getMessage(addiction.getName(), cleanFor);
        return ResponseEntity.ok(new PredictDto(msg));
    }
}
