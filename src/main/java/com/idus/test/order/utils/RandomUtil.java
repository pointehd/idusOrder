package com.idus.test.order.utils;

import java.util.Random;

public class RandomUtil {
    private static final String MIX = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final Random random = new Random(System.currentTimeMillis());

    public static String randomCode(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(MIX.charAt(random.nextInt(36)));
        }
        return sb.toString();
    }
}
