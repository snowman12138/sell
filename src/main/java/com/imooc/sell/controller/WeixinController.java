package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author CodeMonkey
 * @date 2020/3/2 12:04
 */
@RestController
@Slf4j
@RequestMapping("/weixin")
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法.......7878......");
        log.info("code={}",code);

        String  url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx43dd40cbc7b11559&secret=f60abefc4e4ca5180508acd35a72d48a&code="+code+"&grant_type=authorization_code";
//        String url  ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd898fcb01713c658&secret=29d8a650db31472aa87800e3b0d739f2&code="+code +"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}",response);

//        String url2 = " https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    }
}
