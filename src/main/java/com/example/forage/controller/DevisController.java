package com.example.forage.controller;

import com.example.forage.model.Devis;
import com.example.forage.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

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


    @GetMapping("/devis")
    public ModelAndView pageDevis() {

        ModelAndView mv = new ModelAndView("devis/devis");

        mv.addObject("demandes", demandeService.findAll());
        mv.addObject("types", typeService.findAll());

        return mv;
    }


    @PostMapping("/devis")
    public ModelAndView saveDevis(
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

    @GetMapping("/devis/search")
    public ModelAndView pageSearchDevis() {

        ModelAndView mv = new ModelAndView("devis/devis-search");

        mv.addObject("demandes", demandeService.findAll());
        mv.addObject("types", typeService.findAll());

        return mv;
    }

    @PostMapping("/devis/search")
    public ModelAndView searchDevis(
            @RequestParam Integer demande,
            @RequestParam Integer type) {

        ModelAndView mv = new ModelAndView("devis/devis-search");

        mv.addObject("demandes", demandeService.findAll());
        mv.addObject("types", typeService.findAll());

        mv.addObject("devisList",
                devisService.findByDemandeAndType(demande, type));

        return mv;
    }
}