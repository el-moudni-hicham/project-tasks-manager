package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Projet;

import java.util.List;

public interface ProjetDao extends Dao<Projet>{
    List<Projet> findByMotCle(String mc);

}
