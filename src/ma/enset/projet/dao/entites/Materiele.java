package ma.enset.projet.dao.entites;

import java.io.Serializable;

public class Materiele implements Serializable {
    private int id;
    private String nom;
    private String caracteristique;
    private int quantite;

    public Materiele() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Materiele(int id, String nom, String caracteristique, int quantite) {
        this.id = id;
        this.nom = nom;
        this.caracteristique = caracteristique;
        this.quantite = quantite;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Materiele{" +
                "nom='" + nom + '\'' +
                ", caracteristique='" + caracteristique + '\'' +
                ", quantite=" + quantite +
                '}';
    }
}
