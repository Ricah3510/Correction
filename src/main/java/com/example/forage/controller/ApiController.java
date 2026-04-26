package com.example.forage.controller;

import com.example.forage.model.Demande;
import com.example.forage.model.DemandeStatus;
import com.example.forage.model.Devis;
import com.example.forage.service.ClientService;
import com.example.forage.service.DemandeService;
import com.example.forage.service.DemandeStatusService;
import com.example.forage.service.DevisService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
@Controller
public class ApiController {

    private final DemandeService demandeService;
    private final DevisService devisService;
    private final DemandeStatusService demandeStatusService;

    public ApiController(DemandeService demandeService, DevisService devisService, DemandeStatusService demandeStatusService) {

        this.demandeService = demandeService;
        this.devisService = devisService;
        this.demandeStatusService = demandeStatusService;
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

    // @GetMapping("/api/demande/status")
    // @ResponseBody
    // public Map<String, Object> getDemandeStatus(@RequestParam Integer demande) {

    //     Map<String, Object> res = new HashMap<>();

    //     List<DemandeStatus> list = demandeStatusService.getHistoriqueStatus(demande);

    //     if (!list.isEmpty()) {
    //         res.put("current", list.get(0));
    //     }

    //     res.put("history", list);

    //     return res;
    // }

    @GetMapping("/api/demande/status")
    @ResponseBody
    public Map<String, Object> getDemandeStatus(@RequestParam Integer demande) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> res = new HashMap<>();

        List<DemandeStatus> list = demandeStatusService.getHistoriqueStatus(demande);

        if (!list.isEmpty()) {

            DemandeStatus current = demandeService.getCurrentStatus(demande);

            Map<String, Object> currentMap = new HashMap<>();
            currentMap.put("id", current.getId());
            currentMap.put("status", current.getStatus().getLibelle());
            currentMap.put("observation", current.getObservation());
            currentMap.put("date", sdf.format(current.getDate()));
            // currentMap.put("dureePlein",
            //     current.getDureePlein() != null ? current.getDureePlein().getSeconds() : null);

            // currentMap.put("dureeOuvert",
            //     current.getDureeOuvert() != null ? current.getDureeOuvert().getSeconds() : null);


            res.put("current", currentMap);
        }

        List<Map<String, Object>> history = new ArrayList<>();

        for (DemandeStatus ds : list) {

            Map<String, Object> m = new HashMap<>();
            m.put("id", ds.getId());
            m.put("status", ds.getStatus().getLibelle());
            m.put("observation", ds.getObservation());
            m.put("date", sdf.format(ds.getDate()));
            m.put("dureePlein",
                ds.getDureePlein() != null ? ds.getDureePlein().getSeconds() : null);

            m.put("dureeOuvert",
                ds.getDureeOuvert() != null ? ds.getDureeOuvert().getSeconds() : null);

            history.add(m);
        }

        res.put("history", history);

        return res;
    }

    @PostMapping("/api/demande/status/update-observation")
    @ResponseBody
    public String updateObservation(
            @RequestParam Integer id,
            @RequestParam String observation) {

        demandeStatusService.updateObservation(id, observation);
        return "OK";
    }

    @PostMapping("/api/demande/status/update-date")
    @ResponseBody
    public String updateDate(
            @RequestParam Integer id,
            @RequestParam String dateStr) {
    
        demandeStatusService.updateDate(id, dateStr);
    
        return "OK";
    }
}