package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @author CodeMonkey
 * @date 2020/2/23 15:28
 */
@Getter
public enum  ResultEnum {

    PARAM_ERROR(1,"订单参数错误"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"库存不正确"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"当前订单不能取消！"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

    CAR_EMPTY(18,"购物信息为空"),

    PRODUCT_OWNER_ERROR(19,"订单不属于当前用户"),

    WECHAT_MP_ERROR(20,"微信公众号方面错误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
