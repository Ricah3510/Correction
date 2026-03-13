INSERT INTO t_correcteur (nom) VALUES
('Correcteur 1'),
('Correcteur 2'),
('Correcteur 3'),
('Correcteur 4');

INSERT INTO t_matiere (nom) VALUES
('Mathematiques'),
('Anglais');

INSERT INTO t_candidat (nom) VALUES
('Candidat 1'),
('Candidat 2');

INSERT INTO t_resolution (nom) VALUES
('plus petit'),
('plus grand'),
('moyenne');

INSERT INTO t_operateur (operateur) VALUES
('<'),
('<='),
('>'),
('>=');

INSERT INTO t_parametre (id_matiere, diff, id_operateur, id_resolution) VALUES
(1, 5, 4, 2),
(1, 8, 1, 1);

INSERT INTO t_note (id_candidat, id_matiere, id_correcteur, note) VALUES
-- Candidat 1
(1,1,1,10),
(1,1,2,17);
