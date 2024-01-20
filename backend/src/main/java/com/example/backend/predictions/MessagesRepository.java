package com.example.backend.predictions;

import com.example.backend.predictions.dtos.QuitMessage;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessagesRepository {
    List<QuitMessage> alcoholMessages;

    public MessagesRepository() {
        alcoholMessages = new ArrayList<>();
        URI uri = null;
        try {
            uri = Objects.requireNonNull(Main.class.getClassLoader().getResource("alcohol_info.csv")).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Path alcoholMessagesPath = Paths.get(uri);

        try (CSVReader reader = new CSVReader(new FileReader(alcoholMessagesPath.toFile()))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                alcoholMessages.add(new QuitMessage(Integer.parseInt(line[0]), line[1]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMessage(String addictionName, Period cleanFor) {
        var name = addictionName.toLowerCase();
        if (name.contains("alcohol")) {
            QuitMessage quitMessage = alcoholMessages.get(0);
            int cleanForDays = cleanFor.getDays();
            for (QuitMessage msg : alcoholMessages) {
                quitMessage = msg;
                if (msg.days() > cleanForDays) {
                    break;
                }

            }

            return "After " + quitMessage.days() + " days without drinking: " + quitMessage.msg();
        }

        return "According to studies, quiting is more probable in supportive environment.";
    }
}
