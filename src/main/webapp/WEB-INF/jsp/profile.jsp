<%--
  Created by IntelliJ IDEA.
  User: Cedrus
  Date: 2017/5/9
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>主页</title>
    <link rel="stylesheet" href="../../static/css/paper-bootstrap.min.css">
    <link rel="stylesheet" href="../../static/css/layer.css">
    <link rel="stylesheet" href="../../static/css/main.css">

</head>

<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-4 profile-info">
            <div class="profile-info-header">
                <img class="profile-avatar img-responsive img-circle" data-toggle="tooltip" data-placement="right" title="心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。"
                     src="../../static/img/large.gif" alt="...">
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="text-center">About Me</h2>
                </div>
                <div class="panel-body">
                    <dl>
                        <dt>昵称</dt>
                        <dd>tom</dd>
                        <dt>性别</dt>
                        <dd>男</dd>
                        <dt>年龄</dt>
                        <dd>20</dd>
                        <dt>邮箱</dt>
                        <dd>123456789@qq.com</dd>
                        <dt>电话</dt>
                        <dd>4159155151</dd>
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
                    <div class="leaveMsgItem">
                        <div class="leaveMsgItem-head clearfix">
                            <div class="pull-left">
                                <span class="floor-num">5楼:</span>
                                <span class="leaveMsg-time">2017-02-02</span>
                            </div>
                            <div class="pull-right">
                                <button class="btn btn-sm btn-danger">删除</button>
                            </div>
                        </div>
                        <div class="leaveMsgItem-body clearfix">
                            <div class="leaveMsgItem-left pull-left">
                                <span class="leaveMsg-from">Tom</span>
                                <img class="img-responsive img-rounded" src="../../static/img/large.gif" alt="...">
                            </div>
                            <div class="leaveMsgItem-right pull-left">
                                <div class="leaveMsgItem-text">
                                    心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。心里有太多浓重的阴影，被生活混淆的烦躁不安。
                                </div>
                            </div>
                        </div>
                        <div class="rep-list">
                            <div class="arrow"></div>
                            <p>ddsdsdsdsds</p>
                            <p>ddsdsdsdsds</p>
                            <p>ddsdsdsdsds</p>
                        </div>
                        <div class="leaveMsgItem-footer">
                            <form action="#" method="POST" class="rep-form clearfix">
                                <div class="form-group">
                                    <input type="hidden" name="uid" value="这里是这个人的uid">
                                    <textarea rows="6" cols="20" name="rep-text" class="form-control" placeholder="输入你的回复内容"></textarea>
                                </div>
                                <div class="form-group pull-right">
                                    <button class="rep-btn btn btn-primary" type="submit">回复</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../static/js/jquery-2.1.4.min.js"></script>
<script src="../../static/js/bootstrap.min.js"></script>
<script src="../../static/js/layer.js"></script>
<script src="../../static/js/main.js"></script>
</body>

</html>