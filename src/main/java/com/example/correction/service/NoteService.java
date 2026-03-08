package com.example.correction.service;

import com.example.correction.model.Note;
import com.example.correction.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> findAll(){
        return repository.findAll();
    }

    public Note findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Note save(Note Note){
        return repository.save(Note);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}