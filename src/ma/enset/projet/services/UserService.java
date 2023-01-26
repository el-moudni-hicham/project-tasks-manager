package ma.enset.projet.services;

import ma.enset.projet.dao.entites.User;

import java.util.List;

public interface UserService {
    void addUser(User u);
    void deleteUser(User u);
    List<User> getUsersParMC(String mc);
    User UpdateUser(User u);
    List<User> getAllUsers();

}
