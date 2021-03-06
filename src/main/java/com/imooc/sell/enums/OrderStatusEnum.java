package com.imooc.sell.enums;

import com.imooc.sell.dto.OrderDTO;
import lombok.Getter;
import org.aopalliance.reflect.Code;

/**
 * @author CodeMonkey
 * @date 2020/2/22 12:07
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
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
