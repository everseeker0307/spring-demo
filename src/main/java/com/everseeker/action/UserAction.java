package com.everseeker.action;

import com.everseeker.config.IOC;
import com.everseeker.entity.User;
import com.everseeker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by everseeker on 16/8/8.
 */
@Path("/")
public class UserAction {
    private static Logger log = LoggerFactory.getLogger(UserAction.class);
    private UserService userService = (UserService) IOC.getBean("userService");

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByUsername(@PathParam("username") String username) {
        return userService.getUserByUsername(username);
    }
}
