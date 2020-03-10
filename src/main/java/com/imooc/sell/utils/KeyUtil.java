package com.imooc.sell.utils;

import java.util.Random;

/**
 * 生成唯一的主键
 * @author CodeMonkey
 * @date 2020/2/23 16:17
 */
public class KeyUtil {

    public static synchronized String getUniqueKey(){
        Random random = new Random();

        String num = String.valueOf(random.nextInt(900000)+100000);
        return System.currentTimeMillis()+num;
    }
}
