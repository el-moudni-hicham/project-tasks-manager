package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.Tache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheDaoImpl implements TacheDao{
    ResourceHumaineDao rhd = new ResourceHumaineDaoImpl();
    ProjetDao pd = new ProjetDaoImpl();
    @Override
    public List<Tache> findAll() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Tache> taches = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from TACHE");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Tache t = new Tache();
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setDescription(rs.getString(3));
                t.setDate_debut(rs.getDate(4));
                t.setDate_fin(rs.getDate(5));
                t.setResponsable(rhd.findOne(rs.getInt(6)));
                t.setProjet(pd.findOne(rs.getInt(7)));
                t.setEtat(rs.getBoolean(8));

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
            PreparedStatement pstm = connection.prepareStatement("select * from TACHE where ID=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setDescription(rs.getString(3));
                t.setDate_debut(rs.getDate(4));
                t.setDate_fin(rs.getDate(5));
                t.setResponsable(rhd.findOne(rs.getInt(6)));
                t.setEtat(rs.getBoolean(7));
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
            PreparedStatement pstm = connection.prepareStatement("insert into TACHE(NOM,DESCRIPTION,DATE_DEBUT,DATE_FIN,id_respo,id_projet,color) values (?,?,?,?,?,?,?)");
            pstm.setString(1, o.getNom());
            pstm.setString(2, o.getDescription());
            java.util.Date date = o.getDate_debut();
            pstm.setDate(3,new Date(date.getYear()-1900,date.getMonth()-1,date.getDate()));

            java.util.Date date1 = o.getDate_fin();
            pstm.setDate(4,new Date(date1.getYear()-1900,date1.getMonth()-1,date1.getDate()));
            pstm.setInt(5,o.getResponsable().getId());
            pstm.setInt(6,o.getProjet().getId());
            pstm.setString(7,o.getColor());

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
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM TACHE WHERE ID=?");
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
            PreparedStatement pstm = connection.prepareStatement("insert into TACHES(NOM,DESCRIPTION,DATE_DEBUT,DATE_FIN,ETAT) values (?,?,?,?,?,?)");
            pstm.setString(1, o.getNom());
            pstm.setString(2, o.getDescription());
            java.util.Date date = o.getDate_debut();
            pstm.setDate(4,new Date(date.getYear()-1900,date.getMonth()-1,date.getDate()));

            java.util.Date date1 = o.getDate_fin();
            pstm.setDate(5,new Date(date1.getYear()-1900,date1.getMonth()-1,date1.getDate()));
            pstm.setInt(5,o.getResponsable().getId());
            pstm.setBoolean(6, o.getEtat());
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
                t.setDate_debut(rs.getDate(4));
                t.setDate_fin(rs.getDate(5));
                t.setResponsable(rhd.findOne(rs.getInt(6)));
                t.setEtat(rs.getBoolean(7));

                taches.add(t);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return taches;
    }

    @Override
    public List<Tache> tasksOfProject(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Tache> taches = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from TACHE where id_projet=?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Tache t = new Tache();
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setDescription(rs.getString(3));
                t.setDate_debut(rs.getDate(4));
                t.setDate_fin(rs.getDate(5));
                t.setResponsable(rhd.findOne(rs.getInt(6)));
                t.setProjet(pd.findOne(rs.getInt(7)));
                t.setEtat(rs.getBoolean(8));

                taches.add(t);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return taches;
    }

    @Override
    public List<Tache> tasksOfUser(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Tache> taches = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from TACHE where id_respo=?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Tache t = new Tache();
                t.setId(rs.getInt(1));
                t.setNom(rs.getString(2));
                t.setDescription(rs.getString(3));
                t.setDate_debut(rs.getDate(4));
                t.setDate_fin(rs.getDate(5));
                t.setResponsable(rhd.findOne(rs.getInt(6)));
                t.setProjet(pd.findOne(rs.getInt(7)));
                t.setEtat(rs.getBoolean(8));

                taches.add(t);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return taches;
    }

    @Override
    public int countTasks(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        int number = 0;
        try {
            PreparedStatement pstm = connection.prepareStatement("select count(*) from tache where id_respo = ?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return number;
    }

    @Override
    public void updateTache(int id, Boolean statut) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE tache SET etat=? WHERE ID=?");
            pstm.setInt(2,id);
            pstm.setBoolean(1,statut);
            pstm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addMatToTask(int idTask, int id_mat) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into TACHE_MATERIELLE(id_tache,id_materielle) values (?,?)");
            pstm.setInt(1,idTask);
            pstm.setInt(2,id_mat);

            pstm.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> TaskMats(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Integer> MatsId = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select id_materielle from TACHE_MATERIELLE where id_tache=?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                MatsId.add(rs.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return MatsId;
    }
}
