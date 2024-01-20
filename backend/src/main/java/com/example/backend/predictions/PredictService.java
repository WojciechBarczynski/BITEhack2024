package com.example.backend.predictions;

import com.example.backend.addiction.Addiction;
import com.example.backend.predictions.dtos.PredictDto;
import com.example.backend.report.dtos.MilestoneDto;
import com.example.backend.report.dtos.MilestoneSumUpDto;
import com.example.backend.user.User;

import java.util.Optional;

public class PredictService {
    private final MessagesService messagesService;

    public PredictService() {
        this.messagesService = new MessagesService();
    }

    public MilestoneSumUpDto messagePrediction(User user, Addiction addiction, Long daysClean) {
        Optional<String> predictedMsg = Optional.empty();
        if (addiction.getName().toLowerCase().contains("smoking")) {
            predictedMsg = PredictMicroserviceController.predictLungCancer(user, daysClean);
        }

        MilestoneDto archivedMilestone = messagesService.archivedMilestone(addiction.getName(), daysClean);
        MilestoneDto nextMilestone = messagesService.nextMilestone(addiction.getName(), daysClean);
        return new MilestoneSumUpDto(archivedMilestone, nextMilestone, predictedMsg);
    }
}
