package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Materiele;


import java.util.List;

public interface MaterielleDao extends Dao<Materiele>{
    List<Materiele> findByMotCle(String mc);
    int countMat();
}
