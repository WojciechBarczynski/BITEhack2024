package com.example.backend.report;

import com.example.backend.friend.FriendRelationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final FriendRelationRepository friendRelationRepository;

    public ReportService(ReportRepository reportRepository, FriendRelationRepository friendRelationRepository){
        this.reportRepository = reportRepository;
        this.friendRelationRepository = friendRelationRepository;
    }

    public void addReport(int friendId, int addictId, int addictionId, String postContent){
        var friendRelation = friendRelationRepository
                .findByAddict_IdAndFriend_IdAndAddiction_Id(addictId, friendId, addictionId);
        if(friendRelation == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Such a relation does not exist");
        }

        var report = new Report(friendRelation, new Date(), postContent);
        reportRepository.save(report);
    }

    public List<Report> getReportsForAddiction(int userId, int addictionId){
        return reportRepository.findAllByRelation_Addict_IdAndRelation_Addiction_Id(userId, addictionId);
    }
}
