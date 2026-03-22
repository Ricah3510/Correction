package com.example.forage.service;

import com.example.forage.model.DemandeStatus;
import com.example.forage.repository.DemandeStatusRepository;
import org.springframework.stereotype.Service;

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