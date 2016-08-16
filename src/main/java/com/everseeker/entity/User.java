package com.everseeker.entity;

import com.everseeker.tools.validator.DataValidator;
import com.everseeker.tools.validator.RegexType;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by everseeker on 16/8/7.
 */
public class User implements Serializable {
    private String id;

    @DataValidator(max = 16, notEmpty = true)
    private String username;

    @DataValidator(min = 6)
    private String password;

    @DataValidator(type = RegexType.EMAIL)
    private String email;

    private long regDate;

    private int state;

    public User() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.regDate = new Date().getTime();
        this.state = 0;
    }

    public User(String username, String password, String email) {
        this(username, password, email, new Date().getTime(), 0);
    }

    public User(String username, String password, String email, long regDate, int state) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.username = username;
        this.password = password;
        this.email = email;
        this.regDate = regDate;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getRegDate() {
        return regDate;
    }

    public void setRegDate(long regDate) {
        this.regDate = regDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "[User: id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", regDate=" + regDate + ", state=" + state + "]";
    }
}
