package com.example.correction.service;

import com.example.correction.model.Candidat;
import com.example.correction.repository.CandidatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatService {

    private final CandidatRepository repository;

    public CandidatService(CandidatRepository repository) {
        this.repository = repository;
    }

    public List<Candidat> findAll(){
        return repository.findAll();
    }

    public Candidat findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Candidat save(Candidat Candidat){
        return repository.save(Candidat);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}