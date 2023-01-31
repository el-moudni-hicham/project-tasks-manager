package ma.enset.projet.services;

import ma.enset.projet.dao.entites.Materiele;
import ma.enset.projet.dao.entites.ResourceHumaine;

import java.util.List;

public interface MaterielleService {
    void addMat(Materiele mat);
    void deleteMat(Materiele mat);
    Materiele updateMat(Materiele mat);
    List<Materiele> searchMatByQuery(String mc);
    Materiele getMatById(int id);
    List<Materiele> getAllMats();
    int countMat();
}
