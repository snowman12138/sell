package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author CodeMonkey
 * @date 2020/2/19 17:52
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12345675785");
        productInfo.setProductName("香辣鸡翅堡");
        productInfo.setProductPrice(new BigDecimal(7));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("又香又辣，超好吃");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByProductStatus() {

        List<ProductInfo> ProductStatus = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,ProductStatus.size());
    }
}