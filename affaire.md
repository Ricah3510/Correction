sur branch master
    - creation branch double-correction
switch branch double-correction
    -stucture
    - creation branch feature/script
switch branch feature/script
    - script base, test de donnee, delete*
    -commit
    -push
sur github : accepeter pull request feature/script -> double-correction
    -delete branch feature/script sur git et en local

switch branch double-correction
    -creation branch feature/stucture-complet (model,service,repository, views etc)
branch feature/stucture-complet
    TODO :
    -finir les applications.properties, pom.xml
    -creation des models
        Correcteur.java : id, nom
        Matiere.java : id, nom
        Candidat.java : id, nom, List<Note>, List<NoteFinal>
        Resolution.java : id, nom
        Operateur.java : id, operateur
        Parametre.java : id, Matiere, diff, Operateur, Resolution
        Note : id, Matiere, Candidat, Correcteur, note
        NoteFinal: id, Matiere, Candidat, note
    -commit
    -push
sur github : accepeter pull request feature/stucture-complet  -> double-correction
    -delete branch feature/stucture-complet  sur git et en local


switch branch double-correction
apres push double-correction -> master


    -creation branch feature/service-calculNoteFinal
    -switch feature/service-calculNoteFinal
    -services :
        Diff : -NoteService.calculSommeDifference(matiere, candidat)
            List<Note> getNotes(matiere,candidat)

        Parametre : -Parametre.getParametre(matiere, diff)
        NoteFinal.save(NoteFinal) (save si n'existe pas encore et update si deja present : idMatiere, idCandidat)

        view showNoteFinal.jsp : form : select candidat et matiere => on montre en bas sa NoteFinal pour cette matiere
        A part :CRUD pour 2 Modules (avec leurs views)
sur github : accepeter pull request feature/service-calculNoteFinal -> double-correction
    -delete branch feature/service-calculNoteFinal sur git et en local

!!!Rehefa mandeha tsara vao manao an'ity final ty
push double-correction -> master


CRUD note et parametre