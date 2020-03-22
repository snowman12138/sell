package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.form.CategoryForm;
import com.imooc.sell.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author CodeMonkey
 * @date 2020/3/21 11:40
 */
@RequestMapping("/seller/category")
@RestController
@Slf4j
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询商品的类目
     *
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView categoryList(Map<String, Object> map) {

        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示/新增
     *
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/modify")
    public ModelAndView modifyPage(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                                   Map<String, Object> map) {
        if (categoryId != null) {

            ProductCategory category = categoryService.findOne(categoryId);
            map.put("category", category);
            map.put("flag", "更新");
        } else {
            map.put("flag", "新增");
        }
        return new ModelAndView("category/modify", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {

        //判断是更新还是新增
        if (categoryForm.getCategoryId() == null) {
            map.put("url", "/sell/seller/category/modify");
            map.put("back", "商品类目添加页面");
        } else {
            map.put("url", "/sell/seller/category/modify" + categoryForm.getCategoryId());
            map.put("back", "商品类目修改页面");
        }
        //参数有错误返回错误页面
        if (bindingResult.hasErrors()) {
            map.put("errorMsg", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }
        //进行保存
        ProductCategory productCategory = new ProductCategory();

        try {
        if (categoryForm.getCategoryId() != null) {
            productCategory = categoryService.findOne(categoryForm.getCategoryId());
        }
            BeanUtils.copyProperties(categoryForm,productCategory);
        //捕获保存异常
            categoryService.save(productCategory);
        } catch (Exception e) {
            map.put("errorMsg", e.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("msg","商品类目添加成功！");
        map.put("back","商品类目列表页面");
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success");
    }
}
