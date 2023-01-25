package ma.enset.projet.dao.entites;

import java.io.Serializable;
import java.util.List;

public class Projet implements Serializable {
    private String nom ;
    private User responsable;
    private List<Tache> taches;
}
