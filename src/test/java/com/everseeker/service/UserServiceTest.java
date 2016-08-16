package com.everseeker.service;

import com.everseeker.config.RootConfig;
import com.everseeker.entity.User;
import com.everseeker.exception.UserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by everseeker on 16/8/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Rollback   //注: @Roolback是针对事务的, 对非事务性方法不起作用
    public void addUserTest() {
        User user = new User("www", "987", "www@189.cn");
        userService.addUser(user);
    }

    @Test
    public void getUserByUsernameTest() {
        User user = userService.getUserByUsername("abc");
        System.out.println(user);
    }

    @Test
    public void checkUserTest() throws UserException {
        System.out.println("\ncheckUserTest: " + userService.checkUser("www", "9876") + "\n");
    }
}
