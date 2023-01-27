package ma.enset.projet.dao.entites;

import java.io.Serializable;

public class ResourceHumaine implements Serializable {
    private int id;
    private String nom;
    private String prenom;
    private String fonction;
    private String date_naissance;
    private String telephone;
    private String email;
    private String date_insc;

    //User
    private String username;
    private String password;
    private String role;

    public ResourceHumaine() {
    }

    public ResourceHumaine(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ResourceHumaine(int id, String nom, String prenom, String fonction, String date_naissance, String telephone, String email, String date_insc, String username, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
        this.date_naissance = date_naissance;
        this.telephone = telephone;
        this.email = email;
        this.date_insc = date_insc;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public ResourceHumaine(int id, String nom, String prenom, String fonction, String date_naissance, String telephone, String email, String date_insc) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
        this.date_naissance = date_naissance;
        this.telephone = telephone;
        this.email = email;
        this.date_insc = date_insc;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
