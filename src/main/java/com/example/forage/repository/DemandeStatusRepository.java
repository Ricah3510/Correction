package com.example.forage.repository;

import com.example.forage.model.DemandeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeStatusRepository extends JpaRepository<DemandeStatus, Integer> {

    List<DemandeStatus> findByDemandeIdOrderByDateDesc(Integer demandeId);
    DemandeStatus findTopByDemandeIdOrderByDateDesc(Integer demandeId);
    DemandeStatus findTopByDemandeIdOrderByIdDesc(Integer demandeId);
}