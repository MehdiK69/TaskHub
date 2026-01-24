package models;
import enums.*;
import java.time.LocalDate;

/** Classe tache qui va permettre de créer un objet Tache et de le manipuler
 * @author mehdi.khattab
 * */

public class Tache {
    private int id;
    private String titre;
    private String description;
    private Priorite priorite;
    private Statut statut;
    private LocalDate dateCreation;
    private LocalDate dateEcheance;
    private int idProjet;
    private String categorie;
    private boolean terminee;

    public Tache(int id, String titre, String description, Priorite priorite, Statut statut, LocalDate dateCreation, LocalDate dateEcheance, int idProjet, String categorie,boolean terminee) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.priorite = priorite;
        this.statut = statut;
        this.dateCreation = dateCreation;
        this.dateEcheance = dateEcheance;
        this.idProjet = idProjet;
        this.categorie = categorie;
        this.terminee = terminee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean getTerminee() {
        return terminee;
    }

    public void setTerminee(boolean terminee) {
        this.terminee = terminee;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", priorite=" + priorite +
                ", statut=" + statut +
                ", dateCreation=" + dateCreation +
                ", dateEcheance=" + dateEcheance +
                ", idProjet=" + idProjet +
                ", categorie='" + categorie + '\'' +
                ", terminee=" + terminee +
                '}';
    }
}
