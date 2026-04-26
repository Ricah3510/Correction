package com.example.forage.service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forage.dto.Util;
import com.example.forage.model.Demande;
import com.example.forage.model.DemandeStatus;
import com.example.forage.model.Parametre;
import com.example.forage.repository.DemandeRepository;
import com.example.forage.repository.DemandeStatusRepository;
import com.example.forage.repository.ParametreRepository;

@Service
public class DemandeStatusService {

    private final DemandeStatusRepository repo;
    private final DemandeRepository demandeRepository;
    private final ParametreRepository parametreRepository;
    private final DemandeStatusRepository demandeStatusRepository;

    public DemandeStatusService(DemandeStatusRepository repo, DemandeRepository demandeRepository, ParametreRepository parametreRepository, DemandeStatusRepository demandeStatusRepository) {
        this.repo = repo;
        this.demandeRepository = demandeRepository;
        this.parametreRepository = parametreRepository;
        this.demandeStatusRepository = demandeStatusRepository;

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
    public void updateDate0(Integer id, String dateStr) {

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

            DemandeStatus current = getCurrentStatus(d.getId());
            // DemandeStatus current = repo
            //         .findTopByDemandeIdOrderByDateDesc(d.getId());

            if (current != null && current.getStatus().getId().equals(statusId)) {

                result.add(d);
            }
        }

        return result;
    }

    @Transactional
    public void updateDate(Integer id, String dateStr) {

        DemandeStatus current = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date newDate = sdf.parse(dateStr);

            current.setDate(newDate);

            Integer demandeId = current.getDemande().getId();

            DemandeStatus prev = demandeStatusRepository
                    .findTopByDemandeIdAndDateLessThanOrderByDateDesc(demandeId, newDate);

            DemandeStatus next = demandeStatusRepository
                    .findTopByDemandeIdAndDateGreaterThanOrderByDateAsc(demandeId, newDate);

            Parametre p = parametreRepository.findById(1).orElseThrow();


            if (prev != null) {

                LocalDateTime prevDate = Util.toLocalDateTime(prev.getDate());
                LocalDateTime currDate = Util.toLocalDateTime(current.getDate());

                Duration plein = Duration.between(prevDate, currDate);
                prev.setDureePlein(plein);

                Duration ouvert = Util.calculDureeOuvree(prevDate, currDate, p);
                prev.setDureeOuvert(ouvert);
            }


            if (next != null) {

                LocalDateTime currDate = Util.toLocalDateTime(current.getDate());
                LocalDateTime nextDate = Util.toLocalDateTime(next.getDate());

                Duration plein = Duration.between(currDate, nextDate);
                current.setDureePlein(plein);

                Duration ouvert = Util.calculDureeOuvree(currDate, nextDate, p);
                current.setDureeOuvert(ouvert);

            } else {
                current.setDureePlein(null);
                current.setDureeOuvert(null);
            }

        } catch (Exception e) {
            throw new RuntimeException("Format date invalide : " + dateStr);
        }
    }
}