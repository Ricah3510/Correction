package com.example.forage.controller;

import com.example.forage.model.DemandeStatus;
import com.example.forage.model.Devis;
import com.example.forage.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DevisController {

    private final DemandeService demandeService;
    private final TypeService typeService;
    private final DevisService devisService;
    private final DetailsDevisService detailsService;

    public DevisController(
            DemandeService demandeService,
            TypeService typeService,
            DevisService devisService,
            DetailsDevisService detailsService) {

        this.demandeService = demandeService;
        this.typeService = typeService;
        this.devisService = devisService;
        this.detailsService = detailsService;
    }

    @GetMapping("/devis/ca")
    public ModelAndView pageDevisCA() {

        ModelAndView mv = new ModelAndView("devis/ca");

        mv.addObject("ca", devisService.getCA());
        return mv;
    }

    @GetMapping("/devis")
    public ModelAndView pageDevis() {

        ModelAndView mv = new ModelAndView("devis/devis");

        mv.addObject("demandes", demandeService.findAll());
        mv.addObject("types", typeService.findAll());

        return mv;
    }


    @PostMapping("/devis0")
    public ModelAndView saveDevis0(
            @RequestParam Integer demande,
            @RequestParam Integer type,
            @RequestParam("libelle[]") String[] libelles,
            @RequestParam("qte[]") BigDecimal[] qtes,
            @RequestParam("pu[]") BigDecimal[] pus) {

                ModelAndView mv = new ModelAndView("devis/devis");

                try {
            
                    devisService.createDevisWithDetails(demande, type, libelles, qtes, pus);
            
                    return new ModelAndView("redirect:/devis");
            
                } catch (Exception e) {
            
                    mv.addObject("error", e.getMessage());
            
                    mv.addObject("demandes", demandeService.findAll());
                    mv.addObject("types", typeService.findAll());
            
                    return mv;
                }
    }

    @PostMapping("/devis")
    public ModelAndView saveDevis(
            @RequestParam Integer demande,
            @RequestParam Integer type,
            @RequestParam("detailId[]") Integer[] ids,
            @RequestParam("libelle[]") String[] libelles,
            @RequestParam("qte[]") BigDecimal[] qtes,
            @RequestParam("pu[]") BigDecimal[] pus) {
            ModelAndView mv = new ModelAndView("devis/devis");

        try{
            devisService.saveOrUpdateDevis(demande, type, ids, libelles, qtes, pus);

            return new ModelAndView("redirect:/devis");
        } catch (Exception e) {
            
                    mv.addObject("error", e.getMessage());
            
                    mv.addObject("demandes", demandeService.findAll());
                    mv.addObject("types", typeService.findAll());
            
                    return mv;
                }
       
    }

    @GetMapping("/devis/search")
    public ModelAndView pageSearchDevis() {

        ModelAndView mv = new ModelAndView("devis/devis-search");

        mv.addObject("demandes", demandeService.findAll());
        mv.addObject("types", typeService.findAll());

        return mv;
    }

    @PostMapping("/devis/search0")
    public ModelAndView searchDevis0(
            @RequestParam Integer demande,
            @RequestParam Integer type) {

        ModelAndView mv = new ModelAndView("devis/devis-search");

        mv.addObject("demandes", demandeService.findAll());
        mv.addObject("types", typeService.findAll());

        mv.addObject("devisList",
                devisService.findByDemandeAndType(demande, type));

        return mv;
    }

    @PostMapping("/devis/search")
    public ModelAndView searchDevis(
            @RequestParam Integer demande,
            @RequestParam Integer type) {

        ModelAndView mv = new ModelAndView("devis/devis-search");

        mv.addObject("demandes", demandeService.findAll());
        mv.addObject("types", typeService.findAll());

        var devisList = devisService.findByDemandeAndType(demande, type);

        mv.addObject("devisList", devisList);


        Map<Integer, String> statusMap = new HashMap<>();

        for (Devis d : devisList) {

            DemandeStatus ds = demandeService.getCurrentStatus(d.getDemande().getId());

            if (ds != null) {
                statusMap.put(d.getId(), ds.getStatus().getLibelle());
            }
        }

        mv.addObject("statusMap", statusMap);

        return mv;
    }
    @PostMapping("/devis/status")
    public ModelAndView updateStatus(
            @RequestParam Integer devisId,
            @RequestParam String action) {

        Devis devis = devisService.findById(devisId);

        if (devis == null) {
            throw new RuntimeException("Devis not found");
        }

        String type = devis.getType().getLibelle();

        String statusLibelle = "";

        if (type.equalsIgnoreCase("Etude")) {
            if (action.equals("accept")) {
                statusLibelle = "Devis etude acceptee";
            } else {
                statusLibelle = "Devis etude refusee";
            }
        } else if (type.equalsIgnoreCase("Forage")) {
            if (action.equals("accept")) {
                statusLibelle = "Devis forage acceptee";
            } else {
                statusLibelle = "Devis forage refusee";
            }
        }

        Integer demandeId = devis.getDemande().getId();

        demandeService.addStatusByLibelle(demandeId, statusLibelle);

        return new ModelAndView("redirect:/devis/search");
    }

}