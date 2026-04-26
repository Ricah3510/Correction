\c postgres;
DROP DATABASE IF EXISTS db_forage;
CREATE DATABASE db_forage;
\c db_forage;

CREATE TABLE t_client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(200) NOT NULL,
    contact VARCHAR(200)
);

CREATE TABLE t_demande (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    lieu VARCHAR(200),
    district VARCHAR(200),
    id_client INT NOT NULL,
    FOREIGN KEY (id_client) REFERENCES t_client(id) ON DELETE CASCADE
);

CREATE TABLE t_type (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE t_devis (
    id SERIAL PRIMARY KEY,
    id_demande INT NOT NULL,
    id_type INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (id_demande) REFERENCES t_demande(id) ON DELETE CASCADE,
    FOREIGN KEY (id_type) REFERENCES t_type(id)
);

CREATE TABLE t_details_devis (
    id SERIAL PRIMARY KEY,
    id_devis INT NOT NULL,
    libelle VARCHAR(255),
    qte NUMERIC(30,2) NOT NULL,
    pu NUMERIC(30,2) NOT NULL,

    FOREIGN KEY (id_devis) REFERENCES t_devis(id) ON DELETE CASCADE
);

CREATE TABLE t_status (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(200) NOT NULL
);

CREATE TABLE t_demande_status (
    id SERIAL PRIMARY KEY,
    id_demande INT NOT NULL,
    id_status INT NOT NULL,
    --observation VARCHAR(255),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_demande) REFERENCES t_demande(id) ON DELETE CASCADE,
    FOREIGN KEY (id_status) REFERENCES t_status(id)
);

CREATE INDEX idx_demande_client ON t_demande(id_client);
CREATE INDEX idx_devis_demande ON t_devis(id_demande);
CREATE INDEX idx_details_devis ON t_details_devis(id_devis);
CREATE INDEX idx_demande_status ON t_demande_status(id_demande);

ALTER TABLE t_demande_status
ADD COLUMN observation VARCHAR(255);

ALTER TABLE t_demande_status
ADD COLUMN dureePlein INTERVAL;

ALTER TABLE t_demande_status
ADD COLUMN dureeOuvert INTERVAL;


ALTER TABLE t_demande_status
ALTER COLUMN dureeplein SET DEFAULT NULL;

ALTER TABLE t_demande_status
ALTER COLUMN dureeouvert SET DEFAULT NULL;


CREATE TABLE t_parametre (
    id SERIAL PRIMARY KEY,
    heure_debut TIME NOT NULL,
    heure_fin TIME NOT NULL
);