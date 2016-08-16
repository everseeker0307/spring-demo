package com.everseeker.entity;

/**
 * Created by everseeker on 16/8/16.
 */
public class Rest<D extends Object> {
    private int status;
    private String msg;
    private D data;

    public Rest() {
    }

    public Rest(int status, String msg, D data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
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

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
