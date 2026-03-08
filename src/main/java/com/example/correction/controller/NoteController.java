package com.example.correction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.correction.service.CandidatService;
import com.example.correction.service.CorrecteurService;
import com.example.correction.service.MatiereService;
import com.example.correction.service.NoteService;

@Controller
public class NoteController {

    private final NoteService noteService;
    private final CandidatService candidatService;
    private final MatiereService matiereService;
    private final CorrecteurService correcteurService;

    public NoteController(
            NoteService noteService,
            CandidatService candidatService,
            MatiereService matiereService,
            CorrecteurService correcteurService) {

        this.noteService = noteService;
        this.candidatService = candidatService;
        this.matiereService = matiereService;
        this.correcteurService = correcteurService;
    }

    @GetMapping("/notes")
    public ModelAndView notes(){
        ModelAndView mv = new ModelAndView("notes");
        mv.addObject("notes", noteService.findAll());
        mv.addObject("candidats", candidatService.findAll());
        mv.addObject("matieres", matiereService.findAll());
        mv.addObject("correcteurs", correcteurService.findAll());
    
        return mv;
    }
    @PostMapping("/notes/save")
    public String save(
            @RequestParam(required=false) Long id,
            @RequestParam Long candidat,
            @RequestParam Long matiere,
            @RequestParam Long correcteur,
            @RequestParam double note){
    
        noteService.save(id, candidat, matiere, correcteur, note);
    
        return "redirect:/notes";
    }


    @GetMapping("/notes/delete")
    public String delete(@RequestParam Long id){

        noteService.delete(id);

        return "redirect:/notes";
    }

    @GetMapping("/notes/edit")
    public ModelAndView edit(@RequestParam Long id){

        ModelAndView mv = new ModelAndView("notes");

        mv.addObject("noteEdit", noteService.findById(id));

        mv.addObject("notes", noteService.findAll());
        mv.addObject("candidats", candidatService.findAll());
        mv.addObject("matieres", matiereService.findAll());
        mv.addObject("correcteurs", correcteurService.findAll());

        return mv;
    }
}