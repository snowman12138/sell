package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.rmi.runtime.Log;

/**
 * @author CodeMonkey
 * @date 2020/3/11 23:12
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl")String returnUrl){
        //1.查询订单
        OrderDTO orderDTO = orderService.findone(orderId);
        if (orderDTO.getOrderId() == null){
            log.error("【微信支付错误】订单orderId={}不存在",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
    }


}
