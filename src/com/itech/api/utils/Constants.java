package com.itech.api.utils;

import java.nio.charset.StandardCharsets;

public class Constants {
    public static String decodeUTF8(String str) {
        try {
            return java.net.URLDecoder.decode(str, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return str;
        }
    }
    public static Boolean isSuccess(int code){
        return !between(400,599,code);
    }
    public static boolean between(int start, int end, int num) {
        return (num >= start) && (num <= end);
    }
}
