package views;

import data.DataManager;
import models.Projet;
import models.Tache;
import services.ProjetService;
import services.TacheService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FenetrePrincipale extends JFrame {

    private JTable tableTaches;
    private DefaultTableModel tableModel;
    private TacheService tacheService;
    private ProjetService projetService;
    String nomProjet;

    public FenetrePrincipale() {
        super();
        construireFenetrePrincipale();
        tacheService = new TacheService();
        projetService = new ProjetService();
        chargerDonnees();
        construireFenetrePrincipale();
        rafraichirTable();
    }

    public void construireFenetrePrincipale() {
        setTitle("TaskHub - Gestion de tâches");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setContentPane(construirePanelPrincipale());
        setVisible(true);
    }


    public JPanel construirePanelPrincipale() {
        JPanel panelPrincipale = new JPanel(new BorderLayout());
        JPanel panelBoutons = construirePanelBoutons();
        panelPrincipale.add(panelBoutons, BorderLayout.NORTH);
        JScrollPane scrollPane = construireTableau();
        panelPrincipale.add(scrollPane, BorderLayout.CENTER);
        return panelPrincipale;
    }

    private JPanel construirePanelBoutons() {
        JPanel panelBoutons = new JPanel(new FlowLayout());
        JButton boutonAjouterProjet = new JButton("Ajouter un projet");
        JButton boutonAjouterTache = new JButton("Ajouter une tâche");
        JButton boutonSupprimerProjet = new JButton("Supprimer un projet");
        JButton boutonSupprimerTache = new JButton("Supprimer une tâche");
        panelBoutons.add(boutonAjouterProjet);
        panelBoutons.add(boutonAjouterTache);
        panelBoutons.add(boutonSupprimerProjet);
        panelBoutons.add(boutonSupprimerTache);
        return panelBoutons;
    }

    private JScrollPane construireTableau() {
        String[] colonnes = {"ID", "Titre", "Priorité", "Statut", "Projet", "Échéance"};
        tableModel = new DefaultTableModel(colonnes, 0);

       /*@Override
        public boolean isCellEditable(int row, int column){
            return false;  // Aucune cellule n'est éditable
        }*/

        tableTaches = new JTable(tableModel);
        // Après avoir créé tableTaches
        tableTaches.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID (petit)
        tableTaches.getColumnModel().getColumn(1).setPreferredWidth(200);  // Titre (large)
        tableTaches.getColumnModel().getColumn(2).setPreferredWidth(100);  // Priorité
        tableTaches.getColumnModel().getColumn(3).setPreferredWidth(100);  // Statut
        tableTaches.getColumnModel().getColumn(4).setPreferredWidth(150);  // Projet
        tableTaches.getColumnModel().getColumn(5).setPreferredWidth(100);  // Échéance
        JScrollPane scrollPane = new JScrollPane(tableTaches);
        return scrollPane;
    }

    // Charger les données
    private void chargerDonnees() {
        try {
            ArrayList<Tache> taches = DataManager.chargerTaches();
            for (Tache t : taches) {
                tacheService.ajouterTache(t);
            }
            ArrayList<Projet> projets = DataManager.chargerProjets();
            for (Projet p : projets) {
                projetService.ajouterProjet(p);
            }
            System.out.println("✓ " + projets.size() + " projet(s) et " +
                    taches.size() + " tâche(s) chargé(s)");
        } catch (IOException e) {
            // Premier lancement, pas de fichier, c'est normal
            System.out.println("Aucune donnée à charger (premier lancement)");
        }
    }

    // Rafraîchir le tableau
    private void rafraichirTable() {
        tableModel.setRowCount(0);
        ArrayList<Tache> taches = tacheService.listerTaches();
        for (Tache t : taches) {
            if (t.getIdProjet() == 0) {
                nomProjet = "Sans projet";
            } else {
                Projet p = projetService.trouverProjetParId(t.getIdProjet());
                if (p != null) {
                    nomProjet = p.getNom();
                } else {
                    nomProjet = "Projet inconnu";
                }
            }
            Object[] ligne = {
                    t.getId(),              // Colonne 0 : ID
                    t.getTitre(),           // Colonne 1 : Titre
                    t.getPriorite(),        // Colonne 2 : Priorité (enum)
                    t.getStatut(),          // Colonne 3 : Statut (enum)
                    nomProjet,              // Colonne 4 : Nom du projet
                    t.getDateEcheance()     // Colonne 5 : Date d'échéance (peut être null)
            };
            tableModel.addRow(ligne);
        }
        // Remplir le tableModel
    }

}
