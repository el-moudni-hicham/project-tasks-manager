package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Materiele;

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
                m.setQuantite(rs.getInt("QUANTITE"));
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
                m.setQuantite(rs.getInt("QUANTITE"));
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
            PreparedStatement pstm=connection.prepareStatement("insert into MATERIELLE(NOM,CARACTERISTIQUE,QUANTITE) "+
                  "values(?,?,?)");
            pstm.setString(1,o.getNom());
            pstm.setString(2,o.getCaracteristique());
            pstm.setInt(3,o.getQuantite());
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
            PreparedStatement pstm=connection.prepareStatement("update MATERIELLE set NOM=?,CARACTERISTIQUE=?,QUANTITE=? where ID=? ");
            pstm.setString(1,o.getNom());
            pstm.setString(2,o.getCaracteristique());
            pstm.setInt(3,o.getQuantite());
            pstm.setInt(4,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return o;
    }
}
