<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="/static/js/jquery-2.1.4.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/layer.js"></script>
    <script src="/static/js/main.js"></script>

</head>

<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-4 profile-info">
            <div class="profile-info-header">
                <img class="profile-avatar img-responsive img-circle" data-toggle="tooltip" data-placement="right"
                     title="${user.personalizedSignature}"
                     src="/${user.photo}" alt="...">
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="text-center">About Me</h2>
                </div>
                <div class="panel-body">
                    <dl>
                        <dt>昵称</dt>
                        <dd>${user.userName}</dd>
                        <dt>性别</dt>
                        <c:if test="${user.sex==0}">
                            <dd>保密</dd>
                        </c:if>
                        <c:if test="${user.sex==1}">
                            <dd>男</dd>
                        </c:if>
                        <c:if test="${user.sex==2}">
                            <dd>女</dd>
                        </c:if>
                        <dt>年龄</dt>
                        <dd>${user.age}</dd>
                        <dt>邮箱</dt>
                        <dd>${user.email}</dd>
                        <dt>电话</dt>
                        <dd>${user.phone}</dd>
                    </dl>
                </div>
            </div>
        </div>
        <div class="col-md-8 profile-leaveWall">
            <div class="hidden-xs line"></div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h2>留言墙</h2>
                </div>
                <div class="panel-body">
                    <!--每一个留言项，此处forEach-->
                    <c:forEach var="item" items="${pages.pageDatas}">
                        <div class="leaveMsgItem">
                            <div class="leaveMsgItem-head clearfix">
                                <div class="pull-left">
                                    <span class="floor-num">${pages.pageDatas.size()-pages.pageDatas.indexOf(item)}楼:</span>
                                    <span class="leaveMsg-time">
                                    <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                                    value="${item.get(0).time}"/>
                                </span>
                                </div>
                                <div class="pull-right">
                                    <button class="deleteLeaveMsg-btn btn btn-sm btn-danger">删除</button>
                                    <!--留言的ID，隐藏域，下面回复输入框还有一个，懒得一层一层获取，你填充两个吧-->
                                    <input type="hidden" name="uid" value="${item.get(0).leaveWordID}">
                                </div>
                            </div>
                            <div class="leaveMsgItem-body clearfix">
                                <div class="leaveMsgItem-left pull-left">
                                    <span class="leaveMsg-from">${item.get(0).sendUser.userName}</span>
                                    <img class="img-responsive img-rounded" src="/${item.get(0).sendUser.photo}"
                                         alt="...">
                                </div>
                                <div class="leaveMsgItem-right pull-left">
                                    <div class="leaveMsgItem-text">
                                            ${item.get(0).leaveWord}
                                    </div>
                                </div>
                            </div>
                            <div class="rep-list">
                                <div class="arrow" data-toggle="tooltip" data-placement="bottom" title="点击显示回复"></div>
                                <c:forEach var="leaveWord" items="${item}">
                                    <c:if test="${item.indexOf(leaveWord)!=0}">
                                        <div class="rep-item clearfix">
                                            <div class="rep-item-from pull-left">
                                                <img class="img-responsive img-rounded"
                                                     src="/${leaveWord.sendUser.photo}" alt="回复人头像">
                                                <span>${leaveWord.sendUser.userName}</span>
                                            </div>
                                            <span class="pull-left">回复</span>
                                            <div class="rep-item-to pull-left">
                                                <img class="img-responsive img-rounded"
                                                     src="/${leaveWord.receiveUser.photo}" alt="被回复人头像">
                                                <span>${leaveWord.receiveUser.userName}</span>
                                            </div>
                                            <p class="rep-item-content">${leaveWord.leaveWord}</p>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="leaveMsgItem-footer">
                                <form action="/addLeaveWord" method="POST" class="rep-form clearfix">
                                    <div class="form-group">
                                        <input type="hidden" name="sendUserID" value="${sessionScope.get("userID")}">
                                        <c:if test="${item.get(0).sendUserID==sessionScope.get('userID')}">
                                            <input type="hidden" id="receiveUserID" name="receiveUserID" value="${item.get(0).receiveUserID}">
                                        </c:if>
                                        <c:if test="${item.get(0).receiveUserID==sessionScope.get('userID')}">
                                            <input type="hidden" name="receiveUserID" value="${item.get(0).sendUserID}">
                                        </c:if>
                                        <input type="hidden" name="parentLeaveWordID" value="${item.get(0).leaveWordID}">
                                        <textarea rows="1" cols="20" id="content" name="content" class="form-control"
                                                  placeholder="输入你的回复内容"></textarea>
                                    </div>
                                    <div class="form-group pull-right">
                                        <button class="rep-btn btn btn-primary" type="button">回复</button>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    if("${message}"){
        layer.open({
            title: '添加留言',
            content: '${message}'
        });
    }
    if("${error}"){
        layer.open({
            title: '添加留言',
            content: '${error}'
        });
    }
</script>

</body>

</html>