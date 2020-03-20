package com.imooc.sell.form;

import lombok.Data;

import javax.persistence.Id;
import javax.ws.rs.DefaultValue;
import java.math.BigDecimal;

/**
 * @author CodeMonkey
 * @date 2020/3/20 10:26
 */
@Data
public class ProductInfoForm {

    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 类目编号. */
    private Integer categoryType;
}
