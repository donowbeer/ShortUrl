package com.donowbeer.shorturl.logic;

import java.util.Random;

public class MyStringUtil {

    public static final String URL_REG = "((http|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    public static final String KEYWORD_REG = "[a-zA-Z0-9]{4,7}";


    private static String pool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRandomKeyword(int keywordLen) {
        StringBuffer sb = new StringBuffer();
        int len = pool.length();
        for (int i = 0; i < keywordLen; i++) {
            sb.append(pool.charAt(MyRandomUtil.generateRandomInt(len)));

        }
        return sb.toString();
    }

}
