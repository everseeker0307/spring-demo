package com.everseeker.service.impl;

import com.everseeker.dao.UserDao;
import com.everseeker.entity.User;
import com.everseeker.exception.UserException;
import com.everseeker.exception.UserStatus;
import com.everseeker.service.UserService;
import com.everseeker.tools.validator.ValidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
        log.info("Add user start: username={}", user.getUsername());
        userDao.addUser(user);
        log.info("Add user success: username={} success.", user.getUsername());
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User checkUser(String username, String password) throws UserException {
        log.info("Login start: username={}", username);
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            log.warn("Login fail: 用户{} {}", username, UserStatus.USERNAME_NOTFOUND.getMsg());
            throw new UserException(UserStatus.USERNAME_NOTFOUND.getStatus(), UserStatus.USERNAME_NOTFOUND.getMsg());
        }
        else if (user != null && !user.getPassword().equals(password)) {
            log.warn("Login fail: 用户{} {}", username, UserStatus.PASSWORD_WRONG.getMsg());
            throw new UserException(UserStatus.PASSWORD_WRONG.getStatus(), UserStatus.PASSWORD_WRONG.getMsg());
        } else {
            log.info("Login success: {}登录成功.", username);
            return user;
        }
    }

    public Map<String, String> checkUserValidator(User user) {
        return ValidateService.valid(user);
    }
}
