package com.example.correction.service;

import com.example.correction.model.NoteFinal;
import com.example.correction.repository.NoteFinalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteFinalService {

    private final NoteFinalRepository repository;

    public NoteFinalService(NoteFinalRepository repository) {
        this.repository = repository;
    }

    public List<NoteFinal> findAll(){
        return repository.findAll();
    }

    public NoteFinal findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public NoteFinal save(NoteFinal NoteFinal){
        return repository.save(NoteFinal);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}