<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--侧边栏-->
    <#include "../common/nav.ftl">
<#--主要数据-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered  text-center">
                        <thead>
                        <tr class="text-center">
                            <th class="text-center">类目ID</th>
                            <th class="text-center">类目名字</th>
                            <th class="text-center">类目编号</th>
                            <th class="text-center">创建时间</th>
                            <th class="text-center">最近更新</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list categoryList as category>
                            <tr>
                                <td> ${category.categoryId}</td>
                                <td> ${category.categoryName}</td>
                                <td> ${category.categoryType}</td>
                                <td> ${category.createTime}</td>
                                <td> ${category.updateTime}</td>
                                <td align="center"><a
                                        href="/sell/seller/category/modify?categoryId=${category.categoryId}">修改</a></td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>