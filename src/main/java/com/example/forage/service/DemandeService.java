package com.example.forage.service;

import com.example.forage.model.*;
import com.example.forage.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DemandeService {

    private final DemandeRepository demandeRepository;
    private final ClientRepository clientRepository;
    private final DemandeStatusRepository demandeStatusRepository;
    private final StatusRepository statusRepository;

    public DemandeService(DemandeRepository demandeRepository,
                          ClientRepository clientRepository,
                          DemandeStatusRepository demandeStatusRepository,
                          StatusRepository statusRepository) {
        this.demandeRepository = demandeRepository;
        this.clientRepository = clientRepository;
        this.demandeStatusRepository = demandeStatusRepository;
        this.statusRepository = statusRepository;
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

    public void addStatus(Integer demandeId, Integer statusId) {

        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));

        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));

        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demande);
        ds.setStatus(status);
        ds.setDate(new Date());

        demandeStatusRepository.save(ds);
    }

    public Demande update(Demande demande) {
        return demandeRepository.save(demande);
    }

    public void addStatusByLibelle(Integer demandeId, String libelle, String observation) {

        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));
    
        Status status = statusRepository.findByLibelle(libelle);
    
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demande);
        ds.setStatus(status);
        ds.setObservation(observation);
        ds.setDate(new Date());
    
        demandeStatusRepository.save(ds);
    }

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
    
        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demande);
        ds.setStatus(status);
        ds.setDate(new Date());
    
        demandeStatusRepository.save(ds);
    }

    public DemandeStatus getCurrentStatus(Integer id) {
        return demandeStatusRepository.findTopByDemandeIdOrderByDateDesc(id);
    }
}