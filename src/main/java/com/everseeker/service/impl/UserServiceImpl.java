package com.everseeker.service.impl;

import com.everseeker.dao.UserDao;
import com.everseeker.entity.User;
import com.everseeker.service.UserService;
import com.everseeker.tools.validator.ValidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * Created by everseeker on 16/8/7.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        log.info("Add user: username={}", user.getUsername());
        userDao.addUser(user);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User checkUser(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public Map<String, String> checkUserValidator(User user) {
        return ValidateService.valid(user);
    }
}
