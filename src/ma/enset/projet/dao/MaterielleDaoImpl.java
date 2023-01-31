package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Materiele;
import ma.enset.projet.dao.entites.ResourceHumaine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterielleDaoImpl implements MaterielleDao{
    @Override
    public List<Materiele> findAll() {
        Connection connection=SingletonConnexionDB.getConnection();
        List<Materiele> materieles=new ArrayList<>();
        try {
            PreparedStatement stm=connection.prepareStatement("select * from MATERIELLE ");
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
                Materiele m=new Materiele();
                m.setId(rs.getInt("ID"));
                m.setNom(rs.getString("NOM"));
                m.setCaracteristique(rs.getString("CARACTERISTIQUE"));
                materieles.add(m);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return materieles;
    }

    @Override
    public Materiele findOne(int id) {
        Connection connection=SingletonConnexionDB.getConnection();
        Materiele m=new Materiele();
        try {
            PreparedStatement stm= connection.prepareStatement("select * from MATERIELLE where ID=?");
            stm.setInt(1,id);
            ResultSet rs=stm.executeQuery();
            if(rs.next()){
                m.setId(rs.getInt("ID"));
                m.setNom(rs.getString("NOM"));
                m.setCaracteristique(rs.getString("CARACTERISTIQUE"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public Materiele save(Materiele o) {
        Connection connection=SingletonConnexionDB.getConnection();
        try{
            PreparedStatement pstm=connection.prepareStatement("insert into MATERIELLE(NOM,CARACTERISTIQUE) "+
                  "values(?,?)");
            pstm.setString(1,o.getNom());
            pstm.setString(2,o.getCaracteristique());
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(Materiele o) {
        try{
            Connection connection=SingletonConnexionDB.getConnection();
            PreparedStatement pstm=connection.prepareStatement("delete from MATERIELLE where ID=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    @Override
    public Materiele update(Materiele o) {
        Connection connection=SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=connection.prepareStatement("update MATERIELLE set NOM=?,CARACTERISTIQUE=? where ID=? ");
            pstm.setString(1,o.getNom());
            pstm.setString(2,o.getCaracteristique());
            pstm.setInt(3,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return o;
    }

    @Override
    public List<Materiele> findByMotCle(String mc) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Materiele> mats = new ArrayList();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from MATERIELLE where NOM LIKE ? OR caracteristique LIKE ?");
            pstm.setString(1, "%" + mc + "%");
            pstm.setString(2, "%" + mc + "%");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Materiele mat = new Materiele();
                mat.setId(rs.getInt(1));
                mat.setNom(rs.getString(2));
                mat.setCaracteristique(rs.getString(3));
                mats.add(mat);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return mats;
    }

    @Override
    public int countMat() {
        Connection connection = SingletonConnexionDB.getConnection();
        int number = 0;
        try {
            PreparedStatement pstm = connection.prepareStatement("select count(*) from MATERIELLE");
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
