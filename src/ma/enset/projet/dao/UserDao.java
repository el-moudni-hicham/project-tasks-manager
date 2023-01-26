package ma.enset.projet.dao;

import ma.enset.projet.dao.entites.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> findByMotCle(String mc);

}
