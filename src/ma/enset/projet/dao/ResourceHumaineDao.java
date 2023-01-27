package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.ResourceHumaine;

import java.util.List;

public interface ResourceHumaineDao extends Dao<ResourceHumaine> {
    List<ResourceHumaine> findByMotCle(String mc);
    int countRh();
}
