package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.ResourceHumaine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ResourceHumaineDaoImpl implements ResourceHumaineDao{
    @Override
    public List<ResourceHumaine> findAll() {
        return null;
    }

    @Override
    public ResourceHumaine findOne(int id) {
        return null;
    }

    @Override
    public ResourceHumaine save(ResourceHumaine o) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into RESOURCES_HUMAINE(NOM,PRENOM,FONCTION,DATE_NAISSANCE,TELEPHONE,EMAIL,DATE_INSC) values (?,?,?,?,?,?,?)");
            pstm.setString(1, o.getNom());
            pstm.setString(2, o.getPrenom());
            pstm.setString(3, o.getFonction());
            pstm.setString(4, o.getDate_naissance());
            pstm.setString(5, o.getTelephone());
            pstm.setString(6, o.getEmail());
            pstm.setString(7, o.getDate_insc());
            pstm.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(ResourceHumaine o) {
        return false;
    }

    @Override
    public ResourceHumaine update(ResourceHumaine o) {
        return null;
    }
}
