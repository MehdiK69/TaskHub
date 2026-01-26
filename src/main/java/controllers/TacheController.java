package controllers;
import data.*;
import models.Tache;
import services.TacheService;

import java.io.IOException;
import java.util.ArrayList;

public class TacheController {
    private TacheService tacheService;
    public TacheController(TacheService taches) {
        this.tacheService = new TacheService();
        this.chargerDonnees();
    }

    public void ajouterTache(Tache tache) {
        tacheService.ajouterTache(tache);
        DataManager.sauvegarderTaches(tacheService);
        System.out.println("✓ Tâche ajoutée");  // ← Affichage ICI
    }

    private void chargerDonnees() throws IOException {
        this.tacheService = new TacheService();

        // 2. Charger depuis le fichier
        ArrayList<Tache> tacheArrayList = DataManager.chargerTaches();

        // 3. Ajouter chaque tâche au service
        for (Tache t : tacheArrayList) {
            tacheService.ajouterTache(t);
        }
    }

    private void sauvegarderDonnees() {

    }

}
