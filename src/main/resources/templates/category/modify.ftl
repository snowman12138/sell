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
                    <form role="form" method="post" action="/sell/seller/category/save">

                        <#if flag != "更新">
                        <div class="hidden">
                            <input name="categoryId" type="hidden" class="form-control"  placeholder="" />
                        </div>
                        <#else >
                        <div class="form-group">
                         <label>类目ID</label>
                            <input name="categoryId" type="text" class="form-control"  placeholder="" value="${(category.categoryId)!""}"  readonly/>
                        </div>
                        </#if>
                        <div class="form-group">
                            <label >类目名称</label>
                            <input name="categoryName" type="text" class="form-control"  placeholder="请输入商品类目名称" value="${(category.categoryName)!""}"/>
                        </div>

                        <div class="form-group">
                            <label >类目编号</label>
                            <#if flag != "更新">
                            <input name="categoryType" type="number" class="form-control" placeholder="请输入商品类目编号" value="${(category.categoryType)!''}"/>
                           <#else >
                            <input name="categoryType" type="number" class="form-control"  value="${(category.categoryType)!''}" readonly/>

                            </#if>
                            </div>
                        <button type="submit" class="btn btn-default btn-info">Submit</button>
                    </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>