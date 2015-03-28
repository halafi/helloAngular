package fh.helloAngular.service.impl;

import fh.helloAngular.dao.UserDao;
import fh.helloAngular.domain.User;
import fh.helloAngular.dto.UserDto;
import fh.helloAngular.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filip on 28.3.15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    DozerBeanMapper dozer;

    @Override
    public void createUser(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        User userEntity = dozer.map(user, User.class);
        userDao.createUser(userEntity);
        user.setId(userEntity.getId());
    }

    @Override
    public UserDto getUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return dozer.map(userDao.getUser(id), UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userDao.getAllUsers()) {
            users.add(dozer.map(user, UserDto.class));
        }
        return users;
    }

    @Override
    public void updateUser(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        userDao.updateUser(dozer.map(user, User.class));
    }

    @Override
    public void deleteUser(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        userDao.deleteUser(dozer.map(user, User.class));
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }
}
