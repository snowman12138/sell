package com.imooc.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author CodeMonkey
 * @date 2020/2/26 15:22
 */
@Data
public class OrderForm  {
    /**
     * 买家姓名
     * */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机
     * */
    @NotEmpty(message = "手机必填")
    private String phone;

    /**
     * 买家地址
     * */
    @NotEmpty(message = "地址必填")
    private String address   ;

    /**
     * 买家微信openId
     * */
    @NotEmpty(message = "Id必填")
    private String openid;

    /**
     * 购物详情
     * */
    @NotEmpty(message = "购物车必填")
    private String items;
}
