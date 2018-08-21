<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>登录成功了</title>
  </head>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/main/css/reset.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main/css/style.css"/>
  <body>
    <div class="scroll-head"></div>
    <div class="top-wrapper">
        <div class="top-info">
            <!--top-right-->
            <div class="top-right">
                <div data-toggle="arrowdown" id="arrow3" class="user-name">
                    <a href="#">个人中心</a>
                    <span class="down-icon"></span>
                </div>
                <div data-toggle="arrowdown" id="arrow4" class="user-name">
                    <i class="fa fa-shopping-cart fa-orange"></i>
                    <a href="#">购物车</a>
                    <span class="down-icon"></span>
                </div>
                <div data-toggle="arrowdown" id="arrow5" class="user-name">
                    <i class="fa fa-star fa-gray"></i>
                    <a href="#">收藏夹</a>
                    <span class="down-icon"></span>
                </div>
                <a class="a-float-left" href="${pageContext.request.contextPath}/admin/shopTypeProductTypeAction">商品分类</a>
                <span class="vertical-line">|</span>
                <div data-toggle="arrowdown" id="arrow6" class="user-name">
                    <a href="#">卖家中心</a>
                    <span class="down-icon"></span>
                </div>
                <a class="a-float-left" href="#">联系客户</a>
                <div data-toggle="arrowdown" id="arrow7" class="user-name">
                    <i class="fa fa-list-ul fa-orange"></i>
                    <a href="#">网站导航</a>
                    <span class="down-icon"></span>
                </div>
                <!--hidden-box-->
                <div data-toggle="hidden-box" id="nav-box3" class="my-taobao-box">
                    <ul>
                        <li>未知</li>
                        <li>未知</li>                  
                    </ul>
                </div>
                <div data-toggle="hidden-box" id="nav-box4" class="shopping-box">
                    <span>您购物车里还没有任何宝贝。</span><a class="check-shopp" href="#">查看我的购物车</a>
                </div>
                <div data-toggle="hidden-box" id="nav-box5" class="get-box">
                    <ul>
                        <li>收藏的宝贝</li>
                        <li>收藏的店铺</li>
                    </ul>
                </div>
                <div data-toggle="hidden-box" id="nav-box6" class="center-box">
                    <ul>
                        <li>已卖出的宝贝</li>
                        <li>出售中的宝贝</li>
                        <li>卖家服务市场</li>
                        <li>卖家培训中心</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="top-main">
        <img src="${pageContext.request.contextPath}/static/main/img/logo.jpg" height="150px" width="590px">
        <img src="${pageContext.request.contextPath}/static/main/img/logo.jpg" height="150px" width="590px">
    </div>
    <div class="content-top">     
        <div class="sidebar">
            <h3>商品分类</h3>
            <div class="sidebar-info">
                <ul class="side-li">
                <c:forEach items="${productTypeBeans}" var="type">
                    <li class="s_1">
                    	<h3>
                    		<a href="${pageContext.request.contextPath}/admin/chaxunProductAction?typeId=${type.id }" value="${type.id }" name="chaxun">${type.name}</a>               
                    	</h3>
                    </li>
                </c:forEach>
                </ul>
              </div>
         </div>  
        <div class="right-con">
            <div class="nav">
                <a id="spe-a1" href="#">天猫</a>
                <a id="spe-a2" href="#">聚划算</a>
                <a id="spe-a3" href="#">二手</a>
                <span class="line-a">|</span>
                <a href="#">拍卖</a>
                <a href="#">一淘</a>
                <a href="#">电器城</a>
                <a href="#">Hitao粉妆</a>
                <a href="#">旅行</a>
                <a href="#">云手机</a>
                <a href="#">特色中国</a>
                <img style="cursor: pointer" src="${pageContext.request.contextPath}/static/main/img/ad.gif" />
                <span class="keep-a" href="#"><a href="#">消费者保障</a></span>
            </div>     
            <div class="show-box">
                <!--content-->
                <div class="content">
                    <ul class="imgBox">
                        <li><a href="#"><img src="${pageContext.request.contextPath}/static/main/img/1.jpg"></a></li>
                        <li><a href="#"><img src="${pageContext.request.contextPath}/static/main/img/2.jpg"></a></li>
                        <li><a href="#"><img src="${pageContext.request.contextPath}/static/main/img/3.jpg"></a></li>
                        <li><a href="#"><img src="${pageContext.request.contextPath}/static/main/img/4.jpg"></a></li>
                        <li><a href="#"><img src="${pageContext.request.contextPath}/static/main/img/5.jpg"></a></li>
                    </ul>
                    <div class="currentNum">
                        <span class="imgNum mark-color"></span>
                        <span class="imgNum"></span>
                        <span class="imgNum"></span>
                        <span class="imgNum"></span>
                        <span class="imgNum"></span>
                    </div>
                    <div class="control to-left"><i class="fa fa-angle-left"></i></div>
                    <div class="control to-right"><i class="fa fa-angle-right"></i></div>
                </div>
                <a style="float: left" href="#"><img src="${pageContext.request.contextPath}/static/main/img/6.6.jpg" /></a>
                <!--content-down-->
                <div class="content-down">
                    <ul class="imgBox1">
                        <li>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/01.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/02.jpg" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/03.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/04.png" /></a>
                        </li>
                        <li>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/01.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/02.jpg" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/03.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/04.png" /></a>
                        </li>
                        <li>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/01.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/02.jpg" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/03.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/04.png" /></a>
                        </li>
                        <li>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/01.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/02.jpg" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/03.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/04.png" /></a>
                        </li>
                        <li>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/01.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/02.jpg" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/03.png" /></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/static/main/img/04.png" /></a>
                        </li>
                    </ul>
                    <div class="currentNum-down">
                        <span class="imgNum1 mark-color"></span>
                        <span class="imgNum1"></span>
                        <span class="imgNum1"></span>
                        <span class="imgNum1"></span>
                        <span class="imgNum1"></span>
                    </div>
                    <div class="control1 to-left1">
                    	<i class="fa fa-angle-left"></i>
                    </div>
                    <div class="control1 to-right1">
                    	<i class="fa fa-angle-right"></i>
                    </div>
                </div>
                <a style="float: left" href="#"><img src="${pageContext.request.contextPath}/static/main/img/15.jpg" /></a>
            </div>
            <!--right-sidbar-->
            <div class="right-sidebar">
                <div class="info-box">
                    <ul class="tab-nav">
                        <li id="li-1" class="li-nav li-nav-hover li-border">公告</li>
                        <li id="li-2" class="li-nav">规则</li>
                        <li id="li-3" class="li-nav">论坛</li>
                        <li id="li-4" class="li-nav">安全</li>
                        <li style="border-right: none" id="li-5" class="li-nav">公益</li>
                    </ul>
                    <div id="box-1" style="display: block" class="hiddenBox">

                        <a href="#">淘宝2014:开放多样性</a>
                        <a href="#">舌尖中国二季 天猫首尝</a>
                        <a href="#">阿里通信自建计费系统</a>
                        <a href="#">来往首届419宇宙节</a>
                    </div>
                    <div id="box-2" class="hiddenBox">

                        <a href="#">[重要] 淘点点规范</a>
                        <a href="#">[重要] 虚假交易新规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                        <a href="#">[重要] 新增认证规则</a>
                        <a href="#">[重要] 购买刀具实名</a>
                    </div>
                    <div id="box-3" class="hiddenBox">
                        <a href="#">[优化] 称谓滥用将整改</a>
                        <a href="#">[热点] 如何赢取新商机</a>
                        <a href="#">[话题] 同城交易避处罚</a>
                        <a href="#">[聚焦] 新消保法全解析</a>
                    </div>
                    <div id="box-4" class="hiddenBox">

                        <a href="#">个人重要信息要管牢！</a>
                        <a href="#">卖家防范红包欺诈提醒</a>
                        <a href="#">更换收货地址的陷阱！</a>
                        <a href="#">注意骗子的技术升级了</a>
                    </div>
                    <div id="box-5" class="hiddenBox">
                        <a href="#">[优化] 称谓滥用将整改</a>
                        <a href="#">[热点] 如何赢取新商机</a>
                        <a href="#">[话题] 同城交易避处罚</a>
                        <a href="#">[聚焦] 新消保法全解析</a>
                    </div>
                </div>             
                <div class="user-info">
                    <div class="gold-top">
                        <img width="62px" height="62px" src="${pageContext.request.contextPath}/static/main/img/user-head.jpg" />
                        <div class="inner-user">
                            <h3>Hi 天之狼2011</h3>
                            <a class="get-gold" href="#"><span class="glods"></span><span class="get-money">领淘金币抵钱</span></a>
                            <a class="vip-home" href="#">会员俱乐部</a>
                        </div>
                    </div>
                    <!--login-->
                    <div class="login">
                        <a class="login-btn" href="#"><i class="fa fa-user fa-user-loc"></i>登陆</a>
                        <a class="login-btn free" href="#">免费注册</a>
                        <a class="login-btn free" href="#">免费开店</a>
                    </div>
                </div>
                <div class="service">
                    <h3>便民服务</h3>
                    <div id="service-1" class="service-cell service-z">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/phone.png" /></h5>
                        <h6>话费</h6>
                        <i class="fa fa-angle-down"></i>
                    </div>
                    <div id="service-2" class="service-cell service-z">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/game.png" /></h5>
                        <h6>游戏</h6>
                        <i class="fa fa-angle-down"></i>
                    </div>
                    <div id="service-3" class="service-cell service-z">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/plane.png" /></h5>
                        <h6>旅行</h6>
                        <i class="fa fa-angle-down"></i>
                    </div>
                    <div id="service-4" class="service-cell service-z">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/save.png" /></h5>
                        <h6>保险</h6>
                        <i class="fa fa-angle-down"></i>
                    </div>
                    <div class="service-cell">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/18.png" /></h5>
                        <h6>彩票</h6>
                    </div>
                    <div class="service-cell">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/move.png" /></h5>
                        <h6>电影</h6>
                    </div>
                    <div class="service-cell">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/eat.png" /></h5>
                        <h6>点外卖</h6>
                    </div>
                    <div class="service-cell">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/money.png" /></h5>
                        <h6>理财</h6>
                    </div>
                    <div class="service-cell">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/book.png" /></h5>
                        <h6>电子书</h6>
                    </div>
                    <div class="service-cell">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/miusc.png" /></h5>
                        <h6>音乐</h6>
                    </div>
                    <div class="service-cell">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/bag.png" /></h5>
                        <h6>水电煤</h6>
                    </div>
                    <div class="service-cell">
                        <h5 class="service-i"><img src="${pageContext.request.contextPath}/static/main/img/....png" /></h5>
                        <h6>请期待</h6>
                    </div>
                </div>
                <div id="service-box-1" class="service-box">
                    <div class="service-head">
                        <a href="#">话费充值</a>
                        <a href="#">流量充值</a>
                        <span class="fa fa-times"></span>
                    </div>
                    <div class="feihua-in">
                        <span>号码</span>
                        <input class="tell-num" type="text"  placeholder="手机号、固话号"/>
                    </div>
                    <div class="feihua-in">
                        <span>面值</span>
                        <input class="money-in" type="text" value="50" />
                        <span class="fa fa-angle-down sel-money"></span>
                    </div>
                    <div class="gary-text"><span>售价&nbsp;￥&nbsp;</span><span class="orange">49-49.8</span></div>
                    <div style="margin-top: 10px">
                        <a href="#" class="now-chongzhi">立即充值</a>
                        <a href="#" class="now-chongzhi dingqi">定期充值</a>
                        <a href="#">3G急速上网卡</a>
                    </div>
                </div>
                <div id="service-box-2" class="service-box">
                    <div class="service-head">
                        <a href="#">话费充值</a>
                        <a href="#">流量充值</a>
                        <span class="fa fa-times"></span>
                    </div>
                    <div class="feihua-in">
                        <span>号码</span>
                        <input class="tell-num" type="text"  placeholder="手机号、固话号"/>
                    </div>
                    <div class="feihua-in">
                        <span>面值</span>
                        <input class="money-in" type="text" value="50" />
                        <span class="fa fa-angle-down sel-money"></span>
                    </div>
                    <div class="gary-text"><span>售价&nbsp;￥&nbsp;</span><span class="orange">49-49.8</span></div>
                    <div style="margin-top: 10px">
                        <a href="#" class="now-chongzhi">立即充值</a>
                        <a href="#" class="now-chongzhi dingqi">定期充值</a>
                        <a href="#">3G急速上网卡</a>
                    </div>
                </div>
                <div id="service-box-3" class="service-box">
                    <div class="service-head">
                        <a href="#">话费充值</a>
                        <a href="#">流量充值</a>
                        <span class="fa fa-times"></span>
                    </div>
                    <div class="feihua-in">
                        <span>号码</span>
                        <input class="tell-num" type="text"  placeholder="手机号、固话号"/>
                    </div>
                    <div class="feihua-in">
                        <span>面值</span>
                        <input class="money-in" type="text" value="50" />
                        <span class="fa fa-angle-down sel-money"></span>
                    </div>
                    <div class="gary-text"><span>售价&nbsp;￥&nbsp;</span><span class="orange">49-49.8</span></div>
                    <div style="margin-top: 10px">
                        <a href="#" class="now-chongzhi">立即充值</a>
                        <a href="#" class="now-chongzhi dingqi">定期充值</a>
                        <a href="#">3G急速上网卡</a>
                    </div>
                </div>
                <div id="service-box-4" class="service-box">
                    <div class="service-head">
                        <a href="#">话费充值</a>
                        <a href="#">流量充值</a>
                        <span class="fa fa-times"></span>
                    </div>
                    <div class="feihua-in">
                        <span>号码</span>
                        <input class="tell-num" type="text"  placeholder="手机号、固话号"/>
                    </div>
                    <div class="feihua-in">
                        <span>面值</span>
                        <input class="money-in" type="text" value="50" />
                        <span class="fa fa-angle-down sel-money"></span>
                    </div>
                    <div class="gary-text"><span>售价&nbsp;￥&nbsp;</span><span class="orange">49-49.8</span></div>
                    <div style="margin-top: 10px">
                        <a href="#" class="now-chongzhi">立即充值</a>
                        <a href="#" class="now-chongzhi dingqi">定期充值</a>
                        <a href="#">3G急速上网卡</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/static/main/js/jquery_1.9.js"></script>
    <script src="${pageContext.request.contextPath}/static/main/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/static/main/js/img-show.js"></script>
  </body>
</html>