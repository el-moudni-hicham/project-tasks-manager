package ma.enset.projet.services;

import ma.enset.projet.dao.ResourceHumaineDao;
import ma.enset.projet.dao.entites.ResourceHumaine;

import java.util.List;

public class RhServiceImpl implements RhService{
    ResourceHumaineDao rhd;

    public RhServiceImpl(ResourceHumaineDao rhd) {
        this.rhd = rhd;
    }

    @Override
    public void addRh(ResourceHumaine rh) {
        rhd.save(rh);
    }

    @Override
    public void deleteRh(ResourceHumaine rh) {
        rhd.delete(rh);
    }

    @Override
    public List<ResourceHumaine> searchRhByQuery(String mc) {
        return rhd.findByMotCle(mc);
    }

    @Override
    public ResourceHumaine updateRh(ResourceHumaine rh) {
        return rhd.update(rh);
    }

    @Override
    public ResourceHumaine getRhById(int id) {
        return rhd.findOne(id);
    }

    @Override
    public List<ResourceHumaine> getAllRhs() {
        return rhd.findAll();
    }

    @Override
    public int countRh() {
        return rhd.countRh();
    }
}
