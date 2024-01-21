package com.example.backend.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// Most of those methods are to prevent N+1
@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findAllByRelation_Addict_IdAndRelation_Addiction_Id(int addictId, int addictionId);

    List<Report> findAllByRelation_Addict_Id(int addictId);

    Optional<Report> findTopByRelation_Addict_IdAndRelation_Addiction_IdOrderByReportTimeDesc(int addictId, int addictionId);

    @Query("SELECT r.reportTime " +
            "FROM Report r " +
            "INNER JOIN FriendRelation fr ON fr.id = r.relation.id " +
            "WHERE fr.addict.id = ?1 AND fr.addiction.id = ?2")
    List<Date> findAllDatesByAddictIdAndAddictionId(int addictId, int addictionId);
}