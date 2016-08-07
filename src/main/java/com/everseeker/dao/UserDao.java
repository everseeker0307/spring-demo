package com.everseeker.dao;

import com.everseeker.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by everseeker on 16/8/7.
 */
@Repository
public interface UserDao {
    void addUser(User user);

    User getUserByUsername(String username);
}


