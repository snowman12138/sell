package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.repository.SellerInfoRepository;
import com.imooc.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CodeMonkey
 * @date 2020/3/22 12:20
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    SellerInfoRepository repository;

    @Override
    public SellerInfo finOneByopenId(String openid) {
      return  repository.findByOpenid(openid);
    }
}
