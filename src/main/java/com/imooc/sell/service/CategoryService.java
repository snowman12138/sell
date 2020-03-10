package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 * @author CodeMonkey
 * @date 2020/2/19 15:27
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categotyTypeList);

    ProductCategory save(ProductCategory productCategory);

}
