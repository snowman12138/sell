package com.imooc.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @author CodeMonkey
 * @date 2020/2/23 17:18
 */
@Data
public class CarDTO {
    /**商品Id*/
    private String ProductId;

    /**数量*/
    private Integer productQuantity;

    public CarDTO(String productId, Integer productQuantity) {
        ProductId = productId;
        this.productQuantity = productQuantity;
    }
}
