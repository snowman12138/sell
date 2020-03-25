package com.imooc.sell.handler;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.exception.RespondBankException;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.exception.SellerAuthorizeException;
import com.imooc.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO SellExceptionHandler(SellException e){

        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = RespondBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException(){

    }
}
