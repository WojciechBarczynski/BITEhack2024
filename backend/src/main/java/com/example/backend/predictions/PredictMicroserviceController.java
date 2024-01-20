package com.example.backend.predictions;

import com.example.backend.report.Report;
import com.example.backend.user.User;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PredictMicroserviceController {
    public static Optional<String> predictLungCancer(User user, Period cleanFor) {
        HttpClient client = HttpClient.newHttpClient();
        var age = Year.now().getValue() - user.getBirthyear();
        URI url = null;
        try {
            url = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost:5000")
                    .setPath("/predictions/lungCancer")
                    .setParameter("age", String.valueOf(age))
                    .setParameter("weight", String.valueOf(user.getWeightKg()))
                    .setParameter("height", String.valueOf(user.getHeightCm()))
                    .setParameter("cleanFor", String.valueOf(cleanFor.getDays()))
                    .build();
        } catch (URISyntaxException e) {
            return Optional.empty();
        }

        var request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        try {
            return Optional.of(client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .get(5, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return Optional.empty();
        }
    }
}
