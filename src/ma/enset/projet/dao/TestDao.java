package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Materiele;
import ma.enset.projet.dao.entites.Projet;

import java.util.List;

public class TestDao {
    public static void main(String[] args) {
        ProjetDao pd = new ProjetDaoImpl();
        List<Projet> projetList = pd.findAll();
        for (Projet projet:projetList) {
            System.out.println(projet.getId()+projet.getNom()+projet.getDat_debut()+projet.getDat_fin()+projet.getResponsable().getNom());
        }
        Projet pdi = pd.findOne(1);
        System.out.println(pdi.getNom());

        MaterielleDao materielleDao = new MaterielleDaoImpl();
        List<Materiele> materieleList = materielleDao.findAll();
        for (Materiele projet:materieleList) {
            System.out.println(projet.getId()+projet.getNom()+projet.getCaracteristique());
        }

    }
}
