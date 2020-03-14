package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author CodeMonkey
 * @date 2020/3/14 15:56
 */
@Controller
@RequestMapping("/seller/order")
public class SelllerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 查询所有的订单
     * @param page 当前页面,从第1页开始
     * @param size 每页信息的数量
     * @return
     */
    @GetMapping("/list")
    public ModelAndView findlist(@RequestParam("page") Integer page,
                                 @RequestParam("size")Integer size){

        PageRequest pageRequest = new PageRequest(page-1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);

        return new ModelAndView();
    }
}
