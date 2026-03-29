package com.example.forage.service;

import org.springframework.stereotype.Service;

import com.example.forage.model.DemandeStatus;
import com.example.forage.repository.DemandeStatusRepository;

@Service
public class DemandeStatusService {

    private final DemandeStatusRepository repo;



    public DemandeStatusService(DemandeStatusRepository repo) {
        this.repo = repo;

    }

    public DemandeStatus getCurrentStatus(Integer demandeId) {
        return repo.findTopByDemandeIdOrderByDateDesc(demandeId);
    }

    
}