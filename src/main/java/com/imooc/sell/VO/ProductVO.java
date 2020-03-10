package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imooc.sell.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目）
 * @author CodeMonkey
 * @date 2020/2/20 14:41
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;



}
