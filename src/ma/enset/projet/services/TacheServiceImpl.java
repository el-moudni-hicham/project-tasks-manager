package ma.enset.projet.services;

import ma.enset.projet.dao.TacheDao;
import ma.enset.projet.dao.TacheDaoImpl;
import ma.enset.projet.dao.entites.Tache;

import java.util.List;

public class TacheServiceImpl implements TacheService{
    TacheDao td ;

    public TacheServiceImpl(TacheDao td) {
        this.td = td;
    }

    @Override
    public void addTache(Tache tache) {
        td.save(tache);
    }

    @Override
    public void deleleTache(Tache tache) {
        td.delete(tache);
    }

    @Override
    public List<Tache> tasksOfProject(int id) {
        return  td.tasksOfProject(id);
    }

    @Override
    public List<Tache> tasksOfUser(int id) {
        return td.tasksOfUser(id);
    }

    @Override
    public int countTasks(int id) {
        return td.countTasks(id);
    }

    @Override
    public void updateTache(int id, Boolean statut) {
        td.updateTache(id,statut);
    }

    @Override
    public void addMatToTask(int idTask, int id_mat) {
        td.addMatToTask(idTask,id_mat);
    }

    @Override
    public List<Integer> TaskMats(int id) {
        return td.TaskMats(id);
    }

}
