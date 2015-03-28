package fh.helloAngular.service;

import fh.helloAngular.dto.UserDto;

import java.util.List;

/**
 * Created by filip on 28.3.15.
 */
public interface UserService {
    public void createUser(UserDto user);

    public UserDto getUser(Long id);

    public List<UserDto> getAllUsers();

    public void updateUser(UserDto user);

    public void deleteUser(UserDto user);

    public void deleteAllUsers();
}
