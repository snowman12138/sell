package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.ProductInfoForm;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author CodeMonkey
 * @date 2020/3/17 23:02
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * c查询商品列表
     * @param page 页码
     * @param size 每页显示条数
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView productList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                    @RequestParam(value = "size",defaultValue = "10")Integer size,
                                    Map<String,Object> map){

        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("currentPage",page);
        map.put("size",size);
        map.put("productInfoPage",productInfoPage);

        return new ModelAndView("product/list",map);
    }

    /**
     * 上架商品
     * @param productId 商品ID
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam(value = "productId") String productId,
                               Map<String,Object> map){

        try {
            productService.onSale(productId);
        } catch (Exception e) {
            log.error("【卖家上架商品】发上异常，异常信息={}",e.getMessage());
            map.put("errorMsg",e.getMessage());
            map.put("back","商品列表页面");
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg",ResultEnum.PRODUCT_ON_SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam(value = "productId") String productId,
                                Map<String,Object> map){

        try {
            productService.offSale(productId);
        } catch (Exception e) {
            log.error("【卖家下架商品】发上异常，异常信息={}",e.getMessage());
            map.put("errorMsg",e.getMessage());
            map.put("back","商品列表页面");
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg", ResultEnum.PRODUCT_DOWN_SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }

    /**
     * 修改商品信息
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/modify")
    public ModelAndView modify(@RequestParam(value = "productId",required = false) String productId,
                                Map<String,Object> map) {

        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);

        return new ModelAndView("product/modify",map);
    }

    /**
     * 保存/更新
     * @param productInfoForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductInfoForm productInfoForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        //判断是更新还是新增
        boolean flag = StringUtils.isEmpty(productInfoForm.getProductId());
        if (bindingResult.hasErrors()){
            map.put("errorMsg",bindingResult.getFieldError().getDefaultMessage());
            if (flag){
                map.put("back","商品添加页面");
                map.put("url","/sell/seller/product/modify");
            }else {
                map.put("back","商品信息修改页面");
                map.put("url","/sell/seller/product/modify?productId="+productInfoForm.getProductId());
            }
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();

        //捕捉保存操作可能发生的错误，并进行页面跳转
        try {
            if (!StringUtils.isEmpty(productInfoForm.getProductId())){
                productInfo = productService.findOne(productInfoForm.getProductId());
            }else {
                productInfoForm.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(productInfoForm,productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("errorMsg",e.getMessage());
            if (flag){
                map.put("back","新增商品页面");
                map.put("url","/sell/seller/product/modify");
            }else {
                map.put("back","商品信息修改页面");
                map.put("url","/sell/seller/product/modify?productId="+productInfoForm.getProductId());
            }
            return new ModelAndView("common/error",map);
        }
        //操作成功跳转到商品列表
        map.put("msg","商品添加成功！");
        map.put("back","商品列表");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

}


