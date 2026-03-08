package com.example.correction.repository;

import com.example.correction.model.Candidat;
import com.example.correction.model.Matiere;
import com.example.correction.model.NoteFinal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteFinalRepository extends JpaRepository<NoteFinal, Long> {
    NoteFinal findByMatiereAndCandidat(Matiere matiere, Candidat candidat);
}
