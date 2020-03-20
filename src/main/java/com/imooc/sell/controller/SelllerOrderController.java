package com.imooc.sell.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.Map;

/**
 * @author CodeMonkey
 * @date 2020/3/14 15:56
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
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
    public ModelAndView findlist(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10")Integer size,
                                 Map<String,Object> map){

        PageRequest pageRequest = new PageRequest(page-1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }


    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "orderId")String orderId,
                               Map<String,Object> map){

        OrderDTO orderDTO = new OrderDTO();

        try {
         orderDTO = orderService.findone(orderId);

        } catch (Exception e) {
            log.error("【卖家端查询订单详情】发生异常，错误信息：{}",e);
            map.put("errorMsg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            map.put("back","订单列表页面");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") String orderId,
                               Map<String,Object> map){

        try {
            OrderDTO orderDTO = orderService.findone(orderId);
            orderService.cancel(orderDTO);
        } catch (Exception e) {
            log.error("【卖家端取消订单】发生异常，异常信息：={}",e.getMessage());
            map.put("errorMsg",e.getMessage());
            map.put("back","订单详情页面");
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("common/success",map);
    }


    @GetMapping("/finish")
    public ModelAndView finlish(@RequestParam(value = "orderId")String orderId,
                                Map<String, Object> map){


        try {
            OrderDTO orderDTO = orderService.findone(orderId);
            orderService.finlish(orderDTO);
        } catch (Exception e) {
            log.error("【卖家端完结订单】发生异常，错误信息：{}",e);
            map.put("errorMsg",e.getMessage());
            map.put("url","/sell/seller/order/detail?orderId="+orderId);
            map.put("back","订单详情页面");
            return new ModelAndView("common/error",map);
        }

        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("common/success");
    }

}
