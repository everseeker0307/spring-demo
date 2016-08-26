package com.everseeker.service;

import com.everseeker.entity.User;
import com.everseeker.exception.UserException;

import java.util.Map;

/**
 * Created by everseeker on 16/8/7.
 */
public interface UserService {
    //注册新用户
    void addUser(User user);

    //通过用户名返回用户
    User getUserByUsername(String username);

    //核对用户登录是否成功, 如果用户名和密码都匹配, 返回查询到的用户; 否则返回null
    User login(String username, String password) throws UserException;

    //核对用户输入
    Map<String, String> checkUserValidator(User user);
}
