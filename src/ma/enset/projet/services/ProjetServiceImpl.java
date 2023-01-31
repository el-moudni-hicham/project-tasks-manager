package ma.enset.projet.services;

import ma.enset.projet.dao.ProjetDao;
import ma.enset.projet.dao.entites.Projet;

import java.util.List;

public class ProjetServiceImpl implements ProjetService{
    ProjetDao pd;

    public ProjetServiceImpl(ProjetDao pd) {
        this.pd = pd;
    }

    @Override
    public List<Projet> getAllProjects() {
        return pd.findAll();
    }

    @Override
    public void addProjet(Projet prj) {
        pd.save(prj);
    }

    @Override
    public void deleteProjet(int id) {
        pd.deletePrj(id);
    }

    @Override
    public Projet getProjetById(int id) {
        return pd.findOne(id);
    }

    @Override
    public int countProjects() {
        return pd.countProjects();
    }
}
