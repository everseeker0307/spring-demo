package com.everseeker.service;

import com.everseeker.config.RootConfig;
import com.everseeker.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addUserTest() {
        User user = new User("abc", "1234", "abc@123.com");
        userService.addUser(user);
    }

    @Test
    public void getUserByUsernameTest() {
        User user = userService.getUserByUsername("abc");
        System.out.println(user);
    }
}
