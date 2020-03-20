package com.imooc.sell.utils;

import com.imooc.sell.enums.CodeEnum;

/**
 * @author CodeMonkey
 * @date 2020/3/15 16:04
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getEnumByCode(Class<T> enumClass, Integer code){
        for (T enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.getCode().equals(code)){
                return enumConstant;
            }
        }
        return null;
    }
}
