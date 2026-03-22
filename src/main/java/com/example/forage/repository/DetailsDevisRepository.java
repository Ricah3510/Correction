package com.example.forage.repository;

import com.example.forage.model.DetailsDevis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailsDevisRepository extends JpaRepository<DetailsDevis, Integer> {

    List<DetailsDevis> findByDevisId(Integer devisId);

}