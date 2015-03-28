package fh.helloAngular.rest;

/**
 * Created by filip on 28.3.15.
 */

import fh.helloAngular.dto.UserDto;
import fh.helloAngular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Component
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getInfo() {
        return "User resource.";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
