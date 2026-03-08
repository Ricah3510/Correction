package com.example.correction.service;

import com.example.correction.model.Candidat;
import com.example.correction.model.Correcteur;
import com.example.correction.model.Matiere;
import com.example.correction.model.Note;
import com.example.correction.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repository;
    private final CandidatService candidatService;
    private final MatiereService matiereService;
    private final CorrecteurService correcteurService;

    public NoteService(
        NoteRepository repository,
        CandidatService candidatService,
        MatiereService matiereService,
        CorrecteurService correcteurService) {
        this.repository = repository;
        this.candidatService = candidatService;
        this.matiereService = matiereService;
        this.correcteurService = correcteurService;
    }

    public List<Note> findAll(){
        return repository.findAll();
    }

    public Note findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Note save(Note Note){
        return repository.save(Note);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Note> getNotes(Matiere matiere, Candidat candidat) {
        return repository.findByMatiereAndCandidat(matiere, candidat);
    }

    public double calculSommeDifference(Matiere matiere, Candidat candidat) {

        List<Note> notes = getNotes(matiere, candidat);
        double diff = 0;

        if (notes.size() > 1) {

            for (int i = 0; i < notes.size(); i++) {
                for (int j = i + 1; j < notes.size(); j++) {
                    
                    double a = notes.get(i).getNote();
                    double b = notes.get(j).getNote();
                    
                    diff += Math.abs(a - b);
                }
            }
            
        }
        
        return diff;
    }

    public void save(Long id, Long candidatId, Long matiereId, Long correcteurId, double value){

        Note note;
    
        if(id != null){
            note = repository.findById(id).get();
        }else{
            note = new Note();
        }
    
        note.setCandidat(candidatService.findById(candidatId));
        note.setMatiere(matiereService.findById(matiereId));
        note.setCorrecteur(correcteurService.findById(correcteurId));
        note.setNote(value);
    
        repository.save(note);
    }
}