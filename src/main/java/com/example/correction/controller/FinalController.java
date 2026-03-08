package com.example.correction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.correction.model.Candidat;
import com.example.correction.model.Matiere;
import com.example.correction.model.NoteFinal;
import com.example.correction.service.CandidatService;
import com.example.correction.service.MatiereService;
import com.example.correction.service.NoteFinalService;

@Controller
public class FinalController {

    private final CandidatService candidatService;
    private final MatiereService matiereService;
    private final NoteFinalService noteFinalService;

    public FinalController(
            CandidatService candidatService,
            MatiereService matiereService,
            NoteFinalService noteFinalService) {

        this.candidatService = candidatService;
        this.matiereService = matiereService;
        this.noteFinalService = noteFinalService;
    }

    @GetMapping("/final")
    public ModelAndView pageFinal() {

        ModelAndView mv = new ModelAndView("final");

        mv.addObject("candidats", candidatService.findAll());
        mv.addObject("matieres", matiereService.findAll());

        return mv;
    }

    @PostMapping("/final")
    public ModelAndView calculFinal(
            @RequestParam Long candidat,
            @RequestParam Long matiere) {

        ModelAndView mv = new ModelAndView("final");

        Candidat c = candidatService.findById(candidat);
        Matiere m = matiereService.findById(matiere);

        NoteFinal nf = noteFinalService.calculNoteFinal(m, c);

        mv.addObject("noteFinal", nf);
        mv.addObject("candidats", candidatService.findAll());
        mv.addObject("matieres", matiereService.findAll());

        return mv;
    }
}