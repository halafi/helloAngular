package fh.helloAngular;

import fh.helloAngular.dao.UserDao;
import fh.helloAngular.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by filip on 26.3.15.
 */
public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");

        User peter = new User();
        peter.setUserName("peter");
        peter.setEmail("peter@gmail.com");
        User nasta = new User();
        nasta.setUserName("nasta");
        nasta.setEmail("nasta@gmail.com");

        userDao.createUser(peter);
        userDao.createUser(nasta);

        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        User id1 = userDao.getUser(1L);
        System.out.println(id1);
        context.close();
    }
}
