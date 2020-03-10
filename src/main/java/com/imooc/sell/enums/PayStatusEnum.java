package com.imooc.sell.enums;

import lombok.Getter;

/**
 * 订单支付状态
 * @author CodeMonkey
 * @date 2020/2/22 12:15
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
