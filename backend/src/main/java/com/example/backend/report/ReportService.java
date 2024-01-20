package com.example.backend.report;

import com.example.backend.addiction.Addiction;
import com.example.backend.addiction.AddictionRepository;
import com.example.backend.friend.FriendRelationRepository;
import com.example.backend.predictions.PredictService;
import com.example.backend.report.dtos.MilestoneSumUpDto;
import com.example.backend.report.dtos.RecordsForAddictionDto;
import com.example.backend.report.dtos.ReportDto;
import com.example.backend.user.User;
import com.example.backend.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final FriendRelationRepository friendRelationRepository;
    private final AddictionRepository addictionRepository;
    private final UserRepository userRepository;
    private final PredictService predictService;

    public ReportService(ReportRepository reportRepository,
                         FriendRelationRepository friendRelationRepository,
                         AddictionRepository addictionRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.friendRelationRepository = friendRelationRepository;
        this.addictionRepository = addictionRepository;
        this.userRepository = userRepository;
        this.predictService = new PredictService();
    }

    public void addReport(int friendId, int addictId, int addictionId, String postContent) {
        var friendRelation = friendRelationRepository
                .findByAddict_IdAndFriend_IdAndAddiction_Id(addictId, friendId, addictionId);
        if (friendRelation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Such a relation does not exist");
        }

        var report = new Report(friendRelation, new Date(), postContent);
        reportRepository.save(report);
    }

    public RecordsForAddictionDto getReportsForAddiction(int userId, int addictionId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Addiction> addiction = addictionRepository.findById(addictionId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with such ID not found");
        }
        if (addiction.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Addiction with such ID not found");
        }

        var rawReports = reportRepository.findAllByRelation_Addict_IdAndRelation_Addiction_Id(userId, addictionId);
        var reports = rawReports
                .stream()
                .map(report -> new ReportDto(report.getRelation().getFriend().getNick(),
                        report.getPostContent(), report.getReportTime().toString()))
                .toList();
        Long daysClean = daysClean(getLastUserReport(userId, addictionId));
        MilestoneSumUpDto sumUpDto = predictService.messagePrediction(user.get(), addiction.get(), daysClean);

        return new RecordsForAddictionDto(addiction.get().getName(), sumUpDto, daysClean, reports);
    }

    public Optional<Report> getLastUserReport(int userId, int addictionId) {
        return reportRepository.findTopByRelation_Addict_IdAndRelation_Addiction_IdOrderByReportTimeDesc(userId, addictionId);
    }

    public List<Report> getUserReports(int userId) {
        return reportRepository.findAllByRelation_Addict_Id(userId);
    }

    private Long daysClean(Optional<Report> lastReport) {
        if (lastReport.isPresent()) {
            Instant lastReportedDate = lastReport.get().getReportTime().toInstant();
            return Duration.between(Instant.now(), lastReportedDate).toDays();
        } else {
            return 1L;
        }
    }
}
