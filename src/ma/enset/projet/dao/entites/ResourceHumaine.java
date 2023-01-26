package ma.enset.projet.dao.entites;

import java.io.Serializable;

public class ResourceHumaine implements Serializable {
    private String nom;
    private String prenom;
    private String fonction;
    private String date_naissance;
    private String telephone;
    private String email;
    private String date_insc;

    public ResourceHumaine() {
    }

    public ResourceHumaine(String nom, String prenom, String fonction, String date_naissance, String telephone, String email, String date_insc) {
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
        this.date_naissance = date_naissance;
        this.telephone = telephone;
        this.email = email;
        this.date_insc = date_insc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_insc() {
        return date_insc;
    }

    public void setDate_insc(String date_insc) {
        this.date_insc = date_insc;
    }
}
