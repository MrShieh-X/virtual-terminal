package com.mrshiehx.virtual_terminal.utils;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    public static boolean isNumber(String value) {
        for (char a : value.toCharArray()) {
            if (a != '0' && a != '1' && a != '2' && a != '3' && a != '4' && a != '5' && a != '6' && a != '7' && a != '8' && a != '9') {
                return false;
            }
        }
        return true;
    }
}
