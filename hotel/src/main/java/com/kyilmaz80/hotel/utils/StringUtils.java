package com.kyilmaz80.hotel.utils;

public class StringUtils {
    public static String toUpperFirstChar(String str) {
        char first = Character.toUpperCase(str.charAt(0));
        String f = first + "";
        return  f + str.substring(1);
    }

    public static boolean inputValid1(String str) {
        if (!str.isEmpty() && str.matches("[a-zA-Z\\-0-9_ ]+")) {
            return true;
        } else if (str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean inputValid2(String str) {
        if (str.matches("[0-9.]+")) {
            return true;
        }else if (str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean inputValid3(String str) {
        if (str.matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    public static boolean inputValid4(String str) {
        if (str.matches("[0-9]{11}")) {
            return true;
        }
        return false;
    }

    public static boolean inputValid5(String str) {
        if (str.matches("[0-9]{10}")) {
            return true;
        }
        return false;
    }

    public static boolean inputValid6(String str) {
        if (!str.matches("[A-Za-z0-9 ,.-]+")) {
            return false;
        }
        return true;
    }

    public static boolean inputValid7(String str) {
        if (!str.matches("[A-Za-z ]+")) {
            return false;
        }
        return true;
    }

    public static String filterStr(String str) {
        return str;
    }
}
