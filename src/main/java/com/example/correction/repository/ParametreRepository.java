package com.example.correction.repository;

import com.example.correction.model.Matiere;
import com.example.correction.model.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ParametreRepository extends JpaRepository<Parametre, Long> {
    List<Parametre> findByMatiere(Matiere matiere);
}
