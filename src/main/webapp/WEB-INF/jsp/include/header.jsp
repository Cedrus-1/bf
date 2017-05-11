
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-default">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/user/index">BF</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li ><a href="/user/index">主页</a></li>
                <li><a href="/user/findFriend">发现朋友</a></li>
                <li><a href="/user/hot?pageNum=1">热点</a></li>
                <li><a href="/user/chat">聊天</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right aaa">
                <li><img id="myAvatar" src="${sessionScope.get("photo")}"
                         class="avatar-me img-responsive img-rounded" alt="Responsive image"></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.get("username")} <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/profile?pageNum=1">个人中心</a></li>
                        <li><a href="/user/messageBox">消息</a></li>
                        <li><a href="/user/setting">设置</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/logout">注销</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>