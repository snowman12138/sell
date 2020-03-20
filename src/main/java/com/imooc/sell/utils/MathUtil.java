package com.imooc.sell.utils;

/**
 * @author CodeMonkey
 * @date 2020/3/13 17:23
 */
public class MathUtil {

    private static final Double value = 0.001;

    /**
     * 比较两个金额是否相等
     * @param d1
     * @param d2
     * @return boolean
     */
    public static boolean equals(Double d1, Double d2){

        if (Math.abs(d1-d2) <= value){
            return true;
        }else {
            return false;
        }
    }
}
