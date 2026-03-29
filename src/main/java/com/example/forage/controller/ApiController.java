package com.example.forage.controller;

import com.example.forage.model.Demande;
import com.example.forage.service.ClientService;
import com.example.forage.service.DemandeService;
import com.example.forage.service.DemandeStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class ApiController {

    private final DemandeService demandeService;

    public ApiController(DemandeService demandeService) {

        this.demandeService = demandeService;
    }

    @GetMapping("/api/demande/{id}")
    @ResponseBody
    public Demande getDemande(@PathVariable Integer id) {
        return demandeService.findById(id);
    }
}