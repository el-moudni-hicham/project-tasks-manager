package ma.enset.projet.services;

import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.ResourceHumaine;

import java.util.List;

public interface ProjetService {
    List<Projet> getAllProjects();
    void addProjet(Projet prj);
    void deleteProjet(int id);
    Projet getProjetById(int id);
    int countProjects();
}
