<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    window.onload = function () {
        var websocket = null;
        if ('WebSocket' in window) {
            websocket = new WebSocket('ws:/noodle.nat300.top/sell/webSocket');
        } else {
            alert('该浏览器不支持websocket!');
        }
        websocket.onopen = function (event) {
            console.log('建立连接');
        }
        websocket.onclose = function (event) {
            console.log('关闭连接');

        }
        websocket.onmessage = function (event) {
            console.log('收到消息:' + event.data)
            //弹窗提醒, 播放音乐
            $('#myModal').modal('show');
            // document.getElementById('notice').muted=true;

            document.getElementById('notice').play();
        }

        websocket.onerror = function () {
            alert('websocket通信发生异常');
        }

        window.onbeforeunload = function () {
            websocket.close();
        }
        // myVid=document.getElementById("notice");
        // function enableMute()
        // {
        //     myVid.muted=true;
        // }
        // function disableMute()
        // {
        //     myVid.muted=false;
        // }
    }
</script>
<body>
<div id="wrapper" class="toggled">
<#--侧边栏-->
    <#include "../common/nav.ftl">
<#--主要数据-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
            <#assign totalPages = orderDTOPage.getTotalPages()>
            <#assign left = currentPage - 4>
            <#assign right = currentPage + 5>
            <#assign leftnumber = 5>
            <#assign rightnumber = orderDTOPage.getTotalPages() -5>
            <#assign pagegroup =10>
            <#assign index = (currentPage-1) * size>
                    <table class="table table-bordered  text-center">
                        <thead>
                        <tr class="text-center">
                            <th class="text-center">序号</th>
                            <th class="text-center">订单id</th>
                            <th class="text-center">姓名</th>
                            <th class="text-center">手机号</th>
                            <th class="text-center">地址</th>
                            <th class="text-center">金额</th>
                            <th class="text-center">订单状态</th>
                            <th class="text-center">支付状态</th>
                            <th class="text-center">创建时间</th>
                            <th colspan="2" class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list orderDTOPage.content as orderDTo>
                            <tr>
                                <#assign index = index+1>
                                <td> ${index}</td>
                                <td> ${orderDTo.orderId}</td>
                                <td> ${orderDTo.buyerName}</td>
                                <td> ${orderDTo.buyerPhone}</td>
                                <td> ${orderDTo.buyerAddress}</td>
                                <td> ${orderDTo.orderAmount}</td>
                                <td> ${orderDTo.getOrderStatusEnum().msg}</td>
                                <td> ${orderDTo.getPayStatusEnum().message}</td>
                                <td> ${orderDTo.createTime}</td>
                                <td align="center"><a href="/sell/seller/order/detail?orderId=${orderDTo.getOrderId()}">详情</a>
                                </td>
                                 <#if orderDTo.getOrderStatusEnum().msg ==("新订单")>
                                     <td align="center"><a
                                             href="/sell/seller/order/cancel?orderId=${orderDTo.getOrderId()}">取消</a>
                                     </td>
                                 <#else>
                                     <td align="center" class="disabled"></td>
                                 </#if>
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
                    <#list 1..orderDTOPage.getTotalPages() as Page>
                        <#if currentPage == Page>
                                <li class="disabled"><a href="#"> ${Page}</a></li>
                        <#else>
                                 <li><a href="/sell/seller/order/list?page=${Page}&size=${size}"> ${Page}</a></li>
                        </#if>
                    </#list>
                </#if>
                    <#--最后一页禁用下一页-->
                <#if currentPage == orderDTOPage.getTotalPages()>
                    <li class="hidden"><a href="#">下一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button onclick="javascript:document.getElementById('notice').pause()"  type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒:
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>
<#--播放音乐-->
<audio id="notice" loop="loop" muted=true >
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>

</body>
</html>