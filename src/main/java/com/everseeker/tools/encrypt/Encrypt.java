package com.everseeker.tools.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by everseeker on 16/8/30.
 */
public class Encrypt {
    public static String encrypt(String username, String password) {
        System.out.println(username.hashCode() << 4);
        System.out.println(DigestUtils.md5Hex((username.hashCode() << 4) + username));
        System.out.println(Base64.encodeBase64String(password.getBytes()));
        return DigestUtils.shaHex(DigestUtils.md5Hex((username.hashCode() << 4) + username) + Base64.encodeBase64String(password.getBytes()));
    }
}
