CREATE TABLE t_demande_status (
    id SERIAL PRIMARY KEY,
    id_demande INT NOT NULL,
    id_status INT NOT NULL,
    --observation VARCHAR(255),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_demande) REFERENCES t_demande(id) ON DELETE CASCADE,
    FOREIGN KEY (id_status) REFERENCES t_status(id)
);

ajout colonne observation dans table t_demande_status
appliquer les modifications dans le model
- page crud status
- page demande status : 
    partie haut : 
        formulaire(select demande, select status, input observation) = > ajout dans t_demande_status
    partie bas : 
        comme dans devis, apres que demande aie changer, selectionner, on va afficher :
            - le status en cours avec son observation
            - dans pour les details devis (son historique) ou on pourra changer uniquement l'observation, le reste est fixe