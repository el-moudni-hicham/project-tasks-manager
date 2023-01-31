package ma.enset.projet.dao.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Projet implements Serializable {
    private int id;
    private String nom ;
    private ResourceHumaine responsable;
    private List<Tache> taches;
    private Date dat_debut;
    private Date dat_fin;
    private int estimated_time;

    public Projet() {
    }

    public Projet(int id, String nom, ResourceHumaine responsable, Date dat_debut, Date dat_fin, int estimated_time) {
        this.id = id;
        this.nom = nom;
        this.responsable = responsable;
        this.dat_debut = dat_debut;
        this.dat_fin = dat_fin;
        this.estimated_time = estimated_time;
    }

    public Date getDat_debut() {
        return dat_debut;
    }

    public void setDat_debut(Date dat_debut) {
        this.dat_debut = dat_debut;
    }

    public Date getDat_fin() {
        return dat_fin;
    }

    public void setDat_fin(Date dat_fin) {
        this.dat_fin = dat_fin;
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

    public ResourceHumaine getResponsable() {
        return responsable;
    }

    public void setResponsable(ResourceHumaine responsable) {
        this.responsable = responsable;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public int getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(int estimated_time) {
        this.estimated_time = estimated_time;
    }

    @Override
    public String toString() {
        return nom;
    }
}
