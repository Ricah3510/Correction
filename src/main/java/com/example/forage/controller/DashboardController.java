package com.example.forage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.forage.model.Demande;
import com.example.forage.service.ClientService;
import com.example.forage.service.DemandeStatusService;
import com.example.forage.service.DevisService;
import com.example.forage.service.StatusStatDTOService;

@Controller
public class DashboardController {

    private final DevisService devisService;
    private final ClientService clientService;
    private final DemandeStatusService demandeStatusService;
    private final StatusStatDTOService statusStatDTOService;

    public DashboardController(DevisService devisService, ClientService clientService, StatusStatDTOService statusStatDTOService, DemandeStatusService demandeStatusService) {
        this.devisService = devisService;
        this.clientService = clientService;
        this.statusStatDTOService = statusStatDTOService;
        this.demandeStatusService = demandeStatusService;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {

        ModelAndView mv = new ModelAndView("dashboard");

        mv.addObject("ca", devisService.getCA());

        mv.addObject("nbClients", clientService.findAll().size());

        mv.addObject("nbDevis", devisService.findAll().size());

        mv.addObject("stats", statusStatDTOService.getStatByStatus());
        return mv;
    }

    @GetMapping("/dashboard/status/{statusId}")
    public ModelAndView demandesByStatus(@PathVariable Integer statusId) {

        ModelAndView mv = new ModelAndView("status/status-demandes");

        List<Demande> demandes = demandeStatusService.findDemandesByCurrentStatusId(statusId);

        mv.addObject("demandes", demandes);

        return mv;
    }
}