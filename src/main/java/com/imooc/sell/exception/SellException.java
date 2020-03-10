package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

/**
 * @author CodeMonkey
 * @date 2020/2/23 14:30
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
    }
}
