package com.mzfk.utils;

import java.util.Random;

/**
 * @author sz
 * @Title: RandomString
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/2710:17
 */
public class RandomString {

    /**
     * 生成随机字符串
     * @param length 长度
     * @return String
     */
    public static String getRandomChar(int length) {            //生成随机字符串
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(36)]);
        }
        return buffer.toString();
    }
}
