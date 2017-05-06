<%--
  Created by IntelliJ IDEA.
  User: Cedrus
  Date: 2017/5/3
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="collapse navbar-collapse main-navbar-collapse">
                    <a class="navbar-brand" href=""><strong>在线交友网站</strong></a>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i> 欢迎您，${sessionScope.get("username")} <i class="caret"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">资料</a></li>
                                <li role="presentation" class="divider"></li>
                                <li><a href="/logout">注销</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>
--%>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--<a class="navbar-brand page-scroll" href="#page-top">Your Wedding</a>--%>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav nav-justified ">
                <li class="hidden">
                    <a href="#page-top"></a>
                </li>
                <li class="active">
                    <a class="page-scroll" href="/index">个人主页</a>
                </li>
                <li>
                    <a class="page-scroll" href="#">热门动态</a>
                </li>
                <li>
                    <a class="page-scroll" href="#">个人动态</a>
                </li>
                <li>
                    <a class="page-scroll" href="#">留言板</a>
                </li>
                <li>
                    <a class="page-scroll" href="#">个人设置</a>
                </li>
                <li class="dropdown">
                    <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i> 欢迎您，${sessionScope.get("username")} <i class="caret"></i></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">资料</a></li>
                        <li role="presentation" class="divider"></li>
                        <li><a href="/logout">注销</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
