package com.everseeker.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by everseeker on 16/8/22.
 */
public class ConfirmEmail implements Serializable {
    private User user;
    private String confirmAddress;

    public ConfirmEmail() {}

    public ConfirmEmail(User user) {
        this.user = user;
        this.confirmAddress = UUID.randomUUID().toString().replace("-", "");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConfirmAddress() {
        return confirmAddress;
    }

    public void setConfirmAddress(String confirmAddress) {
        this.confirmAddress = confirmAddress;
    }

    @Override
    public String toString() {
        return "[ConfirmEmail: " + user.toString() + ", confirmAddress=" + confirmAddress + "]";
    }
}
