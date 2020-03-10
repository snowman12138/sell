package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.stream.events.StartElement;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author CodeMonkey
 * @date 2020/2/24 10:59
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImpLTest {
    @Autowired
    private OrderServiceImpL orderService;

    private final String BUYER_OPENID = "111111111";

    private final String ORDER_ID = "1582686953663312415";

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("青铜时代");
        orderDTO.setBuyerName("章北海");
        orderDTO.setBuyerPhone("16032879970");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        ArrayList<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("12138");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);


        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123456");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        log.info("【创建订单】result={}",orderDTO1);
        Assert.assertNotEquals(null,orderDTO1);
    }

    @Test
    public void findone() {

        OrderDTO result = orderService.findone(ORDER_ID);
        log.info("【查询单个订单】result+{}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {

        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, pageRequest);
        log.info("【从查询出来的订单分页信息】page+{}",orderDTOPage);
    }

    @Test
    public void cancel() {

        OrderDTO result = orderService.findone(ORDER_ID);
        OrderDTO cancel = orderService.cancel(result);
        Assert.assertEquals(cancel.getOrderStatus(), OrderStatusEnum.CANCEL.getCode());
    }

    @Test
    public void finish() {

        OrderDTO orderDTO = orderService.findone(ORDER_ID);
        OrderDTO finlish = orderService.finlish(orderDTO);
        assertEquals(finlish.getOrderStatus(),OrderStatusEnum.FINISHED.getCode());
    }

    @Test
    public void paid() {

        OrderDTO orderDTO = orderService.findone(ORDER_ID);
        OrderDTO paid = orderService.paid(orderDTO);
        assertEquals(paid.getPayStatus(), PayStatusEnum.SUCCESS.getCode());
    }
}