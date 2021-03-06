package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.utils.EnumUtil;
import com.imooc.sell.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author CodeMonkey
 * @date 2020/2/23 13:12
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO  {

    /** 订单id. */
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

    /** 创建时间. */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    /** 更新时间. */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    /**订单详情列表*/
    List<OrderDetail> orderDetailList;

    /** 获取订单状态的枚举*/
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){

       return EnumUtil.getEnumByCode(OrderStatusEnum.class,orderStatus);
    }

    /**获取支付状态的枚举*/
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getEnumByCode(PayStatusEnum.class,payStatus);
    }
}
