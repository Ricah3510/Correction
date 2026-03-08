package com.example.correction.service;

import com.example.correction.model.Resolution;
import com.example.correction.repository.ResolutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResolutionService {

    private final ResolutionRepository repository;

    public ResolutionService(ResolutionRepository repository) {
        this.repository = repository;
    }

    public List<Resolution> findAll(){
        return repository.findAll();
    }

    public Resolution findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Resolution save(Resolution Resolution){
        return repository.save(Resolution);
    }

    public Resolution findByNom(String nom){
        return repository.findByNom(nom);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}