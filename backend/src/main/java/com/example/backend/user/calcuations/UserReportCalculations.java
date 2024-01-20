package com.example.backend.user.calcuations;

import com.example.backend.addiction.Addiction;
import com.example.backend.addiction.dtos.UserAddictionDto;
import com.example.backend.report.Report;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserReportCalculations {
    public static List<UserAddictionDto> userAddictionDtos(List<Addiction> addictions, List<Report> reports) {
        var daysCleanPerAddiction = new HashMap<Addiction, Long>();
        for (Report report : reports) {
            var addition = report.getRelation().getAddiction();
            var daysClean = Duration.between(report.getReportTime().toInstant(), report.getReportTime().toInstant()).toDays();
            var lastBest = daysCleanPerAddiction.get(addition);
            if (lastBest != null) {
                if (lastBest > daysClean) {
                    daysCleanPerAddiction.put(addition, daysClean);
                }
            } else {
                daysCleanPerAddiction.put(addition, daysClean);
            }
        }

        return addictions.stream().map(addiction -> {
            Optional<Long> clearFor = Optional.empty();
            var daysClean = daysCleanPerAddiction.get(addiction);
            if (daysClean != null) {
                clearFor = Optional.of(daysClean);
            }
            return new UserAddictionDto(addiction.getId(), addiction.getName(), clearFor);
        }).toList();
    }
}
