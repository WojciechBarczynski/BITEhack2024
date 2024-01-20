package com.example.backend.predictions;

import com.example.backend.predictions.dtos.QuitMessage;
import com.example.backend.report.dtos.MilestoneDto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.tools.javac.Main;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessagesService {
    List<QuitMessage> alcoholMessages;
    List<QuitMessage> cigarettesMessages;

    public MessagesService() {
        URI alcoholUri = null;
        URI cigarettesUri = null;
        try {
            alcoholUri = Objects.requireNonNull(Main.class.getClassLoader().getResource("alcohol_info.csv")).toURI();
            cigarettesUri = Objects.requireNonNull(Main.class.getClassLoader().getResource("cigarettes_info.csv")).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        alcoholMessages = getMessages(alcoholUri);
        cigarettesMessages = getMessages(cigarettesUri);
    }

    private static List<QuitMessage> getMessages(URI uri) {
        List<QuitMessage> messages = new ArrayList<>();
        Path path = Paths.get(uri);

        try (CSVReader reader = new CSVReader(new FileReader(path.toFile()))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                messages.add(new QuitMessage(Long.parseLong(line[0]), line[1]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return messages;
    }

    private static MilestoneDto archivedMilestone(List<QuitMessage> messages, Long daysClean) {
        QuitMessage quitMessage = new QuitMessage(0L, "You just started, don't give up!");
        for (QuitMessage msg : messages) {
            if (msg.days() > daysClean) {
                break;
            }
            quitMessage = msg;
        }
        return new MilestoneDto(quitMessage.days(), quitMessage.msg());
    }

    private static MilestoneDto nextMilestone(List<QuitMessage> messages, Long daysClean) {
        QuitMessage quitMessage = messages.get(0);
        for (QuitMessage msg : messages) {
            quitMessage = msg;
            if (msg.days() > daysClean) {
                break;
            }
        }
        return new MilestoneDto(quitMessage.days(), quitMessage.msg());
    }

    public MilestoneDto archivedMilestone(String addictionName, Long daysClean) {
        if (addictionName.contains("alcohol")) {
            return archivedMilestone(alcoholMessages, daysClean);
        }
        if (addictionName.contains("cigarettes")) {
            return archivedMilestone(cigarettesMessages, daysClean);
        }

        return new MilestoneDto(daysClean, "You're clean for " + daysClean + " days");
    }

    public MilestoneDto nextMilestone(String addictionName, Long daysClean) {
        if (addictionName.contains("alcohol")) {
            return nextMilestone(alcoholMessages, daysClean);
        }
        if (addictionName.contains("cigarettes")) {
            return nextMilestone(cigarettesMessages, daysClean);
        }

        return new MilestoneDto(daysClean, "You can be clean for " + (daysClean + 5) + " days");
    }
}
