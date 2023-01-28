package ma.enset.projet.services;

import ma.enset.projet.dao.entites.ResourceHumaine;

import java.util.List;

public interface RhService {
    void addRh(ResourceHumaine rh);
    void deleteRh(ResourceHumaine rh);
    List<ResourceHumaine> searchRhByQuery(String mc);
    void updateRh(int id, String nom,String prenom,String fonction,String email,String telephone);
    void updateLofinInfo(int id, String username, String password);
    ResourceHumaine getRhById(int id);
    List<ResourceHumaine> getAllRhs();
    int countRh();
}
