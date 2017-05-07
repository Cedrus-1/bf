<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Cedrus
  Date: 2017/5/3
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="include/common.jsp"/>
    <title>主页</title>
</head>
<body>
<%--<jsp:include page="include/header.jsp"/>--%>
<div >
    <ul>
        <c:forEach var="item" items="${dynamicPage.pageDatas}">
            <li>动态ID：${item.dynamicID}</li>
            <li>动态时间：${item.dynamicTime}</li>
            <li>发表动态人ID：${item.dynamicUserID}</li>
            <li>动态内容${item.content}</li>
            <ul>
            <c:forEach var="comment" items="${item.comments}">
                <li>评论：${comment.comment}</li>
                <li>评论时间：${comment.commentTime}</li>
            </c:forEach>
            </ul>
        </c:forEach>

    </ul>


</div>

<script>
    if("${message}"){
        layer.msg('${message}', {
            offset: 0
        });
    }
    if("${error}"){
        layer.msg('${error}', {
            offset: 0,
            shift: 6
        });
    }

</script>
</body>
</html>
