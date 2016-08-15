package com.everseeker.tools.validator;

/**
 * Created by everseeker on 16/8/15.
 */
public class RegexUtils {
    //判断是否为ip, 是返回true, 否则false
    public static boolean isIp(String ip) {
        if (null == ip || "".equals(ip))
            return false;
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ip.matches(regex);
    }

    //判断是否为邮箱地址, 是返回ture, 否则false
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email))
            return false;
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }

    //判断是否为数字, 是返回ture, 否则false
    public static boolean isDigits(String number) {
        if (null == number || "".equals(number))
            return false;
        String regex = "[0-9]*";
        return number.matches(regex);
    }

    //判断是否为手机号码
    public static boolean isPhone(String phoneNumber) {
        if (null == phoneNumber || "".equals(phoneNumber))
            return false;
        String regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
        return phoneNumber.matches(regex);
    }

}
