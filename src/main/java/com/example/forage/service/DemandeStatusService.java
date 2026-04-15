package com.example.forage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<DemandeStatus> getHistoriqueStatus(Integer demandeId){
        return repo.findByDemandeIdOrderByDateDesc(demandeId);
    }

    @Transactional
    public void updateObservation(Integer id, String observation) {

        DemandeStatus ds = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    
        ds.setObservation(observation);
    }
    
}