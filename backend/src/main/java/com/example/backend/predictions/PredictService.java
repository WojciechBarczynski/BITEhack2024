package com.example.backend.predictions;

import com.example.backend.addiction.Addiction;
import com.example.backend.predictions.dtos.PredictDto;
import com.example.backend.report.Report;
import com.example.backend.user.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Optional;

public class PredictService {
    private final MessagesRepository messagesRepository;

    public PredictService() {
        this.messagesRepository = new MessagesRepository();
    }

    public PredictDto messagePrediction(User user, Addiction addiction, Optional<Report> lastReport) {
        var cleanFor = Period.ofDays(1);
        if (lastReport.isPresent()) {
            var lastReportedDate = LocalDate.ofInstant(lastReport.get().getReportTime().toInstant(), ZoneId.systemDefault());
            cleanFor = Period.between(LocalDate.now(), lastReportedDate);
        }
        ;

        if (addiction.getName().toLowerCase().contains("smoking")) {
            var predictedMsg = PredictMicroserviceController.predictLungCancer(user, cleanFor);
            if (predictedMsg.isPresent()) {
                return new PredictDto(predictedMsg.get());
            }
        }

        var msg = messagesRepository.getMessage(addiction.getName(), cleanFor);
        return new PredictDto(msg);
    }
}
