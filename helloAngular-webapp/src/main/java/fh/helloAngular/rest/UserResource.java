package fh.helloAngular.rest;

/**
 * Created by filip on 28.3.15.
 */

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fh.helloAngular.dto.UserDto;
import fh.helloAngular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/user")
@Component
public class UserResource {

    final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(String userJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserDto userDto = mapper.readValue(userJSON, new TypeReference<UserDto>(){});
        userService.createUser(userDto);
        return mapper.writeValueAsString(userDto);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateUser(String user, @PathParam("id") Long id) {
        log.info(user, id);
        return null;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public UserDto getUser(@PathParam("id") Long id) {
        return userService.getUser(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        UserDto user = userService.getUser(id);
        if (user != null) {
            userService.deleteUser(user);
        }
        return null;
    }


}
