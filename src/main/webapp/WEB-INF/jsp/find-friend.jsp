<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>找啊找啊找朋友</title>
    <link rel="stylesheet" href="/static/css/paper-bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/layer.css">
    <link rel="stylesheet" href="/static/css/main.css">
    <script src="/static/js/jquery-2.1.4.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/layer.js"></script>
    <script src="/static/js/main.js"></script>

</head>

<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <form action="/user/queryFriend"  method="POST">
            <fieldset>
                <legend>筛选</legend>
                <div class="form-group col-md-4">
                    <label for="sex" class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-6">
                        <select class="form-control" name="sex" id="sex">
                            <c:if test="${user.sex==0}" >
                                <option value="0" selected>保密</option>
                                <option value="1">男</option>
                                <option value="2">女</option>

                            </c:if>
                            <c:if test="${user.sex==1}" >
                                <option value="0" >保密</option>
                                <option value="1" selected>男</option>
                                <option value="2">女</option>

                            </c:if>
                            <c:if test="${user.sex==2}" >
                                <option value="0" >保密</option>
                                <option value="1" >男</option>
                                <option value="2" selected>女</option>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="age" class="col-sm-2 control-label">年龄</label>
                    <div class="col-sm-6">
                        <select class="form-control" name="age" id="age">
                            <c:if test="${user.age <16}">
                                <option value="14" selected>16岁以下</option>
                                <option value="18">16-20岁</option>
                                <option value="22">20-24岁</option>
                                <option value="26">24-28岁</option>
                                <option value="30">28岁以上</option>
                            </c:if>
                            <c:if test="${user.age >=16 && user.age<20}">
                                <option value="14" >16岁以下</option>
                                <option value="18" selected>16-20岁</option>
                                <option value="22">20-24岁</option>
                                <option value="26">24-28岁</option>
                                <option value="30">28岁以上</option>
                            </c:if>
                            <c:if test="${user.age >=20 && user.age<24}">
                                <option value="14" >16岁以下</option>
                                <option value="18">16-20岁</option>
                                <option value="22" selected>20-24岁</option>
                                <option value="26">24-28岁</option>
                                <option value="30">28岁以上</option>
                            </c:if>
                            <c:if test="${user.age >=24 && user.age<28}">
                                <option value="14" >16岁以下</option>
                                <option value="18">16-20岁</option>
                                <option value="22">20-24岁</option>
                                <option value="26" selected>24-28岁</option>
                                <option value="30">28岁以上</option>
                            </c:if>
                            <c:if test="${user.age >=28}">
                                <option value="14" >16岁以下</option>
                                <option value="18">16-20岁</option>
                                <option value="22">20-24岁</option>
                                <option value="26">24-28岁</option>
                                <option value="30" selected>28岁以上</option>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="work" class="col-sm-2 control-label">职业</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="work" id="work" value="${user.work}" placeholder="例如：程序猿">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="hometown" class="col-sm-2 control-label">家乡</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="hometown" id="hometown" value="${user.hometown}" placeholder="例如：重庆市">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="district" class="col-sm-2 control-label">现居</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="district" id="district" value="${user.district}" placeholder="例如：重庆市">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="school" class="col-sm-2 control-label">学校</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="school" id="school" value="${user.school}" placeholder="例如：重庆邮电大学">
                    </div>
                </div>
                <div class="form-group col-md-2 col-md-offset-5">
                    <button type="submit" class="btn-block btn btn-primary">筛选</button>
                </div>
            </fieldset>
        </form>
    </div>
    <h3>筛选结果</h3>
    <div class="row">

        <c:forEach items="${recommend}" var="item">
            <div class="col-md-4">
                <div class="recom-friend-item">
                    <div class="recom-friend-item-body clearfix">
                        <div class="recom-friend-item-left pull-left">
                            <img class="img-responsive img-rounded" src="${item.photo}" alt="...">
                        </div>
                        <div class="recom-friend-item-right pull-left">
                            <a href="/user/singlePage?userID=${item.userID}"> <span class="recom-friend-item-nickname">${item.userName}</span></a>
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
                        </div>
                    </div>
                    <div class="recom-friend-item-footer">
                        <div class="row">
                            <!--start平板以上屏幕大小显示-->
                            <div class="hidden-xs col-md-3 col-md-offset-2">
                                <a href="/user/singlePage?userID=${item.userID}" class="btn btn-default">查看主页</a>
                            </div>
                            <!--如果已经是好友就不要显示下面这个div-->
                            <div class="hidden-xs col-md-3 col-md-offset-2">
                                <input type="hidden" name="uid" value="${item.userID}">
                                <button class="addFriend btn btn-primary">添加好友</button>
                            </div>
                            <!--end平板以上屏幕大小显示-->

                            <!--start手机显示-->
                            <div class="visible-xs">
                                <a href="/user/singlePage?userID=${item.userID}" class="btn-block btn btn-default">查看主页</a>
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

</body>

</html>