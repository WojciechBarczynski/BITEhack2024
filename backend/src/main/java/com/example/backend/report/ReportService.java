package com.example.backend.report;

import com.example.backend.addiction.AddictionRepository;
import com.example.backend.friend.FriendRelationRepository;
import com.example.backend.report.dtos.RecordsForAddictionDto;
import com.example.backend.report.dtos.ReportDto;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final FriendRelationRepository friendRelationRepository;
    private final AddictionRepository addictionRepository;

    public ReportService(ReportRepository reportRepository,
                         FriendRelationRepository friendRelationRepository,
                         AddictionRepository addictionRepository){
        this.reportRepository = reportRepository;
        this.friendRelationRepository = friendRelationRepository;
        this.addictionRepository = addictionRepository;
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

    public RecordsForAddictionDto getReportsForAddiction(int userId, int addictionId){
        var rawReports =  reportRepository.findAllByRelation_Addict_IdAndRelation_Addiction_Id(userId, addictionId);
        var reports = rawReports
                .stream()
                .map(report -> new ReportDto(report.getRelation().getFriend().getNick(),
                        report.getPostContent(), report.getReportTime().toString()))
                .toList();

        var addiction = addictionRepository.findById(addictionId);
        if (addiction.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Addiction with such ID not found");
        }

        return new RecordsForAddictionDto(addiction.get().getName(), reports);
    }

    public Optional<Report> getLastUserReport(int userId, int addictionId) {
        return reportRepository.findTopByRelation_Addict_IdAndRelation_Addiction_IdOrderByReportTimeDesc(userId, addictionId);
    }
}
