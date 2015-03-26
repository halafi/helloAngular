package fh.helloAngular.dao.hibernateImpl;

import fh.helloAngular.dao.UserDao;
import fh.helloAngular.domain.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by filip on 26.3.15.
 */
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        em.persist(user);
    }

    @Override
    public User getUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        em.remove(user);
    }
}
