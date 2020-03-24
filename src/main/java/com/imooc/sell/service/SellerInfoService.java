package com.imooc.sell.service;

import com.imooc.sell.dataobject.SellerInfo;

/**
 *
 * @author CodeMonkey
 * @date 2020/3/22 12:18
 */
public interface SellerInfoService {
    SellerInfo finOneByopenId(String openid);
}
