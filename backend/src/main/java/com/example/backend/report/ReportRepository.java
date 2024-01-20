package com.example.backend.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findAllByRelation_Addict_IdAndRelation_Addiction_Id(int addictId, int addictionId);
    List<Report> findAllByRelation_Addict_Id(int addictId);

    Optional<Report> findTopByRelation_Addict_IdAndRelation_Addiction_IdOrderByReportTimeDesc(int addictId, int addictionId);
}