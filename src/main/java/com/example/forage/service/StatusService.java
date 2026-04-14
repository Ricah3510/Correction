package com.example.forage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.forage.model.Status;
import com.example.forage.repository.StatusRepository;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public Status findById(Integer id){
        return statusRepository.findById(id).orElse(null);
    }

    public Status save(String libelle) {

        Status s = new Status();
        s.setLibelle(libelle);

        return statusRepository.save(s);
    }

    public Status findByLibelle(String libelle) {
        return statusRepository.findByLibelle(libelle);
    }
}