package com.example.correction.service;

import com.example.correction.model.Matiere;
import com.example.correction.repository.MatiereRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereService {

    private final MatiereRepository repository;

    public MatiereService(MatiereRepository repository) {
        this.repository = repository;
    }

    public List<Matiere> findAll(){
        return repository.findAll();
    }

    public Matiere findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Matiere save(Matiere Matiere){
        return repository.save(Matiere);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}