package fh.helloAngular.dao;

import fh.helloAngular.domain.User;

import java.util.List;

/**
 * Created by filip on 26.3.15.
 */
public interface UserDao {
    public void createUser(User user);

    public User getUser(Long id);

    public List<User> getAllUsers();

    public void updateUser(User user);

    public void deleteUser(User user);
}
