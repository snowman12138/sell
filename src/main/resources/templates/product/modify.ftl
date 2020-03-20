<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../common/header.ftl">
    <script type="text/javascript">
        function function19(){
            var address=document.getElementById("imgLink").value;
            document.getElementById('picture').src = address;
        }

        window.onload=function () {
            var stock = document.querySelector('#stock');

            stock.addEventListener('focus', function () {
                this.type = 'number';
            }, false);
            stock.addEventListener('blur', function () {
                this.type = 'text';
            }, false);
        }

    </script>
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
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label >名称</label>
                            <input name="productName" type="text" class="form-control"  placeholder="请输入商品名称" value="${(productInfo.productName)!""}"/>
                        </div>
                        <div class="form-group">
                            <label >单价</label>
                            <input name="productPrice" type="text" class="form-control" placeholder="请输入商品价格" value="${(productInfo.productPrice)!""}" />
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="text" class="form-control"  placeholder="请输入库存数量" value="${(productInfo.productStock)!""}" id="stock"/>
                        </div>
                        <div class="form-group">
                            <label >描述</label>
                            <input name="productDescription" type="text" class="form-control" placeholder="请输入商品描述" value="${(productInfo.productDescription)!""}"  />
                        </div>
                        <div class="form-group">
                            <label >图片</label>
                            <img src="${(productInfo.productIcon)!""}" alt="商品展示图片" height="100" width="100" id="picture" >
                            <input name="productIcon" type="text" class="form-control" placeholder="请输入图片图床链接" value="${(productInfo.productIcon)!""}" id="imgLink" style="margin-top: 15px" onblur="function19()"/>
                        </div>

                        <div class="form-group">
                            <label >类目</label>
                            <select name="categoryType" class="form-control" >
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                        <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                            selected
                                        </#if>
                                    >${category.categoryName}</option>

                                </#list>
                            </select>
                        </div>
                        <input name= "productId" type="text" class="hidden" value="${(productInfo.productId)!""}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>