package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by 廖师兄
 * 2017-07-03 01:31
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 公众平台Id
     * */
    private String mpAppId;

    /**
     * 公众平台密码
     * */
    private String mpAppSecret;

    /**
     * 开放平台Id
     * */
    private String openAppId;

    /**
     * 开放平台密码
     * */
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;
    /**
     * 微信异步通知地址
     */
    private String notifyUrl;

    /**
     * 微信模板ID集合
     */
    private Map<String,String> TemplateId;

}
