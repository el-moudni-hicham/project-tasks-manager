package ma.enset.projet.services;

import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.Tache;

import java.util.List;

public interface TacheService {
    void addTache(Tache tache);
    void deleleTache(Tache tache);
    List<Tache> tasksOfProject(int id);
    List<Tache> tasksOfUser(int id);
    int countTasks(int id);
    void updateTache(int id,Boolean statut);
    void addMatToTask(int idTask,int id_mat);
    List<Integer> TaskMats(int id);
}
