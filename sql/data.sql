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
('>');

INSERT INTO t_parametre (id_matiere, diff, id_operateur, id_resolution) VALUES
(1, 5, 1, 2),
(1, 5, 2, 1),
(2, 4, 1, 2),
(2, 4, 2, 1);

INSERT INTO t_note (id_candidat, id_matiere, id_correcteur, note) VALUES
-- Candidat 1
(1,1,1,7.5),
(1,1,2,6),

-- Candidat 2
(2,2,1,8),
(2,2,2,9),
(2,2,3,13);


-- INSERT INTO t_note (id_candidat, id_matiere, id_correcteur, note) VALUES
-- -- Candidat 1
-- (1,2,1,18),
-- (1,2,2,18),
-- (1,2,3,18),
-- (1,2,4,18);

-- INSERT INTO t_note (id_candidat, id_matiere, id_correcteur, note) VALUES
-- -- Candidat 2
-- (2,1,1,14.5),
-- (2,1,2,12.5),
-- (2,1,4,15);