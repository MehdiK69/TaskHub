import enums.Priorite;
import enums.Statut;
import models.*;
import services.*;
import data.DataManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args){

    Tache tache1 = new Tache(1, "appli java", "appli TaskHub a coder", Priorite.MOYENNE, Statut.A_FAIRE, LocalDate.now(), LocalDate.now().plusMonths(2), 1, "Projet", false);
    Tache tache2 = new Tache(2, "saé 2nd semestre", "appli machin learning", Priorite.HAUTE, Statut.A_FAIRE, LocalDate.now(), LocalDate.now().plusMonths(3), 2, "Projet", false);
    Tache tache3 = new Tache(3, "Repondre email ecole", "repondre pour l'inscription", Priorite.BASSE, Statut.EN_COURS, LocalDate.now(), LocalDate.now().plusDays(7), 3, "Mail", false);
    Tache tache4 = new Tache(4, "rdv orthodontiste", "rdv orthondiste isma", Priorite.HAUTE, Statut.TERMINEE, LocalDate.now(), LocalDate.now(), 4, "Medical", true);
    Tache tache5 = new Tache(5, "bidon", "bidon", Priorite.HAUTE, Statut.TERMINEE, LocalDate.now(), LocalDate.now(), 5, "bidon", true);
    Tache tache6 = new Tache(6, "Rdv medical", "rdv medical perso", Priorite.MOYENNE, Statut.A_FAIRE, LocalDate.now(), null, 5, "bidon", false);
    TacheService service = new TacheService();
    service.ajouterTache(tache1);
    service.ajouterTache(tache2);
    service.ajouterTache(tache3);
    service.ajouterTache(tache4);
    service.ajouterTache(tache5);
    service.ajouterTache(tache6);



        try {
            DataManager.sauvegarderTaches(service.listerTaches());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /*// Afficher
        service.afficherTaches();

        // Marquer 2 comme terminées
        service.marquerTerminee(3);

        // Afficher
        service.afficherTaches();

        // Supprimer 1 tâche
        service.supprimerTache(t4);

        // Afficher
        service.afficherTaches();

        // Stats
        System.out.println("Total : " + service.getNombreTaches());
        System.out.println("Terminées : " + service.getNombreTachesTerminees());*/


    }

}
