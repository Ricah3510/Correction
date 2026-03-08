package com.example.correction.repository;

import com.example.correction.model.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResolutionRepository extends JpaRepository<Resolution, Long> {
    Resolution findByNom(String nom);;
}
