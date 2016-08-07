package com.everseeker.service;

import com.everseeker.entity.User;

/**
 * Created by everseeker on 16/8/7.
 */
public interface UserService {
    void addUser(User user);

    User getUserByUsername(String username);
}
