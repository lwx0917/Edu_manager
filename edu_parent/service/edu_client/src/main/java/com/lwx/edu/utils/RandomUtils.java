package com.lwx.edu.utils;

import java.util.Random;

public class RandomUtils {

    public static String verificationCode() {
        //生成六位随机正整数
        Random random = new Random();
        String verificationCode = String.valueOf(random.nextInt(9) + 1);
        for (int i = 0; i < 5; i++) {
            verificationCode += random.nextInt(10);
        }
        return verificationCode;
    }

    public static String randomName() {
        Random random = new Random();
        StringBuffer valSb = new StringBuffer();
        int length = 10;
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";
        int charLength = charStr.length();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charLength);
            valSb.append(charStr.charAt(index));
        }
        return "学"+valSb.toString();
    }
}
