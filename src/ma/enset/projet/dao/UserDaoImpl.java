package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {
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


}
