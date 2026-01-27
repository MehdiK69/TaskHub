package services;

import models.Projet;

import java.util.ArrayList;

public class ProjetService {

    private ArrayList<Projet> projets;

    public ProjetService(){
        projets = new ArrayList<>();
    }

    public void ajouterProjet(Projet projet){
        projets.add(projet);
    }

    public ArrayList<Projet> listerProjets(){
        return projets;
    }

    public Projet trouverProjetParId(int id){
        for (Projet projet : projets){
            if (projet.getId() == id)
                return projet;
        }
        return null;
    }

    public boolean supprimerProjet(int id){
        Projet projet = trouverProjetParId(id);
        if (projet != null) {
            projets.remove(projet);
            return true;
        }
        return false;
    }

    public int getNombreProjets(){
        return projets.size();
    }
}
