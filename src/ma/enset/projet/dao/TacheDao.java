package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Tache;

import java.util.List;

public interface TacheDao extends Dao<Tache>{
    List<Tache> findByMotCle(String mc);
    List<Tache> tasksOfProject(int id);
    List<Tache> tasksOfUser(int id);
    int countTasks(int id);
    void updateTache(int id,Boolean statut);
    void addMatToTask(int idTask,int id_mat);
    List<Integer> TaskMats(int id);
}
