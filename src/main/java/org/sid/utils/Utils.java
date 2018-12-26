package org.sid.utils;

public class Utils {

    private Utils() {
    }

    public static String capitalizeFirstLetter(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s.substring(0, 1).toUpperCase());
        stringBuilder.append(s.substring(1));
        return stringBuilder.toString();
    }
}
