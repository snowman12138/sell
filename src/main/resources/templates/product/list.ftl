<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../common/header.ftl">
</head>
<body>
<div id = "wrapper" class="toggled">
<#--侧边栏-->
    <#include "../common/nav.ftl">
<#--主要数据-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
            <#assign totalPages = productInfoPage.getTotalPages()>
            <#assign pagegroup =10>
            <#assign left = currentPage - 4>
            <#assign right = currentPage + 5>
            <#assign leftnumber = 5>
            <#assign rightnumber = productInfoPage.getTotalPages() -5>
            <#assign index = (currentPage-1) * size>
                    <table class="table table-bordered  table th ">
                        <thead>
                        <tr >
                            <th >序号</th>
                            <th >商品ID</th>
                            <th >名字</th>
                            <th >单价</th>
                            <th >库存</th>
                            <th >图片</th>
                            <th >描述</th>
                            <th >类目</th>
                            <th >状态</th>
                            <th >上架时间</th>
                            <th >修改时间</th>
                            <th colspan="2" >操作</th>
                        </tr>
                        </thead>
                    <tbody>
                   <#list productInfoPage.content as product>
                       <#assign index = index+1>
                   <tr >
                   <td> ${index}</td>
                   <td> ${product.productId}</td>
                   <td> ${product.productName}</td>
                   <td> ${product.productPrice}</td>
                   <td> ${product.productStock}</td>
                   <td>  <img height="60" width="60"  src="${product.productIcon}"></td>
                   <td> ${product.productDescription}</td>
                   <td> ${product.categoryType}</td>
                   <td> ${product.getProductStatusEnum().message}</td>
                   <td> ${product.createTime}</td>
                   <td> ${product.updateTime}</td>
                   <td align = "center"><a href="/sell/seller/product/modify?productId=${product.productId}">修改</a>  </td>
                   <td > <#if product.getProductStatusEnum().message ==("在架")>
                       <a href = "/sell/seller/product/off_sale?productId=${product.productId}">下架</a>
                   <#elseif product.getProductStatusEnum().message ==("下架")>
                       <a href= "/sell/seller/product/on_sale?productId=${product.productId}">上架 </a>
                   </#if>
                   </td>
                   </tr>
                   </#list>
                    </tbody>
                    </table>
                <#--分页-->
                    <ul class="pagination pull-right">
                    <#--第一页禁用上一页-->
                <#if currentPage == 1>
                    <li class="hidden"><a href="#">上一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                </#if>

                <#if (totalPages > 10)>
                    <#if (currentPage <= leftnumber)>
                        <#list 1..pagegroup as Page>
                            <#if currentPage == Page>
                                <li class="disabled"><a href="#"> ${Page}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${Page}&size=${size}"> ${Page}</a></li>
                            </#if>
                        </#list>
                    <#else>
                        <#if (right <= rightnumber )>
                            <#list left..right as Page>
                                <#if currentPage == Page>
                                <li class="disabled"><a href="#"> ${Page}</a></li>
                                <#else>
                                <li><a href="/sell/seller/order/list?page=${Page}&size=${size}"> ${Page}</a></li>
                                </#if>
                            </#list>
                        <#else >
                            <#list (totalPages -9)..totalPages as Page>
                                <#if currentPage == Page>
                                <li class="disabled"><a href="#"> ${Page}</a></li>
                                <#else>
                                <li><a href="/sell/seller/order/list?page=${Page}&size=${size}"> ${Page}</a></li>
                                </#if>
                            </#list>

                        </#if>

                    </#if>
                <#else>
                    <#list 1..productInfoPage.getTotalPages() as Page>
                        <#if currentPage == Page>
                                <li class="disabled"><a href="#"> ${Page}</a></li>
                        <#else>
                                 <li><a href="/sell/seller/order/list?page=${Page}&size=${size}"> ${Page}</a></li>
                        </#if>
                    </#list>
                </#if>
                    <#--最后一页禁用下一页-->
                <#if currentPage == productInfoPage.getTotalPages()>
                    <li class="hidden"> <a href="#">下一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                </#if>


                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>