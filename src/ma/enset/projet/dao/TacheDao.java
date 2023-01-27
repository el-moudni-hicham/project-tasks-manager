package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Tache;

import java.util.List;

public interface TacheDao extends Dao<Tache>{
    List<Tache> findByMotCle(String mc);

}
