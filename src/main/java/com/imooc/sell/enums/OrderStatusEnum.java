package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @author CodeMonkey
 * @date 2020/2/22 12:07
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
