package ma.enset.projet.services;

import ma.enset.projet.dao.UserDao;
import ma.enset.projet.dao.entites.User;

import java.util.List;

public class UserServiceImp implements UserService {
    UserDao udao;

    public UserServiceImp(UserDao cdao) {
        this.udao = cdao;
    }
    @Override
    public void addUser(User u) {
     udao.save(u);
    }


    @Override
    public void deleteUser(User u) {
        udao.delete(u);
    }

    @Override
    public List<User> getUsersParMC(String mc) {
        return udao.findByMotCle(mc);

    }
    @Override
    public User UpdateUser(User u) {
        return udao.update(u);
    }

    @Override
    public List<User> getAllUsers() {
        return udao.findAll();
    }


}
