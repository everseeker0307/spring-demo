package com.everseeker.action;

import com.everseeker.config.IOC;
import com.everseeker.entity.ConfirmEmail;
import com.everseeker.entity.Rest;
import com.everseeker.entity.ValidatorRest;
import com.everseeker.entity.User;
import com.everseeker.exception.UserException;
import com.everseeker.exception.UserStatus;
import com.everseeker.service.UserAlertService;
import com.everseeker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.HashMap;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ValidatorRest addUser(User user) {
        ValidatorRest result = new ValidatorRest();
        //1. 先校验用户输入是否符合要求
        Map<String, String> error = userService.checkUserValidator(user);
        result.setResult(error == null ? true : false);
        //2. 如果符合要求, 注册用户
        if (error == null) {
            try {
                userService.addUser(user);
                //3. 发送确认邮件
                ConfirmEmail confirmEmail = new ConfirmEmail(user);
                userAlertService.sendEmailToUserQueue(confirmEmail);
            } catch (Exception e) {
                error = new HashMap<String, String>();
                error.put("username", "用户名已被占用!");
                result.setResult(false);
                log.warn("Add user失败, 用户名{}已被占用!", user.getUsername());
            }
        }
        result.setError(error);
        result.setData(userService.getUserByUsername(user.getUsername()));
        return result;
    }

//    @POST
//    @Path("/login")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Rest<User> checkUser(User user) {
//        Rest<User> rest = new Rest<User>();
//        try {
//            User ruser = userService.checkUser(user.getUsername(), user.getPassword());
//            rest.setStatus(UserStatus.OK.getStatus());
//            rest.setMsg(UserStatus.OK.getMsg());
//            rest.setData(ruser);
//        } catch (UserException e) {
//            rest.setStatus(e.getStatus());
//            rest.setMsg(e.getMsg());
//        }
//
//        return rest;
//    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkUser(User user) {
        Rest<User> rest = new Rest<User>();
        try {
            User ruser = userService.checkUser(user.getUsername(), user.getPassword());
            rest.setStatus(UserStatus.OK.getStatus());
            rest.setMsg(UserStatus.OK.getMsg());
            rest.setData(ruser);
        } catch (UserException e) {
            rest.setStatus(e.getStatus());
            rest.setMsg(e.getMsg());
        }
        return Response.ok()
                       .cookie(new NewCookie("sid", "abcd1234xyz"))
                       .entity(rest)
                       .build();
    }
}
