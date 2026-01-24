package services;
import models.Tache;
import enums.*;
import java.util.ArrayList;

public class TacheService {
    private ArrayList<Tache> taches;

    public TacheService(){
        this.taches=new ArrayList<>();
    }

    public void ajouterTache(Tache tache){
        taches.add(tache);
        System.out.println("✓ Tâche ajoutée : " + tache.getTitre());
    }

    public boolean tacheExiste(Tache tache){
        for (Tache t : taches) {
            if(t == tache){
                return true;
            }
        }
        return false;
    }

    // Lister toutes les tâches
    public ArrayList<Tache> listerTaches() {
        return taches;
    }

    public void afficherTaches() {
        if (taches.isEmpty()) {
            System.out.println("Aucune tâche.");
            return;
        }

        System.out.println("\n=== LISTE DES TÂCHES ===");
        for (Tache t : taches) {
            System.out.println(t);
        }
        System.out.println("========================\n");
    }

    // Trouver une tâche par son ID
    public Tache trouverTacheParId(int id){
        for(Tache t : taches){
            if(t.getId() == id){
                return t;
            }
        }
        System.out.println("Cette tâche n'existe pas.");
        return null;
    }

    // Marquer une tâche comme terminée
    public void marquerTerminee(int id){
        Tache tache = trouverTacheParId(id);
        if(tache != null){
            tache.setTerminee(true);
            System.out.println("✓ Tâche #" + id + " marquée comme terminée");
        } else {
            System.out.println("✗ Tâche #" + id + " non trouvée");
        }
    }

    public void supprimerTache(Tache tache){
        taches.remove(tache);
    }

    // Obtenir le nombre total de tâches
    public int getNombreTaches(){
        return this.taches.size();
    }

    public int getNombreTachesTerminees(){
        int compteur=0;
        for(Tache t : taches){
            if(t.getTerminee()){
                compteur++;
            }
        }
        return compteur;
    }

}
