package com.example.correction.service;

import com.example.correction.model.Candidat;
import com.example.correction.model.Matiere;
import com.example.correction.model.Note;
import com.example.correction.model.NoteFinal;
import com.example.correction.model.Parametre;
import com.example.correction.model.Note;
import com.example.correction.repository.NoteFinalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteFinalService {

    private final NoteFinalRepository repository;
    private final NoteService noteService;
    private final ParametreService parametreService;


    public NoteFinalService(NoteFinalRepository repository, NoteService noteService, ParametreService parametreService) {
        this.repository = repository;
        this.noteService = noteService;
        this.parametreService = parametreService;
    }

    public List<NoteFinal> findAll(){
        return repository.findAll();
    }

    public NoteFinal findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public NoteFinal save(NoteFinal NoteFinal){
        return repository.save(NoteFinal);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public NoteFinal calculNoteFinal(Matiere matiere, Candidat candidat) {
        List<Note> notes = noteService.getNotes(matiere, candidat);
        double diff = noteService.calculSommeDifference(matiere, candidat);
        Parametre parametre = parametreService.getParametre(matiere, diff);
        String resolution = parametre.getResolution().getNom();
        double noteFinale = calculerNoteFinal(notes, resolution);
        NoteFinal nf = repository.findByMatiereAndCandidat(matiere, candidat);
        if (nf == null) {
            nf = new NoteFinal();
            nf.setCandidat(candidat);
            nf.setMatiere(matiere);
        }
        nf.setNote(noteFinale);
        return repository.save(nf);
    }

    public double calculerNoteFinal(List<Note> notes, String resolution) {
        if (notes == null || notes.isEmpty()) {
            return 0;
        }
        switch (resolution) {
            case "moyenne":
                double somme = 0;
                for (Note n : notes) {
                    somme += n.getNote();
                }
                return somme / notes.size();
            case "plus petit":
                double min = notes.get(0).getNote();
                for (Note n : notes) {
                    if (n.getNote() < min) {
                        min = n.getNote();
                    }
                }
                return min;
            case "plus grand":
                double max = notes.get(0).getNote();
                for (Note n : notes) {
                    if (n.getNote() > max) {
                        max = n.getNote();
                    }
                }
                return max;
        }
        return 0;
    }
}