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

    public PredictDto messagePrediction(User user, Addiction addiction, Long daysClean) {
        if (addiction.getName().toLowerCase().contains("smoking")) {
            var predictedMsg = PredictMicroserviceController.predictLungCancer(user, daysClean);
            if (predictedMsg.isPresent()) {
                return new PredictDto(predictedMsg.get());
            }
        }

        var msg = messagesRepository.getMessage(addiction.getName(), daysClean);
        return new PredictDto(msg);
    }
}
