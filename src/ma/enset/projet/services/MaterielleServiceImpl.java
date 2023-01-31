package ma.enset.projet.services;

import ma.enset.projet.dao.MaterielleDao;
import ma.enset.projet.dao.entites.Materiele;

import java.util.List;

public class MaterielleServiceImpl implements MaterielleService{
    MaterielleDao md;

    public MaterielleServiceImpl(MaterielleDao md) {
        this.md = md;
    }

    @Override
    public void addMat(Materiele mat) {
        md.save(mat);
    }

    @Override
    public void deleteMat(Materiele mat) {
        md.delete(mat);
    }

    @Override
    public Materiele updateMat(Materiele mat) {
        return md.update(mat);
    }

    @Override
    public List<Materiele> searchMatByQuery(String mc) {
        return md.findByMotCle(mc);
    }

    @Override
    public Materiele getMatById(int id) {
        return md.findOne(id);
    }

    @Override
    public List<Materiele> getAllMats() {
        return md.findAll();
    }

    @Override
    public int countMat() {
        return md.countMat();
    }
}
