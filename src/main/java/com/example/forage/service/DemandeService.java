package com.example.forage.service;

import com.example.forage.dto.Util;
import com.example.forage.model.*;
import com.example.forage.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class DemandeService {

    private final DemandeRepository demandeRepository;
    private final ClientRepository clientRepository;
    private final DemandeStatusRepository demandeStatusRepository;
    private final StatusRepository statusRepository;
    private final ParametreRepository parametreRepository;

    public DemandeService(DemandeRepository demandeRepository,
                          ClientRepository clientRepository,
                          DemandeStatusRepository demandeStatusRepository,
                          StatusRepository statusRepository,
                          ParametreRepository parametreRepository) {
        this.demandeRepository = demandeRepository;
        this.clientRepository = clientRepository;
        this.demandeStatusRepository = demandeStatusRepository;
        this.statusRepository = statusRepository;
        this.parametreRepository = parametreRepository;
    }

    @Transactional
    public Demande save(Demande demande, Integer clientId) {
    
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    
        demande.setClient(client);
    
        Demande savedDemande = demandeRepository.save(demande);
    
        Status statusInitial = statusRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(savedDemande);
        ds.setStatus(statusInitial);
        ds.setDate(new Date());
        ds.setDureePlein(null);
        ds.setDureeOuvert(null);
        demandeStatusRepository.save(ds);
    
        return savedDemande;
    }

    public List<Demande> findAll() {
        return demandeRepository.findAll();
    }

    public Demande findById(Integer id) {
        return demandeRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        demandeRepository.deleteById(id);
    }

    public List<Demande> findByClient(Integer clientId) {
        return demandeRepository.findByClientId(clientId);
    }

    @Transactional
    public void addStatus(Integer demandeId, Integer statusId) {
    
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));
    
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    
        Date now = new Date();
    
        updateAllDuree(demandeId, now);
    
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demande);
        ds.setStatus(status);
        ds.setDate(now);
    
        demandeStatusRepository.save(ds);
    }

    public Demande update(Demande demande) {
        return demandeRepository.save(demande);
    }

    @Transactional
    public void addStatusByLibelle(Integer demandeId, String libelle, String observation) {
    
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));
    
        Status status = statusRepository.findByLibelle(libelle);
    
        Date now = new Date();
    
        updateAllDuree(demandeId, now);
    
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demande);
        ds.setStatus(status);
        ds.setObservation(observation);
        ds.setDate(now);
    
        demandeStatusRepository.save(ds);
    }

    @Transactional
    public void addStatusByLibelle(Integer demandeId, String libelle) {
    
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));
    
        Status status = statusRepository.findByLibelle(libelle);
    
        if (status == null) {
            throw new RuntimeException("Status not found : " + libelle);
        }
    
        DemandeStatus last = demandeStatusRepository
                .findTopByDemandeIdOrderByDateDesc(demandeId);
    
        if (last != null && last.getStatus().getId().equals(status.getId())) {
            return;
        }
    
        Date now = new Date();
    
        updateAllDuree(demandeId, now);

        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demande);
        ds.setStatus(status);
        ds.setDate(now);
        
        demandeStatusRepository.save(ds);
    }

    public DemandeStatus getCurrentStatus(Integer id) {
        return demandeStatusRepository.findTopByDemandeIdOrderByDateDesc(id);
        // return demandeStatusRepository.findTopByDemandeIdOrderByIdDesc(id);
    }

    private void updateAllDuree(Integer demandeId, Date nowDate) {
        updateDureePlein(demandeId, nowDate);
        updateDureeOuvert(demandeId, nowDate);
    }
    private void updateDureePlein(Integer demandeId, Date nowDate) {

        DemandeStatus last = getCurrentStatus(demandeId);
    
        if (last != null && last.getDate() != null) {
    
            java.time.LocalDateTime lastDate = last.getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
    
            java.time.LocalDateTime now = nowDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
    
            Duration duration = Duration.between(lastDate, now);
            last.setDureePlein(duration);
        }
    }

    private void updateDureeOuvert(Integer demandeId, Date nowDate) {

        DemandeStatus last = getCurrentStatus(demandeId);
    
        if (last != null && last.getDate() != null) {
    
            java.time.LocalDateTime lastDate = last.getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
    
            java.time.LocalDateTime now = nowDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
    
            Parametre p = parametreRepository.findById(1).orElseThrow();
            Duration dureeOuvert = Util.calculDureeOuvree(lastDate, now, p);

            last.setDureeOuvert(dureeOuvert);
        }
    }

    // public Duration calculDureeOuvree(LocalDateTime start, LocalDateTime end, Parametre p) {

    //     if (start == null || end == null) return Duration.ZERO;

    //     LocalTime debutTravail = p.getHeureDebut(); // 08:00
    //     LocalTime finTravail = p.getHeureFin();     // 17:00

    //     Duration total = Duration.ZERO;

    //     LocalDate currentDate = start.toLocalDate();
    //     LocalDate endDate = end.toLocalDate();

    //     while (!currentDate.isAfter(endDate)) {

    //         LocalDateTime debutJour = LocalDateTime.of(currentDate, debutTravail);
    //         LocalDateTime finJour = LocalDateTime.of(currentDate, finTravail);

    //         LocalDateTime realStart = start.isAfter(debutJour) ? start : debutJour;
    //         LocalDateTime realEnd = end.isBefore(finJour) ? end : finJour;

    //         if (realStart.isBefore(realEnd)) {
    //             total = total.plus(Duration.between(realStart, realEnd));
    //         }

    //         currentDate = currentDate.plusDays(1);

    //         start = LocalDateTime.of(currentDate, debutTravail);
    //     }

    //     return total;
    // }

}