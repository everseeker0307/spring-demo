package com.everseeker.action;

import com.everseeker.entity.User;
import com.everseeker.service.UserAlertService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by everseeker on 16/8/10.
 */
public class UserAlertHandler {
    private UserAlertService userAlertService;

    @Autowired
    public UserAlertHandler(UserAlertService userAlertService) {
        this.userAlertService = userAlertService;
    }

    public void sendEmailToUserHandler(User user) {
        userAlertService.sendEmailToUser(user.getEmail(), user);
//        System.out.println(user);
    }
}
