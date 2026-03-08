package com.example.correction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.correction.dto.Other;
import com.example.correction.model.Matiere;
import com.example.correction.model.Parametre;
import com.example.correction.repository.ParametreRepository;

@Service
public class ParametreService {

    private final ParametreRepository repository;

    private final ResolutionService resolutionService;

    public ParametreService(ParametreRepository repository,
                            ResolutionService resolutionService) {
        this.repository = repository;
        this.resolutionService = resolutionService;
    }

    public List<Parametre> findAll(){
        return repository.findAll();
    }

    public Parametre findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Parametre save(Parametre Parametre){
        return repository.save(Parametre);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    Parametre getParametreInit(Matiere matiere, double diff){

        if (diff == 0) {
            Parametre defaut = new Parametre();
            defaut.setResolution(resolutionService.findByNom("moyenne"));
            return defaut;
        }

        List<Parametre> params = repository.findByMatiereOrderByDiffAsc(matiere);
    
        for (Parametre p : params) {
    
            double seuil = p.getDiff();
            String operateur = p.getOperateur().getOperateur();
    
            if (Other.eval(diff, operateur, seuil)) {
                return p;
            }
        }

        Parametre defaut = new Parametre();
        defaut.setResolution(resolutionService.findByNom("moyenne"));

        return defaut;
    }

    Parametre getParametre(Matiere matiere, double diff){
        if (diff == 0) {
            Parametre defaut = new Parametre();
            defaut.setResolution(resolutionService.findByNom("moyenne"));
            return defaut;
        }
        List<Parametre> params = repository.findByMatiereOrderByDiffAsc(matiere);
        Parametre meilleur = null;
        for (Parametre p : params) {
            double seuil = p.getDiff();
            String operateur = p.getOperateur().getOperateur();
            if (Other.eval(diff, operateur, seuil)) {
                if (meilleur == null) {
                    meilleur = p;
                } else {
                    double distanceActuelle = Math.abs(diff - seuil);
                    double distanceMeilleur = Math.abs(diff - meilleur.getDiff());
                    if (distanceActuelle < distanceMeilleur) {
                        meilleur = p;
                    }
                }
            }
        }
        if (meilleur != null) {
            return meilleur;
        }
        Parametre defaut = new Parametre();
        defaut.setResolution(resolutionService.findByNom("moyenne"));
        return defaut;
    }
}