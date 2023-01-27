package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.ResourceHumaine;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceHumaineDaoImpl implements ResourceHumaineDao{

    public ResourceHumaine checkRH(String username, String password){
        Connection connection = SingletonConnexionDB.getConnection();
        ResourceHumaine rh = null;
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM RESOURCES_HUMAINE WHERE USERNAME=? AND PASSWORD=?");
            pstm.setString(1,username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                rh = new ResourceHumaine();
                rh.setId(rs.getInt(1));
                rh.setNom(rs.getString(2));
                rh.setPrenom(rs.getString(3));
                rh.setFonction(rs.getString(4));
                rh.setDate_naissance(rs.getString(5));
                rh.setTelephone(rs.getString(6));
                rh.setEmail(rs.getString(7));
                rh.setDate_insc(rs.getString(8));
                rh.setUsername(rs.getString(9));
                rh.setPassword(rs.getString(10));
                rh.setRole(rs.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rh;
    }
    @Override
    public List<ResourceHumaine> findAll() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<ResourceHumaine> users = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from RESOURCES_HUMAINE");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ResourceHumaine rh = new ResourceHumaine();
                rh.setId(rs.getInt(1));
                rh.setNom(rs.getString(2));
                rh.setPrenom(rs.getString(3));
                rh.setFonction(rs.getString(4));
                rh.setDate_naissance(rs.getString(5));
                rh.setTelephone(rs.getString(6));
                rh.setEmail(rs.getString(7));
                rh.setDate_insc(rs.getString(8));
                rh.setUsername(rs.getString(9));
                rh.setPassword(rs.getString(10));
                rh.setRole(rs.getString(11));
                users.add(rh);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public ResourceHumaine findOne(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        ResourceHumaine rh = new ResourceHumaine();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from RESOURCES_HUMAINE where ID=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                rh = new ResourceHumaine();
                rh.setId(rs.getInt(1));
                rh.setNom(rs.getString(2));
                rh.setPrenom(rs.getString(3));
                rh.setFonction(rs.getString(4));
                rh.setDate_naissance(rs.getString(5));
                rh.setTelephone(rs.getString(6));
                rh.setEmail(rs.getString(7));
                rh.setDate_insc(rs.getString(8));
                rh.setUsername(rs.getString(9));
                rh.setPassword(rs.getString(10));
                rh.setRole(rs.getString(11));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return rh;
    }

    @Override
    public ResourceHumaine save(ResourceHumaine o) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into RESOURCES_HUMAINE(NOM,PRENOM,FONCTION,DATE_NAISSANCE,TELEPHONE,EMAIL,DATE_INSC,USERNAME,PASSWORD,ROLE) values (?,?,?,?,?,?,?,?,?,?)");
            pstm.setString(1, o.getNom());
            pstm.setString(2, o.getPrenom());
            pstm.setString(3, o.getFonction());
            pstm.setString(4, o.getDate_naissance());
            pstm.setString(5, o.getTelephone());
            pstm.setString(6, o.getEmail());
            pstm.setString(7, o.getDate_insc());
            pstm.setString(8, o.getUsername());
            pstm.setString(9, o.getPassword());
            pstm.setString(10, o.getRole());
            pstm.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(ResourceHumaine o) {
        try {
            Connection connection = SingletonConnexionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM RESOURCES_HUMAINE WHERE ID=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public ResourceHumaine update(ResourceHumaine o) {
        return null;
    }

    @Override
    public List<ResourceHumaine> findByMotCle(String mc) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<ResourceHumaine> users = new ArrayList();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from RSOURCES_HUMAINE where NOM LIKE ?");
            pstm.setString(1, "%" + mc + "%");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ResourceHumaine rh = new ResourceHumaine();
                rh.setId(rs.getInt(1));
                rh.setNom(rs.getString(2));
                rh.setPrenom(rs.getString(3));
                rh.setFonction(rs.getString(4));
                rh.setDate_naissance(rs.getString(5));
                rh.setTelephone(rs.getString(6));
                rh.setEmail(rs.getString(7));
                rh.setDate_insc(rs.getString(8));
                rh.setUsername(rs.getString(9));
                rh.setPassword(rs.getString(10));
                rh.setRole(rs.getString(11));
                users.add(rh);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
}
