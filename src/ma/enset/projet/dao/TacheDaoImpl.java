package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Tache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TacheDaoImpl implements TacheDao{
    @Override
    public List<Tache> findAll() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Tache> taches = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from TACHES");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Tache t = new Tache();
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setDescription(rs.getString(3));
                t.setDate_debut(rs.getString(4));
                t.setDate_fin(rs.getString(5));
                t.setEtat(rs.getString(6));



                taches.add(t);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return taches;
    }


    @Override
    public Tache findOne(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        Tache t = new Tache();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from TACHES where ID=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setDescription(rs.getString(3));
                t.setDate_debut(rs.getString(4));
                t.setDate_fin(rs.getString(5));
                t.setEtat(rs.getString(6));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return t;
    }

    @Override
    public Tache save(Tache o) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into TACHES(NOM,DESCRIPTION,DATE_DEBUT,DATE_FIN,ETAT) values (?,?,?,?,?)");
            pstm.setString(1, o.getNom());
            pstm.setString(2, o.getDescription());
            pstm.setString(3, o.getDate_debut());
            pstm.setString(4, o.getDate_fin());
            pstm.setString(5, o.getEtat());

            pstm.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(Tache o) {
        try {
            Connection connection = SingletonConnexionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM TACHES WHERE ID=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public Tache update(Tache o) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into TACHES(NOM,DESCRIPTION,DATE_DEBUT,DATE_FIN,ETAT) values (?,?,?,?,?)");
            pstm.setString(1, o.getNom());
            pstm.setString(2, o.getDescription());
            pstm.setString(3, o.getDate_debut());
            pstm.setString(4, o.getDate_fin());
            pstm.setString(5, o.getEtat());
            pstm.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return o;

    }

    @Override
    public List<Tache> findByMotCle(String mc) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Tache> taches = new ArrayList();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from TACHES where NOM LIKE ?");
            pstm.setString(1, "%" + mc + "%");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Tache t = new Tache();
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setDescription(rs.getString(3));
                t.setDate_debut(rs.getString(4));
                t.setDate_fin(rs.getString(5));
                t.setEtat(rs.getString(6));

                taches.add(t);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return taches;
    }
}
