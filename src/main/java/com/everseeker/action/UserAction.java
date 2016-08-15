package com.everseeker.action;

import com.everseeker.config.IOC;
import com.everseeker.entity.User;
import com.everseeker.service.UserAlertService;
import com.everseeker.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by everseeker on 16/8/8.
 */
@Path("/")
public class UserAction {
    private static Logger log = LoggerFactory.getLogger(UserAction.class);
    private UserService userService = (UserService) IOC.getBean("userService");
    private UserAlertService userAlertService = (UserAlertService) IOC.getBean("userAlertService");

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByUsername(@PathParam("username") String username) {
        return userService.getUserByUsername(username);
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject addUser(String username, String password, String email) {
        JSONObject obj = new JSONObject();
        //先校验用户输入是否符合要求
        User user = new User(username, password, email);
        Map<String, String> error = userService.checkUserValidator(user);
        obj.put("result", error.isEmpty());
        obj.put("error", error);
        //如果符合要求, 注册用户
        if (error.isEmpty()) {
            try {
                userService.addUser(user);
            } catch (Exception e) {
                e.printStackTrace();
                obj.put("result", false);
            }
        }

        return obj;
    }
}
