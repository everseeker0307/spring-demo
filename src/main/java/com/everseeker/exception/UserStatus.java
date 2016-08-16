package com.everseeker.exception;

/**
 * Created by everseeker on 16/8/16.
 */
public enum UserStatus {
    OK(999, "OK"),
    USERNAME_NOTFOUND(701, "用户名不存在"),
    PASSWORD_WRONG(702, "密码错误");

    private int status;
    private String msg;

    UserStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
