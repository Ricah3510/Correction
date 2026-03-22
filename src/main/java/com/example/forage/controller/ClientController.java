package com.example.forage.controller;

import com.example.forage.model.Client;
import com.example.forage.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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
}