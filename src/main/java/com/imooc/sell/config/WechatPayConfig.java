package com.imooc.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author CodeMonkey
 * @date 2020/3/12 1:26
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public BestPayServiceImpl bestPayService(){
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayConfig());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayConfig(){

        WxPayH5Config wxPayConfig = new WxPayH5Config();
        wxPayConfig.setAppId(wechatAccountConfig.getMpAppId());
        wxPayConfig.setAppSecret(wechatAccountConfig.getMpAppSecret());
        wxPayConfig.setMchId(wechatAccountConfig.getMchId());
        wxPayConfig.setMchKey(wechatAccountConfig.getMchKey());
        wxPayConfig.setKeyPath(wechatAccountConfig.getKeyPath());
        wxPayConfig.setNotifyUrl(wechatAccountConfig.getNotifyUrl());
        return wxPayConfig;
    }


}
