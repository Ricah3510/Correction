package com.example.forage.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.forage.model.Demande;
import com.example.forage.service.ClientService;
import com.example.forage.service.DemandeService;
import com.example.forage.service.DemandeStatusService;
import com.example.forage.service.StatusService;

@Controller
public class DemandeController {

    private final DemandeService demandeService;
    private final ClientService clientService;
    private final DemandeStatusService demandeStatusService;
    private final StatusService statusService;


    public DemandeController(
            DemandeService demandeService,
            ClientService clientService,StatusService statusService,
            DemandeStatusService demandeStatusService) {
        this.statusService = statusService;
        this.demandeService = demandeService;
        this.clientService = clientService;
        this.demandeStatusService = demandeStatusService;
    }

    @GetMapping("/demandes")
    public ModelAndView pageDemande() {

        ModelAndView mv = new ModelAndView("demandes/demande");

        mv.addObject("demandes", demandeService.findAll());
        mv.addObject("clients", clientService.findAll());

        return mv;
    }

    @PostMapping("/demandes")
    public ModelAndView saveDemande(
            @RequestParam Integer client,
            @RequestParam String lieu,
            @RequestParam String district) {

        Demande d = new Demande();
        d.setLieu(lieu);
        d.setDistrict(district);
        d.setDate(new Date());

        demandeService.save(d, client);

        return new ModelAndView("redirect:/demandes");
    }

    @GetMapping("/demandes/delete")
    public ModelAndView deleteDemande(@RequestParam Integer id) {
        demandeService.delete(id);
        return new ModelAndView("redirect:/demandes");
    }

    @GetMapping("/demandes/edit")
    public ModelAndView editDemande(@RequestParam Integer id) {

        ModelAndView mv = new ModelAndView("demandes/demande-edit");

        mv.addObject("demande", demandeService.findById(id));
        mv.addObject("clients", clientService.findAll());

        return mv;
    }

    @PostMapping("/demandes/update")
    public ModelAndView updateDemande(
            @RequestParam Integer id,
            @RequestParam Integer client,
            @RequestParam String lieu,
            @RequestParam String district) {

        Demande d = demandeService.findById(id);

        d.setLieu(lieu);
        d.setDistrict(district);

        d.setClient(clientService.findById(client));

        demandeService.update(d);

        return new ModelAndView("redirect:/demandes");
    }

    @GetMapping("/demandes/status")
    public ModelAndView pageDemandeStatus() {

        ModelAndView mv = new ModelAndView("demandes/demande-status");

        mv.addObject("demandes", demandeService.findAll());
        mv.addObject("statuses", statusService.findAll());

        return mv;
    }

    @PostMapping("/demandes/status")
    public ModelAndView saveDemandeStatus(
            @RequestParam Integer demande,
            @RequestParam Integer status,
            @RequestParam String observation) {

        //demandeService.addStatus(demande, status, observation);
        demandeService.addStatusByLibelle(demande, statusService.findById(status).getLibelle(), observation);

        return new ModelAndView("redirect:/demandes/status");
    }
}