<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>设置</title>
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
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" id="settingTabs" role="tablist">
        <li role="presentation" class="active"><a href="#profileDetail" aria-controls="profileDetail" role="tab" data-toggle="tab">基本资料</a></li>
        <li role="presentation"><a href="#profileAccount" aria-controls="profileAccount" role="profileAccount" data-toggle="tab">账号和密码</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="profileDetail">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-horizontal" action="/user/updateInfo" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="sex" class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-6">
                                <select class="form-control" name="sex" id="sex">
                                    <c:if test="${user.sex==0}" >
                                        <option value="1">男</option>
                                        <option value="2">女</option>
                                        <option value="0" selected>保密</option>
                                    </c:if>
                                    <c:if test="${user.sex==1}" >
                                        <option value="1" selected>男</option>
                                        <option value="2">女</option>
                                        <option value="0" >保密</option>
                                    </c:if>
                                    <c:if test="${user.sex==2}" >
                                        <option value="1" >男</option>
                                        <option value="2" selected>女</option>
                                        <option value="0" >保密</option>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="birthday" class="col-sm-2 control-label">生日</label>
                            <div class="col-sm-6">
                                <input type="date" class="form-control" name="birthday" value="<fmt:formatDate value="${user.birth }" pattern="yyyy-MM-dd"/>" id="birthday" placeholder="填写你的生日">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="work" class="col-sm-2 control-label">职业</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="work" id="work" value="${user.work}" placeholder="告诉我们你的职业吧">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="hometown" class="col-sm-2 control-label">家乡</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="hometown" id="hometown" value="${user.hometown}" placeholder="故乡是最回不去地方">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="district" class="col-sm-2 control-label">现居地</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="district" id="district" value="${user.district}" placeholder="告诉我们你现在在哪里，帮助你找到好朋友">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="school" class="col-sm-2 control-label">学校</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="school" id="school" value="${user.school}" placeholder="告诉我们你的学校，方便找到你的同学">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label">手机</label>
                            <div class="col-sm-6">
                                <input type="number" class="form-control" name="phone" id="phone" value="${user.phone}" placeholder="告诉我们你的手机，不会向陌生人显示">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="perSig" class="col-sm-2 control-label">个性签名</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="perSig" id="perSig" value="${user.personalizedSignature}" placeholder="来一段个性签名吧">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="photo" class="col-sm-2 control-label">头像</label>
                            <div class="col-sm-6">
                                <input type="file" class="form-control" name="photo" id="photo"  placeholder="选择头像">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">更新资料</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="profileAccount">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="/user/updatePassword" method="POST" class="form-horizontal">
                        <div class="form-group">
                            <label for="oldPassword" class="col-sm-2 control-label">原密码</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" name="oldPassword" id="oldPassword" placeholder="请输入原密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newPassword" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="请输入新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-warning">修改</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    if("${message}"){
        layer.open({
            title: '修改信息',
            content: '${message}'
        });
    }
    if("${error}"){
        layer.open({
            title: '修改信息',
            content: '${error}'
        });
    }
</script>
</body>

</html>