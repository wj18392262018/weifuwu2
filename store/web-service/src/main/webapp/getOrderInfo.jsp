<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/12 0012
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>乐优商城--结算页</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/webbase.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pages-getOrderInfo.css" />
</head>

<body>
<!--head-->
<div id="orderInfoApp">
    <div class="top">
        <shortcut />
    </div>
    <form method="post" name="frm" action="${pageContext.request.contextPath}/category/submitOrder">
        <div  class="cart py-container">

            <!--logoArea-->
            <div class="logoArea">
                <div class="fl logo"><span class="title">结算页</span></div>
            </div>

            <!--主内容-->
            <div class="checkout py-container">
                <div class="checkout-tit">
                    <h4 class="tit-txt">填写并核对订单信息</h4>
                </div>
                <div class="checkout-steps">
                    <!--收件人信息-->
                    <div class="step-tit">
                        <h5>收件人信息<span><a data-toggle="modal" data-target=".edit" data-keyboard="false" class="newadd">新增收货地址</a></span></h5>
                    </div>
                    <div class="step-cont">
                        <div class="addressInfo">
                            <ul class="addr-detail">
                                <li class="addr-item" v-for="(a,i) in addresses" :key="i">
                                    <div>
                                        <div :class="{con:true,name:true,selected:i === selectedAddress}"><a href="javascript:;" @click.stop="selectedAddress=i" >{{a.name}}<span title="点击取消选择"></span>&nbsp;</a></div>
                                        <div class="con address">{{a.state + a.city + a.district + a.address}} <span>{{a.phone}}</span>
                                            <span class="base" v-if="a.default">默认地址</span>
                                            <span class="edittext"><a data-toggle="modal" data-target=".edit" data-keyboard="false" >编辑</a>&nbsp;&nbsp;<a href="javascript:;">删除</a></span>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>

                                </li>
                            </ul>
                            <!--添加地址-->
                            <div  tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade edit">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" data-dismiss="modal" aria-hidden="true" class="sui-close">×</button>
                                            <h4 id="myModalLabel" class="modal-title">添加收货地址</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form action="" class="sui-form form-horizontal">
                                                <div class="control-group">
                                                    <label class="control-label">收货人：</label>
                                                    <div class="controls">
                                                        <input type="text" class="input-medium">
                                                    </div>
                                                </div>

                                                <div class="control-group">
                                                    <label class="control-label">详细地址：</label>
                                                    <div class="controls">
                                                        <input type="text" class="input-large">
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">联系电话：</label>
                                                    <div class="controls">
                                                        <input type="text" class="input-medium">
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">邮箱：</label>
                                                    <div class="controls">
                                                        <input type="text" class="input-medium">
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">地址别名：</label>
                                                    <div class="controls">
                                                        <input type="text" class="input-medium">
                                                    </div>
                                                    <div class="othername">
                                                        建议填写常用地址：<a href="#" class="sui-btn btn-default">家里</a>　<a href="#" class="sui-btn btn-default">父母家</a>　<a href="#" class="sui-btn btn-default">公司</a>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" data-ok="modal" class="sui-btn btn-primary btn-large">确定</button>
                                            <button type="button" data-dismiss="modal" class="sui-btn btn-default btn-large">取消</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--确认地址-->
                        </div>
                        <div class="hr"></div>

                    </div>
                    <div class="hr"></div>
                    <!--支付和送货-->
                    <div class="payshipInfo">
                        <div class="step-tit">
                            <h5>支付方式</h5>
                        </div>
                        <div class="step-cont">
                            <ul class="payType" id="ul">
                                <li class="" value="0"><input type="radio" name="zifu" value="0" checked>微信付款</input><span title="点击取消选择"></span></li>
                                <li class="selected" value="1"><input type="radio" name="zifu" value="1">货到付款</input><span title="点击取消选择"></span></li>
                            </ul>
                        </div>
                        <div class="hr"></div>
                        <div class="step-tit">
                            <h5>送货清单</h5>
                        </div>
                        <input type="hidden" name="userId" value="${uid}">
                        <c:forEach items="${list}" var="sk">
                            <input type="hidden" name="skuId" value="${sk.id}">
                            <div class="step-cont">
                                <ul class="send-detail">
                                    <li >
                                        <div class="sendGoods">
                                            <ul class="yui3-g">
                                                <li class="yui3-u-1-6">
                                                    <span><img width="70px" height="70px" src="${pageContext.request.contextPath}/img/goods.png"/></span>
                                                </li>
                                                <li class="yui3-u-7-12">
                                                    <div class="desc">${sk.title}</div>
                                                    <div class="seven">7天无理由退货</div>
                                                </li>
                                                <li class="yui3-u-1-12">
                                                    <div class="price">${sk.price}</div>
                                                </li>
                                                <li class="yui3-u-1-12">
                                                    <div class="num">${sk.num}</div>
                                                </li>
                                                <li class="yui3-u-1-12">
                                                    <div class="exit">有货</div>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </c:forEach>
                        <div class="hr"></div>
                    </div>
                    <div class="linkInfo">
                        <div class="step-tit">
                            <h5>发票信息</h5>
                        </div>
                        <div class="step-cont">
                            <span>普通发票（电子）</span>
                            <span>个人</span>
                            <span>明细</span>
                        </div>
                    </div>
                    <div class="cardInfo">
                        <div class="step-tit">
                            <h5>使用优惠/抵用</h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="order-summary">
                <div class="static fr">
                    <div class="list">
                        <span><i class="number">${number}</i>件商品，总商品金额</span>
                        <em class="allprice"><span id="zong1">¥${zong}</span></em>
                    </div>
                    <div class="list">
                        <span>返现：</span>
                        <em class="money">0.00</em>
                    </div>
                    <div class="list">
                        <span>运费：</span>
                        <em class="transport">0.00</em>
                    </div>
                </div>
            </div>
            <div class="clearfix trade">
                <div class="fc-price">应付金额:　<span class="price" id="zong2">¥${zong}</span></div>
                <div class="fc-receiverInfo">
                    寄送至:{{addresses[selectedAddress].address}}
                    收货人：{{addresses[selectedAddress].name}} {{addresses[selectedAddress].phone}}
                </div>
            </div>
            <div class="submit">
                <a class="sui-btn btn-danger btn-xlarge" href="javascript:document.frm.submit()">提交订单</a>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/vue/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/axios.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
    /*var zifu;
    function f1(obj) {
        zifu=obj;
    }
    function f2(userId) {
        zifu=document.getElementById("hs").value;
        var skuId=document.getElementsByName("hd").value;
        alert(skuId);
        alert(zifu);
       //location.href="${pageContext.request.contextPath}/category/submitOrder?userId="+userId+"&&zifu="+zifu+"&&skuId="+skuId;
    }*/
    var orderInfoVm = new Vue({
        el:"#orderInfoApp",
        data:{
            addresses:[// 可选地址列表,假数据，需要从后台查询
                {
                    name:"虎哥",// 收件人姓名
                    phone:"15800000000",// 电话
                    state:"上海",// 省份
                    city:"上海",// 城市
                    district:"浦东新区",// 区
                    address:"航头镇航头路18号传智播客 3号楼",// 街道地址
                    zipCode:"210000", // 邮编
                    default: true
                },
                {
                    name:"张三",// 收件人姓名
                    phone:"13600000000",// 电话
                    state:"北京",// 省份
                    city:"北京",// 城市
                    district:"朝阳区",// 区
                    address:"天堂路 3号楼",// 街道地址
                    zipCode:"100000", // 邮编
                    default: false
                }
            ],
            selectedAddress: 0,

        },
        components:{
            shortcut: () => import("${pageContext.request.contextPath}/js/pages/shortcut.js")
    }
    });
</script>

<!-- 底部栏位 -->
<!--页面底部，由js动态加载-->
<div class="clearfix footer"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">$(".footer").load("foot.html");</script>
<!--页面底部END-->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jquery.easing/jquery.easing.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/sui/sui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pages/getOrderInfo.js"></script>
</body>

</html>
