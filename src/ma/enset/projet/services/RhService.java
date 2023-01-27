package ma.enset.projet.services;

import ma.enset.projet.dao.entites.ResourceHumaine;

import java.util.List;

public interface RhService {
    void addRh(ResourceHumaine rh);
    void deleteRh(ResourceHumaine rh);
    List<ResourceHumaine> searchRhByQuery(String mc);
    ResourceHumaine updateRh(ResourceHumaine rh);
    ResourceHumaine getRhById(int id);
    List<ResourceHumaine> getAllRhs();
}
