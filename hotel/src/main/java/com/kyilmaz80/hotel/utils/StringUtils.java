package com.kyilmaz80.hotel.utils;

public class StringUtils {
    public static String toUpperFirstChar(String str) {
        char first = Character.toUpperCase(str.charAt(0));
        String f = first + "";
        return  f + str.substring(1);
    }

    public static boolean inputValid1(String str) {
        if (!str.isEmpty() && str.matches("[a-zA-Z_ ]+")) {
            return true;
        }
        return false;
    }
}
