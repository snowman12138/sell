package com.imooc.sell.handler;

import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author CodeMonkey
 * @date 2020/3/23 18:55
 */
@ControllerAdvice
public class MyControllerAdvice {

    @Autowired
    private ProjectUrlConfig urlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
        return new ModelAndView("redirect:".
                concat(urlConfig.wechatOpenAuthorize).
                concat("/sell/wechat/qrAuthorize").
                concat("?returnUrl=").
                concat(urlConfig.wechatMpAuthorize).
                concat("/sell/seller/login"));
    }
}
