package com.example.correction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.correction.service.ParametreService;
import com.example.correction.service.MatiereService;
import com.example.correction.service.OperateurService;
import com.example.correction.service.ResolutionService;

@Controller
public class ParametreController {

    private final ParametreService parametreService;
    private final MatiereService matiereService;
    private final OperateurService operateurService;
    private final ResolutionService resolutionService;

    public ParametreController(
            ParametreService parametreService,
            MatiereService matiereService,
            OperateurService operateurService,
            ResolutionService resolutionService) {

        this.parametreService = parametreService;
        this.matiereService = matiereService;
        this.operateurService = operateurService;
        this.resolutionService = resolutionService;
    }

    @GetMapping("/parametres")
    public ModelAndView page() {

        ModelAndView mv = new ModelAndView("parametres");

        mv.addObject("parametres", parametreService.findAll());
        mv.addObject("matieres", matiereService.findAll());
        mv.addObject("operateurs", operateurService.findAll());
        mv.addObject("resolutions", resolutionService.findAll());

        return mv;
    }


    @PostMapping("/parametres/save")
    public String save(
            @RequestParam(required = false) Long id,
            @RequestParam Long matiere,
            @RequestParam Long operateur,
            @RequestParam double diff,
            @RequestParam Long resolution) {

        parametreService.save(id, matiere, operateur, diff, resolution);

        return "redirect:/parametres";
    }


    @GetMapping("/parametres/edit")
    public ModelAndView edit(@RequestParam Long id) {

        ModelAndView mv = new ModelAndView("parametres");

        mv.addObject("paramEdit", parametreService.findById(id));

        mv.addObject("parametres", parametreService.findAll());
        mv.addObject("matieres", matiereService.findAll());
        mv.addObject("operateurs", operateurService.findAll());
        mv.addObject("resolutions", resolutionService.findAll());

        return mv;
    }


    @GetMapping("/parametres/delete")
    public String delete(@RequestParam Long id) {

        parametreService.delete(id);

        return "redirect:/parametres";
    }

}