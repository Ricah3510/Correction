package com.example.forage.service;

import com.example.forage.model.DetailsDevis;
import com.example.forage.model.Devis;
import com.example.forage.repository.DetailsDevisRepository;
import com.example.forage.repository.DevisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class DetailsDevisService {

    private final DetailsDevisRepository detailsRepo;
    private final DevisRepository devisRepo;

    public DetailsDevisService(DetailsDevisRepository detailsRepo,
                               DevisRepository devisRepo) {
        this.detailsRepo = detailsRepo;
        this.devisRepo = devisRepo;
    }

    public DetailsDevis addDetail(Integer devisId, String libelle, BigDecimal qte, BigDecimal pu) {

        Devis devis = devisRepo.findById(devisId)
                .orElseThrow(() -> new RuntimeException("Devis not found"));

        DetailsDevis d = new DetailsDevis();
        d.setDevis(devis);
        d.setLibelle(libelle);
        d.setQte(qte);
        d.setPu(pu);

        DetailsDevis saved = detailsRepo.save(d);

        updateTotal(devisId);

        return saved;
    }

    public void delete(Integer id) {

        DetailsDevis d = detailsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Detail not found"));

        Integer devisId = d.getDevis().getId();

        detailsRepo.deleteById(id);

        updateTotal(devisId);
    }

    public void updateTotal(Integer devisId) {

        Devis devis = devisRepo.findById(devisId)
                .orElseThrow(() -> new RuntimeException("Devis not found"));

        BigDecimal total = BigDecimal.ZERO;

        for (DetailsDevis d : detailsRepo.findByDevisId(devisId)) {
            BigDecimal montant = d.getQte().multiply(d.getPu());
            total = total.add(montant);
        }

        // devis.setMontantTotal(total);

        devisRepo.save(devis);
    }
}