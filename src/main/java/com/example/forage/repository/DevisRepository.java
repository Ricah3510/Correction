package com.example.forage.repository;

import com.example.forage.model.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevisRepository extends JpaRepository<Devis, Integer> {

    List<Devis> findByDemandeId(Integer demandeId);

}