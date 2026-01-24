package models;

import java.time.LocalDate;

public class Projet {

    private int id;
    private String nom;
    private String description;
    private String couleur;
    private LocalDate dateCreation;

    public Projet(int id, String nom, String description, String couleur, LocalDate dateCreation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.couleur = couleur;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getCouleur() {
        return couleur;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", couleur='" + couleur + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }


}
