package ma.enset.projet.dao.entites;

import java.io.Serializable;

public class Materiele implements Serializable {
    private int id;
    private String nom;
    private String caracteristique;

    public Materiele() {
    }

    public Materiele(int id, String nom, String caracteristique) {
        this.id = id;
        this.nom = nom;
        this.caracteristique = caracteristique;
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

    public String getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }

    @Override
    public String toString() {
        return nom;
    }
}
