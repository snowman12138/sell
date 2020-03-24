package com.imooc.sell.utils;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie工具类
 * @author CodeMonkey
 * @date 2020/3/23 0:08
 */
public class CookieUtil {

    /**
     * 添加cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set (HttpServletResponse response,
                            String name,
                            String value,
                            int maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /***
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request,
                             String name){

        Map<String,Cookie> cookieMap = cookieMap(request);
        if (cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }
        return null;
    }

    /***
     * 将cookie数组转化为Map集合
     * @param request
     * @return
     */
    public static Map<String,Cookie> cookieMap(HttpServletRequest request){

        HashMap<String, Cookie> cookieHashMap = new HashMap<>();
        for (Cookie cookie : request.getCookies()) {
            cookieHashMap.put(cookie.getName(),cookie);
        }
        return cookieHashMap;
    }

}
