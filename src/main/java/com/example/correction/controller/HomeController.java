package com.example.correction.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.correction.model.Candidat;
import com.example.correction.service.CandidatService;


@Controller
public class HomeController {

    private final CandidatService candidatService;

    public HomeController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/test")
    public ModelAndView test() {

        ModelAndView mv = new ModelAndView("test");

        List<Candidat> listes = candidatService.findAll();

        mv.addObject("listes", listes);

        return mv;
    }
    

}