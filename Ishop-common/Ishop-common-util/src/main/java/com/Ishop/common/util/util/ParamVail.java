package com.Ishop.common.util.util;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamVail {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean isNull(Object o) {
        return ObjectUtils.isEmpty(o);
    }

    public static boolean vailNumber(int num) {
        if (ObjectUtils.isEmpty(num)) {
            return false;
        }
        String str = String.valueOf(num);
        if (StringUtils.hasText(str)) {
            String regex = "^[0-9\u4E00-\u9FA5]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(str);
            return match.matches();
        } else {
            return false;
        }
    }
    public static boolean vailNumber(int ... num) {
        for (int i : num) {
            String str = String.valueOf(i);
            if (StringUtils.hasText(str)) {
                String regex = "^[0-9\u4E00-\u9FA5]+$";
                Pattern pattern = Pattern.compile(regex);
                Matcher match = pattern.matcher(str);
                if (!match.matches()) {
                    return false;
                }
            }
        }
            return true;
    }


    public static boolean vailNumber(String str) {
        if (StringUtils.hasText(str)) {
            String regex = "^[0-9\u4E00-\u9FA5]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(str);
            return match.matches();
        } else {
            return false;
        }
    }


    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean vailString(String ... args) {
        for (String str:args) {
            if (StringUtils.hasText(str)) {
                String regex = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
                Pattern pattern = Pattern.compile(regex);
                Matcher match = pattern.matcher(str);
                if (!match.matches()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }


}
