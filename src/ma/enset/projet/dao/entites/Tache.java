package ma.enset.projet.dao.entites;

import java.io.Serializable;
import java.util.Date;

public class Tache implements Serializable {
    private int id;
    private String nom;
    private String description;
    private Date date_debut;
    private Date date_fin;
    private String etat;


}
