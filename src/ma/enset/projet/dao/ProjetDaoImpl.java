package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.dao.entites.Tache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDaoImpl implements ProjetDao{
    private Class<? extends ma.enset.projet.dao.entites.ResourceHumaine> ResourceHumaine;

    @Override
    public List<Projet> findAll() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Projet> projets = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from PROJETS");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Projet p = new Projet();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setResponsable(rs.getObject(3, ResourceHumaine));
                p.setTaches((List<Tache>) rs.getArray(4));
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
            PreparedStatement pstm = connection.prepareStatement("select * from PROJETS where ID=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setResponsable(rs.getObject(3, ResourceHumaine));
                p.setTaches((List<Tache>) rs.getArray(4));
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
                p.setResponsable(rs.getObject(3,ResourceHumaine));
                p.setTaches((List<Tache>) rs.getArray(4));

                projets.add(p);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return projets;
    }
}
