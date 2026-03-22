MCD : 
-t_client: nom, contact
-t_demande : date, client, lieu, district
-t_type: libelle (etude,forage,analyse)
choix 1 : 
-t_details_devis: type, demande, date,libelle(motif), montant
-v_devis (view) : type, demande, date(last), SUM(montant) montant_total

choix 2 :
-t_devis: type, demande, montant_total, date
-t_details_devis: devis, libelle(motif), montant

-t_status : libelle (etude en attente, etude commencee, etude terminer, forage commencer, etc jusqu'a -> analyse terminer),
--il peut aussi y avoir : projet interrompu, eau buvable
-t_demande_status : demande, status, date

Entity : (quand c'est avec "s" => List<>)
Client : nom, contact, Demandes
Demande : Dlient, lieu, district, date, Deviss, DemandeStatus (satus en cours)
Devis : Demande, Type, montant_total, date, DetailsDeviss
Type : libelle, (may be  ajout Deviss)
DetailsDevis: Devis, libelle, montant
Status : 
DemandeStatus : Demande, Status

Plan :
1-creation base
2-creation model
3-repository
4-service (CRUD Client et Demande)
5-Controller Client et Demande
6-View pour CRUD complet Client et Demande