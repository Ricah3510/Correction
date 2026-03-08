package com.example.correction.service;

import com.example.correction.model.Correcteur;
import com.example.correction.repository.CorrecteurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorrecteurService {

    private final CorrecteurRepository repository;

    public CorrecteurService(CorrecteurRepository repository) {
        this.repository = repository;
    }

    public List<Correcteur> findAll(){
        return repository.findAll();
    }

    public Correcteur findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Correcteur save(Correcteur correcteur){
        return repository.save(correcteur);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}