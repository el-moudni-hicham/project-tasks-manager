package ma.enset.projet.dao.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Tache implements Serializable {
    private int id;
    private String nom;
    private String description;
    private Date date_debut;
    private Date date_fin;
    private ResourceHumaine responsable;
    private Projet projet;
    private List<Materiele> materielles;
    private Boolean etat;
    private String color;

    public Tache() {
    }

    public Tache(int id, String nom, String description, Date date_debut, Date date_fin, ResourceHumaine responsable, Projet projet, String color) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.responsable = responsable;
        this.projet = projet;
        this.color = color;

    }

    public List<Materiele> getMaterielles() {
        return materielles;
    }

    public void setMaterielles(List<Materiele> materielles) {
        this.materielles = materielles;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public ResourceHumaine getResponsable() {
        return responsable;
    }

    public void setResponsable(ResourceHumaine responsable) {
        this.responsable = responsable;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
