package fh.helloAngular.service;

import fh.helloAngular.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by filip on 28.3.15.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Before
    public void setUp() {
        userService.deleteAllUsers();
    }

    @Test
    public void createUserDto() {
        UserDto user = new UserDto();
        user.setEmail("fake@gmail.com");
        user.setUserName("user101");
        userService.createUser(user);
        assertEquals(userService.getUser(user.getId()), user);
        try {
            userService.createUser(null);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("exception not thrown");
    }

    @Test
    public void updateUserDto() {
        UserDto user = new UserDto();
        user.setEmail("fake@gmail.com");
        user.setUserName("user101");
        userService.createUser(user);
        user.setUserName("user202");
        userService.updateUser(user);
        assertEquals(userService.getUser(user.getId()).getUserName(), user.getUserName());
        try {
            userService.updateUser(null);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("exception not thrown");
    }

    @Test
    public void deleteUserDto() {
        UserDto user = new UserDto();
        user.setEmail("fake@gmail.com");
        user.setUserName("user101");
        userService.createUser(user);
        assertEquals(userService.getUser(user.getId()), user);
        userService.deleteUser(user);
        assertEquals(userService.getAllUsers().size(), 0);
    }
}
