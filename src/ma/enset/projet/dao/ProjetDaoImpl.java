package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.dao.entites.Tache;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ProjetDaoImpl implements ProjetDao{
    ResourceHumaineDao rhd = new ResourceHumaineDaoImpl();
    @Override
    public List<Projet> findAll() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Projet> projets = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from projet");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Projet p = new Projet();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setResponsable(rhd.findOne(rs.getInt(3)));
                p.setDat_debut(rs.getDate(4));
                p.setDat_fin(rs.getDate(5));
                p.setEstimated_time(rs.getInt(6));
                projets.add(p);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return projets;
    }

    @Override
    public Projet findOne(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        Projet p = new Projet();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from PROJET where ID=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setResponsable(rhd.findOne(rs.getInt(3)));
                p.setDat_debut(rs.getDate(4));
                p.setDat_fin(rs.getDate(5));
                p.setEstimated_time(rs.getInt(6));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return p;
    }

    @Override
    public Projet save(Projet o) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into PROJET(NOM,ID_RES,dat_debut,dat_fin,estimated_time) values (?,?,?,?,?)");
            pstm.setString(1, o.getNom());
            pstm.setInt(2,o.getResponsable().getId());

            java.util.Date date = o.getDat_debut();
            pstm.setDate(3,new Date(date.getYear()-1900,date.getMonth()-1,date.getDate()));

            java.util.Date date1 = o.getDat_debut();
            pstm.setDate(4,new Date(date1.getYear()-1900,date1.getMonth()-1,date1.getDate()));

            pstm.setInt(5,o.getEstimated_time());

            pstm.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(Projet o) {
        try {
            Connection connection = SingletonConnexionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM PROJETS WHERE ID=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePrj(int id) {
        try {
            Connection connection = SingletonConnexionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM PROJET WHERE ID=?");
            pstm.setInt(1,id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    @Override
    public Projet update(Projet o) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into PROJETS(NOM,RESPONSABLE,TACHES) values (?,?,?)");
            pstm.setString(1, o.getNom());
            pstm.setObject(2, o.getResponsable());
            pstm.setArray(3, (Array) o.getTaches());
            pstm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public List<Projet> findByMotCle(String mc) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Projet> projets = new ArrayList();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from PROJETS where NOM LIKE ?");
            pstm.setString(1, "%" + mc + "%");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Projet p = new Projet();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setResponsable(rhd.findOne(rs.getInt(3)));
                p.setTaches((List<Tache>) rs.getArray(4));

                projets.add(p);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return projets;
    }

    @Override
    public int countProjects() {
        Connection connection = SingletonConnexionDB.getConnection();
        int number = 0;
        try {
            PreparedStatement pstm = connection.prepareStatement("select count(*) from PROJET");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return number;
    }
}
