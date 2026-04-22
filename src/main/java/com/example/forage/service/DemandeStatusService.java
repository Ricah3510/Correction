package com.example.forage.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forage.model.Demande;
import com.example.forage.model.DemandeStatus;
import com.example.forage.repository.DemandeRepository;
import com.example.forage.repository.DemandeStatusRepository;

@Service
public class DemandeStatusService {

    private final DemandeStatusRepository repo;
    private final DemandeRepository demandeRepository;

    public DemandeStatusService(DemandeStatusRepository repo, DemandeRepository demandeRepository) {
        this.repo = repo;
        this.demandeRepository = demandeRepository;

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

    @Transactional
    public void updateDate(Integer id, String dateStr) {

        DemandeStatus ds = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = sdf.parse(dateStr);

            ds.setDate(date);

        } catch (Exception e) {
            throw new RuntimeException("Format date invalide : " + dateStr);
        }
    }
//     public Map<String, Integer> getStatByStatus() {

//     List<Demande> demandes = demandeRepository.findAll();

//     Map<String, Integer> stats = new HashMap<>();

//     for (Demande d : demandes) {

//         DemandeStatus current = getCurrentStatus(d.getId());

//         if (current != null) {
//             String libelle = current.getStatus().getLibelle();

//             stats.put(libelle, stats.getOrDefault(libelle, 0) + 1);
//         }
//     }

//     return stats;
// }

    public List<Demande> findDemandesByCurrentStatusId(Integer statusId) {

        List<Demande> demandes = demandeRepository.findAll();

        List<Demande> result = new ArrayList<>();

        for (Demande d : demandes) {

            DemandeStatus current = repo
                    .findTopByDemandeIdOrderByDateDesc(d.getId());

            if (current != null &&
                current.getStatus().getId().equals(statusId)) {

                result.add(d);
            }
        }

        return result;
    }
}