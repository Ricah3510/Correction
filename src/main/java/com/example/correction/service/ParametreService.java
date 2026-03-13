package com.example.correction.service;

import java.util.ArrayList;
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
    private final MatiereService matiereService;
    private final OperateurService operateurService;

    public ParametreService(ParametreRepository repository,
                            ResolutionService resolutionService,
                            MatiereService matiereService,
                            OperateurService operateurService) {
        this.repository = repository;
        this.resolutionService = resolutionService;
        this.matiereService = matiereService;
        this.operateurService = operateurService;
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

    Parametre getParametre0(Matiere matiere, double diff){
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

    Parametre getParametre(Matiere matiere, double diff){
        List<Parametre> paramsPossible = getParametrePossible(matiere, diff);
        return getBestParametre(paramsPossible, diff);
    }
    List<Parametre> getParametrePossible(Matiere matiere, double diff){
        List<Parametre> retour = new ArrayList<>();
        List<Parametre> params = repository.findByMatiereOrderByDiffAsc(matiere);
        for (Parametre p : params) {
            double seuil = p.getDiff();
            String operateur = p.getOperateur().getOperateur();
            if (Other.eval(diff, operateur, seuil)) {
                retour.add(p);
            }
        }
        return retour;
    }
    Parametre getBestParametre(List<Parametre> parametres, double diff){
        Parametre bestPamatre = parametres.get(0);
        for(Parametre p : parametres){
            double distanceActuelle = Math.abs(diff - p.getDiff());
            double distanceMeilleur = Math.abs(diff - bestPamatre.getDiff());
            if(distanceActuelle < distanceMeilleur){
                bestPamatre = p;
            }
        }
        return bestPamatre;
    }
    public void save(Long id, Long matiereId, Long operateurId, double diff, Long resolutionId){

        Parametre p;
    
        if(id != null){
            p = repository.findById(id).get();
        }else{
            p = new Parametre();
        }
    
        p.setMatiere(matiereService.findById(matiereId));
        p.setOperateur(operateurService.findById(operateurId));
        p.setResolution(resolutionService.findById(resolutionId));
        p.setDiff(diff);
    
        repository.save(p);
    }
}