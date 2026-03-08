package com.example.correction.repository;

import com.example.correction.model.Matiere;
import com.example.correction.model.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ParametreRepository extends JpaRepository<Parametre, Long> {
    List<Parametre> findByMatiereOrderByDiffAsc(Matiere matiere);

    // @Query("""
    // SELECT p
    // FROM Parametre p
    // WHERE p.matiere = :matiere
    // AND :diff >= p.diffMin
    // AND :diff < p.diffMax
    // """)
    // Parametre findInterval(Matiere matiere, double diff);
}
