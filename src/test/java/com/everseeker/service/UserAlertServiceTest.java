package com.everseeker.service;

import com.everseeker.config.RootConfig;
import com.everseeker.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by everseeker on 16/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class UserAlertServiceTest {
    @Autowired
    private UserAlertService userAlertService;

    @Test
    public void sendEmailToUserTest() {
        User user = new User("aqq", "88991234", "wuhao@ctyun.cn");
        userAlertService.sendEmailToUserQueue(user);
    }
}
