package com.example.correction.service;

import com.example.correction.model.Operateur;
import com.example.correction.repository.OperateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateurService {

    private final OperateurRepository repository;

    public OperateurService(OperateurRepository repository) {
        this.repository = repository;
    }

    public List<Operateur> findAll(){
        return repository.findAll();
    }

    public Operateur findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Operateur save(Operateur Operateur){
        return repository.save(Operateur);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}