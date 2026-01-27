package services;

import enums.Priorite;
import enums.Statut;
import models.Tache;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataManagerTest {
    private TacheService service;
    private TacheService service2;
    private Tache tache1;
    private Tache tache2;
    private Tache tache3;
    private Tache tache4;
    private Tache tache5;

    @Before
    public void initialiserDonnees() {
        tache1 = new Tache(1, "appli java", "appli TaskHub a coder", Priorite.MOYENNE, Statut.A_FAIRE, LocalDate.now(), LocalDate.now().plusMonths(2), 1, "Projet", false);
        tache2 = new Tache(2, "saé 2nd semestre", "appli machin learning", Priorite.HAUTE, Statut.A_FAIRE, LocalDate.now(), LocalDate.now().plusMonths(3), 2, "Projet", false);
        tache3 = new Tache(3, "Repondre email ecole", "repondre pour l'inscription", Priorite.BASSE, Statut.EN_COURS, LocalDate.now(), LocalDate.now().plusDays(7), 3, "Mail", false);
        tache4 = new Tache(4, "rdv orthodontiste", "rdv orthondiste isma", Priorite.HAUTE, Statut.TERMINEE, LocalDate.now(), LocalDate.now(), 4, "Medical", true);
        tache5 = new Tache(5, "bidon", "bidon", Priorite.HAUTE, Statut.TERMINEE, LocalDate.now(), LocalDate.now(), 5, "bidon", true);
        service = new TacheService();
        service2 = new TacheService();
        service.ajouterTache(tache1);
        service.ajouterTache(tache2);
        service.ajouterTache(tache3);
        service.ajouterTache(tache4);

    }

    @Test
    public static void sauvegarderTaches(ArrayList<Tache> taches) throws IOException{
        FileWriter fw = new FileWriter("test.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(taches.toString());
        bw.newLine();
    }
}
