package com.example.forage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.forage.service.StatusService;

@Controller
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/status")
    public ModelAndView pageStatus() {

        ModelAndView mv = new ModelAndView("status/status");

        mv.addObject("statuses", statusService.findAll());

        return mv;
    }

    @PostMapping("/status")
    public ModelAndView saveStatus(@RequestParam String libelle) {

        statusService.save(libelle);

        return new ModelAndView("redirect:/status");
    }
}