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
<html>
<head>
    <jsp:include page="include/common.jsp"/>
    <title>Title</title>
</head>
<body>
<%--<jsp:include page="include/header.jsp"/>--%>
<div >

    <div class="am-tab-panel am-fade" id="tab2">
        <form class="am-form am-form-horizontal" action="/user/upload" enctype="multipart/form-data" method="post"  >
            <div style="margin-bottom: 10px">
                <img class="am-circle" src="${ctx}/${user.photo}" width="140" height="140" alt=""/>
            </div>
            <div class="am-form-group am-form-file">
                <button type="button" class="am-btn am-btn-secondary am-btn-sm">
                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
                <input id="file" type="file" name="file" multiple>
            </div>
            <div id="file-list"></div>
            <button type="submit" class="am-btn am-round am-btn-success"><span class="am-icon-upload"></span> 上传头像</button>
            <script>
                $(function() {
                    $('#file').on('change', function() {
                        var fileNames = '';
                        $.each(this.files, function() {
                            fileNames += '<span class="am-badge">' + this.name + '</span> ';
                        });
                        $('#file-list').html(fileNames);
                    });
                });
            </script>
        </form>
    </div>
    <ul>
        <c:forEach var="item" items="${dynamicPage.pageDatas}">
            <li>动态ID：${item.dynamicID}</li>
            <li>动态时间：${item.dynamicTime}</li>
            <li>发表动态人ID：${item.dynamicUserID}</li>
            <li>动态内容：${item.content}</li>
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
