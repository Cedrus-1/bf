<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>热点</title>
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
        <div class="col-md-5">
            <form action="/addDynamic" method="post" class="dynamic-form form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <textarea class="form-control" rows="6" id="dynamicText" name="content" placeholder="动态内容"></textarea>
                </div>
                <div class="form-group">
                    <input type="file" name="dynamicImg" id="dynamicImg" class="form-control" placeholder="选择图片">
                </div>
                <div class="form-group">
                    <button type="submit" class="center-block btn btn-primary">发布动态</button>
                </div>
            </form>
        </div>
        <div class="col-md-7">
            <h3 class="dynamicH3">动态</h3>
            <!--动态列表-->
            <c:forEach var="item" items="${pages.pageDatas}">
            <div class="dynamicItem">
                <div class="dynamicBody">
                   <%-- <img class="img-responsive img-rounded" src="${item.dynamicUser.photo}" alt="...">--%>
                    <div class="dynamicBodyContent">${item.content}</div>
                    <c:if test="${item.photo!=null && item.photo!=''}">
                        <img class="dynamicImg img-responsive img-rounded" src="${item.photo}" alt="imgPath">
                    </c:if>
                </div>
                <div class="dynamicFooter clearfix">
                    <div class="dynamicItem-info pull-left">
                        <span class="glyphicon glyphicon-user"></span>
                        <a href="/user/singlePage?userID=${item.dynamicUserID}">${item.dynamicUser.userName}</a>
                    </div>
                    <div class="dynamicItem-info pull-left">
                        <span class="glyphicon glyphicon-time"></span>
                        <span><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${item.dynamicTime}"/></span>
                    </div>
                    <div class="dynamicItem-info pull-left">
                        <input type="hidden" name="uid" value="${item.dynamicID}">
                        <span class="like-btn glyphicon glyphicon-thumbs-up"></span> <span class="like-num">${item.likeNumber}</span>
                    </div>
                </div>
                <!--回复列表-->

                <div class="rep-list">
                    <div class="arrow" data-toggle="tooltip" data-placement="bottom" title="点击切换显示回复"></div>
                    <c:forEach var="comment" items="${item.comments}">
                    <div class="rep-item clearfix">
                        <div class="rep-item-from pull-left">
                            <img class="img-responsive img-rounded" src="${comment.commentUser.photo}" alt="回复人头像">
                            <span>${comment.commentUser.userName}</span>
                        </div>
                        <p class="rep-item-content">${comment.comment}</p>
                    </div>
                    </c:forEach>
                </div>

                <!--回复输入框-->
                <form action="/addComment" method="POST" class="rep-form clearfix">
                    <div class="form-group">
                        <!--留言的ID，隐藏域-->
                        <input type="hidden" name="dynamicID" value="${item.dynamicID}">
                        <input type="hidden" name="receiveUserID" value="${item.dynamicUserID}">
                        <input type="hidden" name="pageNum" value="${pages.pageIndex}">
                        <textarea rows="3" cols="20" name="content" class="form-control" placeholder="输入你的评论内容"></textarea>
                    </div>
                    <div class="form-group pull-right">
                        <button class="rep-btn btn btn-primary" type="submit">评论</button>
                    </div>
                </form>
            </div>
            </c:forEach>
            <div class="panel-footer">
                <nav aria-label="...">
                    <ul class="pager">
                        <li><a href="/user/profile?pageNum=1">首页</a></li>
                        <c:if test="${pages.isHavePrePage}">
                            <li><a href="/user/hot?pageNum=${pages.pageIndex-1}">上一页</a></li>
                        </c:if>
                        <c:if test="${!pages.isHavePrePage}">
                            <li><a href="/user/hot?pageNum=${pages.pageIndex}">上一页</a></li>
                        </c:if>
                        <c:if test="${pages.isHaveNextPage}">
                            <li><a href="/user/hot?pageNum=${pages.pageIndex+1}">下一页</a></li>
                        </c:if>
                        <c:if test="${!pages.isHaveNextPage}">
                            <li><a href="/user/hot?pageNum=${pages.pageIndex}">下一页</a></li>
                        </c:if>
                        <li><a href="/user/hot?pageNum=${pages.totalPages}">尾页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<script>
    if("${message}"){
        layer.open({
            title: '发布动态',
            content: '${message}'
        });
    }
    if("${error}"){
        layer.open({
            title: '发布动态',
            content: '${error}'
        });
    }
</script>
</body>

</html>