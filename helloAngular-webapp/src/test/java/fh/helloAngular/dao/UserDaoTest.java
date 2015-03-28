package fh.helloAngular.dao;

import fh.helloAngular.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by filip on 28.3.15.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/applicationContext.xml")
@Transactional
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    @Before
    public void setUp() {
        em.createNativeQuery("TRUNCATE TABLE User").executeUpdate();
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setEmail("fake@gmail.com");
        user.setUserName("user101");
        userDao.createUser(user);
        User userFromDb = em.find(User.class, user.getId());
        assertNotNull(user);
        assertEquals(user, userFromDb);
        try {
            userDao.createUser(null);
        } catch (DataAccessException ex) {
            return;
        }
        fail("created null user");
    }

    @Test
    public void getUser() {
        User user = new User();
        user.setEmail("fake@gmail.com");
        user.setUserName("user101");
        User user2 = new User();
        user.setEmail("fake2@yahoo.com");
        user.setUserName("user202");
        em.persist(user);
        em.persist(user2);
        assertEquals(userDao.getUser(user.getId()), em.find(User.class, user.getId()));
        assertEquals(userDao.getUser(user2.getId()), em.find(User.class, user2.getId()));
        assertEquals(userDao.getAllUsers().size(), 2);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setEmail("fake@gmail.com");
        user.setUserName("user101");
        em.persist(user);
        user.setUserName("user102");
        userDao.updateUser(user);
        User userFromDb = em.find(User.class, user.getId());
        assertEquals(user.getUserName(), userFromDb.getUserName());
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setEmail("fake@gmail.com");
        user.setUserName("user101");
        User user2 = new User();
        user.setEmail("fake2@yahoo.com");
        user.setUserName("user202");
        em.persist(user);
        em.persist(user2);
        assertEquals(em.createQuery("SELECT u FROM User u", User.class).getResultList().size(), 2);
        userDao.deleteUser(user);
        assertNull(userDao.getUser(user.getId()));
        assertEquals(em.createQuery("SELECT u FROM User u", User.class).getResultList().size(), 1);
        userDao.deleteUser(user2);
        assertNull(userDao.getUser(user2.getId()));
        assertEquals(em.createQuery("SELECT u FROM User u", User.class).getResultList().size(), 0);
        try {
            userDao.deleteUser(null);
        } catch (DataAccessException ex) {
            return;
        }
        fail("deleting null user did not throw exception");
    }
}
