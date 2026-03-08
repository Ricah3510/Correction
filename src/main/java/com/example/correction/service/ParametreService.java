package com.example.correction.service;

import com.example.correction.model.Parametre;
import com.example.correction.repository.ParametreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametreService {

    private final ParametreRepository repository;

    public ParametreService(ParametreRepository repository) {
        this.repository = repository;
    }

    public List<Parametre> findAll(){
        return repository.findAll();
    }

    public Parametre findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Parametre save(Parametre Parametre){
        return repository.save(Parametre);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}