package com.example.forage.controller;

import com.example.forage.model.Demande;
import com.example.forage.model.DemandeStatus;
import com.example.forage.model.Devis;
import com.example.forage.service.ClientService;
import com.example.forage.service.DemandeService;
import com.example.forage.service.DemandeStatusService;
import com.example.forage.service.DevisService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ApiController {

    private final DemandeService demandeService;
    private final DevisService devisService;

    public ApiController(DemandeService demandeService, DevisService devisService) {

        this.demandeService = demandeService;
        this.devisService = devisService;
    }

    @GetMapping("/api/demande/{id}")
    @ResponseBody
    public Demande getDemande(@PathVariable Integer id) {
        return demandeService.findById(id);
    }

    @GetMapping("/api/devis")
    @ResponseBody
    public Devis getDevis(
            @RequestParam Integer demande,
            @RequestParam Integer type) {

                Devis devis = devisService.findByDemandeAndTypeUnique(demande, type);

                if (devis == null) {
                    return new Devis();
                }
            
                return devis;
    }

    @GetMapping("/api/devis/status")
    @ResponseBody
    public Map<String, Object> getDevisStatus(
            @RequestParam Integer demande,
            @RequestParam Integer type) {

        Map<String, Object> response = new HashMap<>();

        Devis devis = devisService.findByDemandeAndTypeUnique(demande, type);

        boolean isAccepted = false;

        if (devis != null) {

            DemandeStatus ds = demandeService
                    .getCurrentStatus(devis.getDemande().getId());

            if (ds != null &&
                ds.getStatus().getLibelle().toLowerCase().contains("acceptee")) {

                isAccepted = true;
            }
        }

        response.put("devis", devis);
        response.put("isAccepted", isAccepted);

        return response;
    }
}