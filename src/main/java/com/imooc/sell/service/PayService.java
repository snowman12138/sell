package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

/**
 * @author CodeMonkey
 * @date 2020/3/12 0:33
 */
public interface PayService {

    void create(OrderDTO orderDTO);

}
