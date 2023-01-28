package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.ResourceHumaine;

import java.util.List;

public interface ResourceHumaineDao extends Dao<ResourceHumaine> {
    List<ResourceHumaine> findByMotCle(String mc);
    int countRh();
    void updateRh(int id, String nom,String prenom,String fonction,String email,String telephone);
    String getName(int id);
    void updateLogin(int id, String username, String password);
}
