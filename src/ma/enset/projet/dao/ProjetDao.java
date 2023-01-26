package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.User;

import java.util.List;

public interface ProjetDao extends Dao<Projet>{
    List<Projet> findByMotCle(String mc);

}
