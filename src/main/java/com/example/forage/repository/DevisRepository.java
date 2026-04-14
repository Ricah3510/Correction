package com.example.forage.repository;

import com.example.forage.model.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.List;

public interface DevisRepository extends JpaRepository<Devis, Integer> {

    List<Devis> findByDemandeId(Integer demandeId);
    List<Devis> findByDemandeIdAndTypeId(Integer demandeId, Integer typeId);
    Devis findFirstByDemandeIdAndTypeId(Integer demandeId, Integer typeId);

    @Query("""
        SELECT SUM (d.qte * d.pu) as somme FROM DetailsDevis d
    """)
    BigDecimal getChiffreAffaire();
}