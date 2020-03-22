package com.imooc.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

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
    @NotEmpty(message = "商品名称必填")
    private String productName;

    /** 单价. */
    @NotEmpty(message = "商品价格必填")
    private BigDecimal productPrice;

    /** 库存. */
    @NotEmpty(message = "商品库存必填")
    private Integer productStock;

    /** 描述. */
    @NotEmpty(message = "商品描述必填")
    private String productDescription;

    /** 小图. */
    @NotEmpty(message = "商品图片必填")
    private String productIcon;

    /** 类目编号. */
    @NotEmpty(message = "商品类目必填")
    private Integer categoryType;
}
