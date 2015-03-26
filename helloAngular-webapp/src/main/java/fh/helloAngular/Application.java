package fh.helloAngular;

import fh.helloAngular.dao.UserDao;
import fh.helloAngular.domain.User;
import fh.helloAngular.dto.UserDto;
import org.dozer.Mapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by filip on 26.3.15.
 */
public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        //Mapper mapper = (Mapper) context.getBean("dozer");

        /*User peter = userDao.getUser(1L);
        UserDto peterDto = mapper.map(peter, UserDto.class);
        System.out.println(peterDto);*/
        /*User nasta = new User();
        nasta.setUserName("nasta");
        nasta.setEmail("nasta@gmail.com");

        userDao.createUser(peter);
        userDao.createUser(nasta);*/

        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        context.close();
    }
}
