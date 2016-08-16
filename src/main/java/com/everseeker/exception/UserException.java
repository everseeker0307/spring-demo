package com.everseeker.exception;

/**
 * Created by everseeker on 16/8/16.
 */
public class UserException extends Exception {
    private int status;
    private String msg;

    public UserException() {
        super();
    }

    public UserException(Exception e) {
        super(e);
    }

    public UserException(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public UserException(Exception e, int status, String msg) {
        super(e);
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}