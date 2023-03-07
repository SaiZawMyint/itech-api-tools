package com.itech.api.utils;

import java.nio.charset.StandardCharsets;

/**
 * A constant for Itech API
 * @author Itech
 */
public class Constants {
    /**
     * Decode String to UTF-8 representation.
     * @param str the String
     * @return decoded String
     */
    public static String decodeUTF8(String str) {
        try {
            return java.net.URLDecoder.decode(str, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return str;
        }
    }

    /**
     * Encode String to UTF-8 representation.
     * @param str the String
     * @return decoded String
     */
    public static String encodeUTF8(String str){
        try {
            return java.net.URLEncoder.encode(str, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return str;
        }
    }
}
