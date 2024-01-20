package com.example.backend.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findAllByRelation_Addict_IdAndRelation_Addiction_Id(int addictId, int addictionId);
}