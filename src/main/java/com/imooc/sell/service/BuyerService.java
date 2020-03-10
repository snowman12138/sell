package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

/**
 * @author CodeMonkey
 * @date 2020/2/26 14:46
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findoneOrder(String openid,String orderId);
    //取消一个订单
    OrderDTO CancleOneorderDTO(String openid,String orderId);
}
