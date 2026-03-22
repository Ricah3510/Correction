INSERT INTO t_correcteur (nom) VALUES
('Correcteur 1'),
('Correcteur 2'),
('Correcteur 3');

INSERT INTO t_matiere (nom) VALUES
('PYTHON');

INSERT INTO t_candidat (nom) VALUES
('Candidat 3');

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
(1, 4, 3, 1),
(1, 1, 3, 3);


INSERT INTO t_note (id_candidat, id_matiere, id_correcteur, note) VALUES
-- Candidat 1
(1,1,1,14.5),
(1,1,2,13);
