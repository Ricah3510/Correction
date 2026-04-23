package com.example.forage.controller;

import com.example.forage.model.Client;
import com.example.forage.model.Demande;
import com.example.forage.model.DemandeStatus;
import com.example.forage.service.ClientService;
import com.example.forage.service.DemandeService;
import com.example.forage.service.DemandeStatusService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    private final ClientService clientService;
    private final DemandeService demandeService;
    private final DemandeStatusService demandeStatusService;

    public ClientController(ClientService clientService, DemandeService demandeService, DemandeStatusService demandeStatusService) {
        this.clientService = clientService;
        this.demandeService = demandeService;
        this.demandeStatusService = demandeStatusService;
    }

    @GetMapping("/clients")
    public ModelAndView pageClient() {

        ModelAndView mv = new ModelAndView("clients/client");

        mv.addObject("clients", clientService.findAll());

        return mv;
    }

    @PostMapping("/clients")
    public ModelAndView saveClient(
            @RequestParam String nom,
            @RequestParam String contact) {

        Client c = new Client();
        c.setNom(nom);
        c.setContact(contact);

        clientService.save(c);

        return new ModelAndView("redirect:/clients");
    }

    @GetMapping("/clients/delete")
    public ModelAndView deleteClient(@RequestParam Integer id) {
        clientService.delete(id);
        return new ModelAndView("redirect:/clients");
    }

    @GetMapping("/clients/edit")
    public ModelAndView editClient(@RequestParam Integer id) {

        ModelAndView mv = new ModelAndView("clients/client-edit");

        mv.addObject("client", clientService.findById(id));

        return mv;
    }

    @PostMapping("/clients/update")
    public ModelAndView updateClient(
            @RequestParam Integer id,
            @RequestParam String nom,
            @RequestParam String contact) {

        Client c = clientService.findById(id);
        c.setNom(nom);
        c.setContact(contact);

        clientService.save(c);

        return new ModelAndView("redirect:/clients");
    }

    @GetMapping("/clients/{id}")
    public ModelAndView detailClient(@PathVariable Integer id) {

        ModelAndView mv = new ModelAndView("clients/client-detail");

        Client client = clientService.findById(id);

        List<Demande> demandes = demandeService.findByClient(id);

        Map<Integer, String> statusMap = new HashMap<>();

        for (Demande d : demandes) {

            DemandeStatus ds = demandeStatusService.getCurrentStatus(d.getId());

            if (ds != null) {
                statusMap.put(d.getId(), ds.getStatus().getLibelle());
            }
        }

        mv.addObject("client", client);
        mv.addObject("demandes", demandes);
        mv.addObject("statusMap", statusMap);

        return mv;
    }
}