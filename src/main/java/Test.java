import models.Tache;
import models.Projet;
import services.TacheService;
import services.ProjetService;
import data.DataManager;
import enums.Priorite;
import enums.Statut;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args){

        System.out.println("=== TASKHUB - Démarrage ===\n");

        // 1. Créer les services
        TacheService tacheService = new TacheService();
        ProjetService projetService = new ProjetService();

        // 2. Charger les données
        chargerDonnees(tacheService, projetService);

        // 3. Afficher les projets existants
        afficherProjets(projetService);

        // 4. Créer des projets si nécessaire
        if (projetService.getNombreProjets() == 0) {
            System.out.println("\n--- Création de projets ---");

            Projet p1 = new Projet(1, "École", "Projets scolaires","orange", LocalDate.now());
            projetService.ajouterProjet(p1);
            System.out.println("✓ Projet créé : " + p1.getNom());

            Projet p2 = new Projet(2, "Personnel", "Projets perso","rouge", LocalDate.now());
            projetService.ajouterProjet(p2);
            System.out.println("✓ Projet créé : " + p2.getNom());

            afficherProjets(projetService);
        }

        // 5. Afficher les tâches existantes
        afficherTaches(tacheService, projetService);

        // 6. Ajouter quelques tâches
        System.out.println("\n--- Ajout de nouvelles tâches ---");

        Tache t1 = new Tache(
                getProchainIdTache(tacheService),
                "Finir TaskHub",
                "Terminer le projet",
                Priorite.HAUTE,
                Statut.EN_COURS,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                1, // ID projet "École"
                "Projet",
                false
        );
        tacheService.ajouterTache(t1);
        System.out.println("✓ Tâche ajoutée : " + t1.getTitre());

        Tache t2 = new Tache(
                getProchainIdTache(tacheService),
                "Faire les courses",
                "Lait, pain, œufs",
                Priorite.BASSE,
                Statut.A_FAIRE,
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                2, // ID projet "Personnel"
                "Quotidien",
                false
        );
        tacheService.ajouterTache(t2);
        System.out.println("✓ Tâche ajoutée : " + t2.getTitre());

        // 7. Afficher toutes les tâches avec leurs projets
        afficherTaches(tacheService, projetService);

        // 8. Afficher les statistiques
        afficherStatistiques(tacheService, projetService);

        // 9. Sauvegarder tout
        sauvegarderDonnees(tacheService, projetService);

        System.out.println("\n=== TASKHUB - Fin ===");
    }

        // Charger les données
        private static void chargerDonnees(TacheService tacheService, ProjetService projetService) {
            try {
                // Charger les projets
                ArrayList<Projet> projets = DataManager.chargerProjets();
                for (Projet p : projets) {
                    projetService.ajouterProjet(p);
                }
                System.out.println("✓ " + projets.size() + " projet(s) chargé(s)");

                // Charger les tâches
                ArrayList<Tache> taches = DataManager.chargerTaches();
                for (Tache t : taches) {
                    tacheService.ajouterTache(t);
                }
                System.out.println("✓ " + taches.size() + " tâche(s) chargée(s)\n");

            } catch (IOException e) {
                System.out.println("⚠ Aucune donnée à charger (premier lancement ?)\n");
            }
        }

        // Sauvegarder les données
        private static void sauvegarderDonnees(TacheService tacheService, ProjetService projetService) {
            try {
                DataManager.sauvegarderProjets(projetService.listerProjets());
                DataManager.sauvegarderTaches(tacheService.listerTaches());
                System.out.println("✓ Données sauvegardées");
            } catch (IOException e) {
                System.out.println("✗ Erreur sauvegarde : " + e.getMessage());
            }
        }

        // Afficher les projets
        private static void afficherProjets(ProjetService projetService) {
            ArrayList<Projet> projets = projetService.listerProjets();

            if (projets.isEmpty()) {
                System.out.println("Aucun projet.");
                return;
            }

            System.out.println("\n=== PROJETS ===");
            for (Projet p : projets) {
                System.out.println(p);
            }
            System.out.println("===============");
        }

        // Afficher les tâches avec leurs projets
        private static void afficherTaches(TacheService tacheService, ProjetService projetService) {
            ArrayList<Tache> taches = tacheService.listerTaches();

            if (taches.isEmpty()) {
                System.out.println("\nAucune tâche.");
                return;
            }

            System.out.println("\n=== TÂCHES ===");
            for (Tache t : taches) {
                String statut = t.getTerminee() ? "✓" : "○";
                String nomProjet = "Sans projet";

                if (t.getIdProjet() != 0) {
                    Projet p = projetService.trouverProjetParId(t.getIdProjet());
                    if (p != null) {
                        nomProjet = p.getNom();
                    }
                }

                System.out.println(statut + " [" + t.getPriorite() + "] " +
                        t.getTitre() + " - " + t.getDescription() +
                        " (Projet: " + nomProjet + ")");
            }
            System.out.println("==============");
        }

        // Afficher les statistiques
        private static void afficherStatistiques(TacheService tacheService, ProjetService projetService) {
            System.out.println("\n=== STATISTIQUES ===");
            System.out.println("Projets : " + projetService.getNombreProjets());
            System.out.println("Total tâches : " + tacheService.getNombreTaches());
            System.out.println("Tâches terminées : " + tacheService.getNombreTachesTerminees());
            System.out.println("====================");
        }

        // Obtenir le prochain ID de tâche
        private static int getProchainIdTache(TacheService service) {
            ArrayList<Tache> taches = service.listerTaches();
            int maxId = 0;
            for (Tache t : taches) {
                if (t.getId() > maxId) {
                    maxId = t.getId();
                }
            }
            return maxId + 1;
        }
}
