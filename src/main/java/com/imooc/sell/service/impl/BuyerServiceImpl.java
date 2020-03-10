package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CodeMonkey
 * @date 2020/2/28 9:50
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findoneOrder(String openid, String orderId) {

        OrderDTO orderDTO = CheckOutOrderOwner(openid, orderId);

        return orderDTO;
    }


    @Override
    public OrderDTO CancleOneorderDTO(String openid, String orderId) {

        OrderDTO orderDTO = CheckOutOrderOwner(openid, orderId);

        if (orderDTO == null){
            log.error("【订单取消】该订单不存在orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }



    //鉴权操作
    private OrderDTO CheckOutOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findone(orderId);

        if (orderDTO == null) {

            log.error("【查询订单】查询不到orderId={}的订单", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单】订单的openid不一致，openid={}，orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.PRODUCT_OWNER_ERROR);
        }
        return orderDTO;
    }
}
