package ma.enset.projet.dao.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Projet implements Serializable {
    private int id;
    private String nom ;
    private User responsable;
    private List<Tache> taches;
    private Date dat_debut;
    private Date dat_fin;

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

    public User getResponsable() {
        return responsable;
    }

    public void setResponsable(User responsable) {
        this.responsable = responsable;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}
