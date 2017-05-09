<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Cedrus
  Date: 2017/5/3
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>主页</title>
    <link rel="stylesheet" href="/static/css/paper-bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/layer.css">
    <link rel="stylesheet" href="/static/css/main.css">

</head>

<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container">
    <div class="recom-friend">
        <h3>好友推荐</h3>
        <div class="row">
            <c:forEach items="${recommend}" var="item">
            <div class="col-md-4">
                <div class="recom-friend-item">
                    <div class="recom-friend-item-body clearfix">
                        <div class="recom-friend-item-left pull-left">
                            <img class="img-responsive img-rounded" src="/${item.photo}" alt="...">
                        </div>
                        <div class="recom-friend-item-right pull-left">
                            <a href="#"> <span class="recom-friend-item-nickname">${item.userName}</span></a>
                            <div class="recom-friend-item-detail">
                                <span class="recom-friend-item-detail-sex">
                                    <c:if test="${item.sex==0}">
                                        <img src="/static/img/secret.png" alt="">
                                    </c:if>
                                    <c:if test="${item.sex==1}">
                                        <img src="/static/img/male.png" alt="">
                                    </c:if>
                                    <c:if test="${item.sex==2}">
                                        <img src="/static/img/female.png" alt="">
                                    </c:if>
                                </span>
                                <span class="recom-friend-item-detail-age">${item.age}岁</span>
                            </div>
                            <p class="recom-friend-item-signature">${item.personalizedSignature}</p>
                           <%-- <p class="recom-friend-item-signature">心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。</p>--%>
                        </div>
                    </div>
                    <div class="recom-friend-item-footer">
                        <div class="row">
                            <!--start平板以上屏幕大小显示-->
                            <div class="hidden-xs col-md-3 col-md-offset-2">
                                <a href="/user/profile?userID=${item.userID}" class="btn btn-default">查看主页</a>
                            </div>
                            <!--如果已经是好友就不要显示下面这个div-->
                            <div class="hidden-xs col-md-3 col-md-offset-2">
                                <input type="hidden" name="uid" value="${item.userID}">
                                <button class="addFriend btn btn-primary">添加好友</button>
                            </div>
                            <!--end平板以上屏幕大小显示-->

                            <!--start手机显示-->
                            <div class="visible-xs">
                                <a href="/user/profile?userID=${item.userID}" class="btn-block btn btn-default">查看主页</a>
                            </div>
                            <!--如果已经是好友就不要显示下面这个div-->
                            <div class="visible-xs">
                                <input type="hidden" name="uid" value="${item.userID}">
                                <button class="addFriend btn-block btn btn-primary">添加好友</button>
                            </div>
                            <!--end手机显示-->
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
</div>
<script src="/static/js/jquery-2.1.4.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/layer.js"></script>
<script src="/static/js/main.js"></script>
</body>

</html>