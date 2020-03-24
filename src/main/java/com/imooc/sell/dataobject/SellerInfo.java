package com.imooc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author CodeMonkey
 * @date 2020/3/22 11:59
 */
@Entity
@Data
@DynamicUpdate
public class SellerInfo {

    @Id
    public String sellerId;

    /**卖家用户名*/
    public String username;

    /**登录密码*/
    public String password;

    /**openId*/
    public  String openid;
}
