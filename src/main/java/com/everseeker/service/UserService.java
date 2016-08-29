package com.everseeker.service;

import com.everseeker.entity.User;
import com.everseeker.exception.UserException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

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

    //根据sessionid查找用户, 如果在缓存中找到, 直接返回user; 否则返回null
    @Cacheable("sidCache")
    User getUserCacheBySessionId(String sid);

    //根据username查找sessionId, 如果在缓存中找到, 直接返回sid; 否则返回null
    @Cacheable("usernameCache")
    String getSessionIdCacheByUsername(String username);

    //删除key为sid的缓存
    @CacheEvict("sidCache")
    void removeUserCacheBySessionId(String sid);

    //设置缓存, hash格式
//    void setCache(String key, String subkey, Object value);

    //设置缓存, String格式
    void setCache(String key, Object value);
}
