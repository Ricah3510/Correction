package com.example.correction.repository;

import com.example.correction.model.Candidat;
import com.example.correction.model.Matiere;
import com.example.correction.model.Note;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByMatiereAndCandidat(Matiere matiere, Candidat candidat);
}
