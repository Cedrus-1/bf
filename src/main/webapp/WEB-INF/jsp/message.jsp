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
    <div class="col-md-6 col-md-offset-3">
        <h3 class="text-center">消息盒子</h3>
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            留言提醒
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <ul class="list-group">
                            <c:forEach var="item" items="${leaveWordMessage}">
                            <li class="list-group-item clearfix">
                                <div class="clearfix">
                                    <div class="info">
                                        <img class="img-responsive img-rounded" src="${item.sendUser.photo}" alt="...">
                                        <a href="/user/singlePage?userID=${item.sendUser.userID}" class="from">${item.sendUser.userName}</a>
                                    </div>
                                    <p class="text">给你留言：${item.content}</p>
                                </div>
                                <div class="pull-right">
                                    <input type="hidden" name="leave-msg-id" value="${item.messageID}">
                                    <a href="/user/profile?pageNum=1" class="btn btn-sm btn-success">查看</a>
                                    <button class="leave-ignore-btn btn btn-sm btn-default">忽略</button>
                                </div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                           aria-controls="collapseTwo">
                            动态评论
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <ul class="list-group">
                            <c:forEach var="item" items="${commentMessage}">
                            <li class="list-group-item clearfix">
                                <div class="clearfix">
                                    <div class="info">
                                        <img class="img-responsive img-rounded" src="${item.sendUser.photo}" alt="...">
                                        <a href="/user/singlePage?userID=${item.sendUser.userID}" class="from">${item.sendUser.userName}</a>
                                    </div>
                                    <p class="text">评论了你的动态：${item.content}</p>
                                </div>
                                <div class="pull-right">
                                    <input type="hidden" name="dynamic-msg-id" value="${item.messageID}">
                                    <button class="dynamic-ignore-btn btn btn-sm btn-default">我知道了</button>
                                </div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false"
                           aria-controls="collapseThree">
                            好友申请
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <ul class="list-group">
                            <c:forEach var="item" items="${friendMessage}">
                            <li class="list-group-item clearfix">
                                <div class="apply-msg-info pull-left">
                                    <div class="info">
                                        <img class="img-responsive img-rounded" src="${item.sendUser.photo}" alt="...">
                                        <a href="/user/singlePage?userID=${item.sendUser.userID}" class="from">${item.sendUser.userName}</a>
                                        <span>申请添加你为好友</span>
                                    </div>
                                </div>
                                <div class="pull-right">
                                    <input type="hidden" name="apply-msg-id" value="${item.messageID}">
                                    <button class="apply-pass-btn btn btn-sm btn-success">同意</button>
                                    <button class="apply-ref-btn btn btn-sm btn-danger">拒绝</button>
                                </div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>