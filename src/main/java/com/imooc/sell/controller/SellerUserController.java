package com.imooc.sell.controller;

import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.constant.CookieConstant;
import com.imooc.sell.constant.RedisConstrant;
import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.SellerInfoService;
import com.imooc.sell.utils.CookieUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Name;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author CodeMonkey
 * @date 2020/3/22 22:13
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openId")String openId,
                              HttpServletResponse response,
                              Map<String,Object> map){

        //1.数据库查询openid是否存在
        SellerInfo sellerInfo = sellerInfoService.finOneByopenId(openId);
        if (sellerInfo == null){
            map.put("errorMsg", ResultEnum.ACCOUNT_NO_PREMISSION.getMessage());

            map.put("url","/sell/seller/order/list");
            map.put("back","登陆界面");
            return new ModelAndView("common/error",map);
        }

        //2.如果存在就生成token保存在redis中
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstrant.TOKEN_PREFIX,token),openId,RedisConstrant.EXPIRE, TimeUnit.SECONDS);

        //3.设置token到cookie
        CookieUtil.set(response,CookieConstant.TOKEN,token,CookieConstant.EXPIRE);
        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/seller/order/list");

    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response,
                               HttpServletRequest request,
                               Map<String,Object> map){
        //1.取出cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        //2.删除session中的token
        String key =String.format(RedisConstrant.TOKEN_PREFIX,cookie.getValue());
        redisTemplate.opsForValue().getOperations().delete(key);
        //3.删除对应的cookie

        CookieUtil.set(response,CookieConstant.TOKEN,null,0);

        map.put("msg","注销操作添加成功！");
        map.put("back","登陆首页");
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
//        token_fb0105e7-8441-4db0-b5dc-783a67af7b08；
//              fb0105e7-8441-4db0-b5dc-783a67af7b08
    }
}
