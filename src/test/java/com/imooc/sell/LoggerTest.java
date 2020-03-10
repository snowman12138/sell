package com.imooc.sell;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Data
@Slf4j
public class LoggerTest {
//    private  final Logger log = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        log.debug("debug;.........");
        log.info("info............");
        log.error("eroor................");
    }
    @Test
    public void test02(){
        System.out.println("你好陌生人");
    }
}
