package com.imooc.sell.repository;

import com.imooc.sell.constant.RedisConstrant;
import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.*;

/**
 * @author CodeMonkey
 * @date 2020/3/22 12:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void findByOpenid() {
        SellerInfo adc1212 = repository.findByOpenid("adc1212");
        assertEquals(adc1212.getOpenid(),"adc1212");
    }

    @Test
    public void findByUsername() {
        SellerInfo admin = repository.findByUsername("admin");
        assertEquals("admin",admin.getUsername());
    }

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("adc1212");
        repository.save(sellerInfo);
    }
    @Test
    public void delete(){
        Cookie cookie = new Cookie("1","  fb0105e7-8441-4db0-b5dc-783a67af7b08");
        String key = String.format(RedisConstrant.TOKEN_PREFIX,cookie.getValue());
        System.out.println(key);

        System.out.println(key == "token_fb0105e7-8441-4db0-b5dc-783a67af7b08");
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}