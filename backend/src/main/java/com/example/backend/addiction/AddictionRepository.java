package com.example.backend.addiction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddictionRepository extends JpaRepository<Addiction, Integer> {
    Addiction findByName(String name);
}