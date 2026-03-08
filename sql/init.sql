CREATE DATABASE db_double_correction;
\c db_double_correction;

CREATE TABLE t_correcteur(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(200) NOT NULL
);

CREATE TABLE t_matiere(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(200) NOT NULL
);

CREATE TABLE t_candidat(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(200) NOT NULL
);

CREATE TABLE t_resolution(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(200) NOT NULL
);

CREATE TABLE t_operateur(
    id SERIAL PRIMARY KEY,
    operateur VARCHAR(200) NOT NULL
);

CREATE TABLE t_parametre(
    id SERIAL PRIMARY KEY,
    id_matiere INT NOT NULL,
    diff DECIMAL NOT NULL,
    id_operateur INT NOT NULL,
    id_resolution INT NOT NULL,
    FOREIGN KEY (id_matiere) REFERENCES t_matiere(id),
    FOREIGN KEY (id_operateur) REFERENCES t_operateur(id),
    FOREIGN KEY (id_resolution) REFERENCES t_resolution(id)
);

CREATE TABLE t_note(
    id SERIAL PRIMARY KEY,
    id_candidat INT NOT NULL,
    id_matiere INT NOT NULL,
    id_correcteur INT NOT NULL,
    note DECIMAL NOT NULL,
    FOREIGN KEY (id_candidat) REFERENCES t_candidat(id),
    FOREIGN KEY (id_matiere) REFERENCES t_matiere(id),
    FOREIGN KEY (id_correcteur) REFERENCES t_correcteur(id)
);

CREATE TABLE t_note_final(
    id SERIAL PRIMARY KEY,
    id_candidat INT NOT NULL,
    id_matiere INT NOT NULL,
    note DECIMAL NOT NULL,
    FOREIGN KEY (id_matiere) REFERENCES t_matiere(id),
    FOREIGN KEY (id_candidat) REFERENCES t_candidat(id)
);