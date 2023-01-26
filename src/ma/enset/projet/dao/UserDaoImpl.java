package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    public User checkUser(String username,String password){
        Connection connection = SingletonConnexionDB.getConnection();
        User user = null;
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?");
            pstm.setString(1,username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setInsc_date(rs.getString(4));
                user.setRole(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public List<User> findAll() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from USERS");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setInsc_date(rs.getString(4));
                u.setRole(rs.getString(5));


                users.add(u);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findOne(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        User u = new User();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from USERS where ID=?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setInsc_date(rs.getString(4));
                u.setRole(rs.getString(5));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return u;
    }

    @Override
    public User save(User o) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into USERS(USERNAME,PASSWORD,INSC_DATE,ROLE) values (?,?,?,?)");
            pstm.setString(1, o.getUsername());
            pstm.setString(2, o.getPassword());
            pstm.setString(3, o.getInsc_date());
            pstm.setString(4, o.getRole());
            pstm.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean delete(User o) {
        try {
            Connection connection = SingletonConnexionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM USERS WHERE ID=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public User update(User o) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into USERS(USERNAME,PASSWORD,INSC_DATE,ROLE) values (?,?,?,?,?,?)");
            pstm.setString(1, o.getUsername());
            pstm.setString(2, o.getPassword());
            pstm.setString(3, o.getInsc_date());
            pstm.setString(4, o.getRole());
            pstm.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return o;

    }


    @Override
    public List<User> findByMotCle(String mc) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<User> users = new ArrayList();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from USERS where USERNAME LIKE ?");
            pstm.setString(1, "%" + mc + "%");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setInsc_date(rs.getString(4));
                u.setRole(rs.getString(5));

                users.add(u);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;

    }
}
