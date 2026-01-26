package data;
import java.io.*;

import enums.Priorite;
import enums.Statut;
import models.Tache;
import services.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataManager {

    private static final String FICHIER_TACHES_DEFAUT = "data/taches.txt";

    public static void sauvegarderTaches(ArrayList<Tache> taches) throws IOException {
        File dossier = new File("data");
        if (!dossier.exists()){
            dossier.mkdir();  // Créer le dossier
        }
        FileWriter fw = new FileWriter(FICHIER_TACHES_DEFAUT);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Tache t : taches){
            String ligne = t.getId() + ";" +
                    t.getTitre() + ";" +
                    t.getDescription() + ";" +
                    t.getPriorite() + ";" +
                    t.getStatut()+ ";" +
                    t.getDateCreation() + ";" +
                    (t.getDateEcheance() != null ? t.getDateEcheance() : "pas d'échéance") + ";" +
                    t.getIdProjet() + ";" +
                    t.getCategorie() + ";" +
                    t.getTerminee();
            bw.write(ligne);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    public static ArrayList<Tache> chargerTaches() throws IOException {
        File fichier = new File(FICHIER_TACHES_DEFAUT);
        if (!fichier.exists()) {
            return new ArrayList<>();  // Retourner liste vide si pas de fichier
        }
        FileReader fr = new FileReader(fichier);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Tache> taches = new ArrayList<>();
        String ligne;
        while ((ligne = br.readLine()) != null) {
            // Découper la ligne
            String[] parties = ligne.split(";");

            // Extraire chaque info
            int id = Integer.parseInt(parties[0]);
            String titre = parties[1];
            String description = parties[2];
            Priorite priorite = Priorite.valueOf(parties[3]);
            Statut statut = Statut.valueOf(parties[4]);
            LocalDate dateCreation = LocalDate.parse(parties[5]);
            LocalDate dateEcheance = parties[6].isEmpty() ? null : LocalDate.parse(parties[6]);
            int idProjet = Integer.parseInt(parties[7]);
            String idCategorie = parties[8];
            Boolean idTerminee = Boolean.parseBoolean(parties[9]);


            // Créer l'objet Tache
            Tache t = new Tache(id ,titre, description, priorite, statut, dateCreation, dateEcheance, idProjet, idCategorie, idTerminee);

            // Ajouter à la liste
            taches.add(t);
        }
        br.close();
        fr.close();
        return taches;
    }
}
