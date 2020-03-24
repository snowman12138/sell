package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.service.SellerInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author CodeMonkey
 * @date 2020/3/22 13:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {
    @Autowired
    private SellerInfoService sellerInfoService;


    @Test
    public void finOne() {
        SellerInfo admin = sellerInfoService.finOneByopenId("adc1212");
        assertEquals(admin.getOpenid(),"adc1212");

    }
}