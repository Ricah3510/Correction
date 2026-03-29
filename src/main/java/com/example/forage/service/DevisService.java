package com.example.forage.service;

import com.example.forage.model.Demande;
import com.example.forage.model.DetailsDevis;
import com.example.forage.model.Devis;
import com.example.forage.model.Type;
import com.example.forage.repository.DemandeRepository;
import com.example.forage.repository.DetailsDevisRepository;
import com.example.forage.repository.DevisRepository;
import com.example.forage.repository.TypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DevisService {

    private final DevisRepository devisRepository;
    private final DemandeRepository demandeRepository;
    private final TypeRepository typeRepository;
    private final DetailsDevisRepository detailsDevisRepository;
    private final DetailsDevisService detailsDevisService;
    private final DemandeService demandeService;


    public DevisService(DevisRepository devisRepository,
                        DetailsDevisRepository detailsRepo,
                        DetailsDevisService detailsService,
                        DemandeRepository demandeRepository,
                        DemandeService demandeService,
                        TypeRepository typeRepository) {

        this.devisRepository = devisRepository;
        this.demandeRepository = demandeRepository;
        this.typeRepository = typeRepository;
        this.detailsDevisRepository = detailsRepo;
        this.detailsDevisService = detailsService;
        this.demandeService = demandeService;
    }

    public Devis create(Integer demandeId, Integer typeId) {

        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));

        Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Type not found"));

        Devis devis = new Devis();
        devis.setDemande(demande);
        devis.setType(type);
        devis.setDate(new Date());
        // devis.setMontantTotal(BigDecimal.ZERO);

        return devisRepository.save(devis);
    }

    public List<Devis> findAll() {
        return devisRepository.findAll();
    }

    public Devis findById(Integer id) {
        return devisRepository.findById(id).orElse(null);
    }

    public List<Devis> findByDemande(Integer demandeId) {
        return devisRepository.findByDemandeId(demandeId);
    }

    public void delete(Integer id) {
        devisRepository.deleteById(id);
    }

    public List<Devis> findByDemandeAndType(Integer demandeId, Integer typeId) {

        List<Devis> list = devisRepository.findByDemandeIdAndTypeId(demandeId, typeId);
    
        list.forEach(d -> d.getDetailsDeviss().size());
    
        return list;
    }

    @Transactional
    public Devis createDevisWithDetails0(
            Integer demandeId,
            Integer typeId,
            String[] libelles,
            BigDecimal[] qtes,
            BigDecimal[] pus) {

        Devis devis = create(demandeId, typeId);

        for (int i = 0; i < libelles.length; i++) {

            if (libelles[i] == null || libelles[i].isEmpty()) continue;
            if (qtes[i] == null || pus[i] == null) continue;

            DetailsDevis d = new DetailsDevis();
            d.setDevis(devis);
            d.setLibelle(libelles[i]);
            d.setQte(qtes[i]);
            d.setPu(pus[i]);

            detailsDevisRepository.save(d);
        }

        detailsDevisService.updateTotal(devis.getId());

        return devis;
    }

    @Transactional
    public Devis createDevisWithDetails(
            Integer demandeId,
            Integer typeId,
            String[] libelles,
            BigDecimal[] qtes,
            BigDecimal[] pus) {


        if (libelles == null || qtes == null || pus == null ||
            libelles.length != qtes.length || qtes.length != pus.length) {
            throw new IllegalArgumentException("Données invalides");
        }

        Devis devis = create(demandeId, typeId);

        BigDecimal total = BigDecimal.ZERO;


        for (int i = 0; i < libelles.length; i++) {

            if (libelles[i] == null || libelles[i].trim().isEmpty()) continue;
            if (qtes[i] == null || pus[i] == null) continue;

            DetailsDevis d = new DetailsDevis();
            d.setDevis(devis);
            d.setLibelle(libelles[i]);
            d.setQte(qtes[i]);
            d.setPu(pus[i]);

            detailsDevisRepository.save(d);

            total = total.add(qtes[i].multiply(pus[i]));
        }


        // devis.setMontantTotal(total);
        devisRepository.save(devis);


        String typeLib = devis.getType().getLibelle();

        String statusLibelle = null;

        if (typeLib.equalsIgnoreCase("Etude")) {
            statusLibelle = "Devis etude terminee";
        } else if (typeLib.equalsIgnoreCase("Forage")) {
            statusLibelle = "Devis forage terminee";
        }

        if (statusLibelle != null) {
            demandeService.addStatusByLibelle(demandeId, statusLibelle);
        }

        return devis;
    }
}