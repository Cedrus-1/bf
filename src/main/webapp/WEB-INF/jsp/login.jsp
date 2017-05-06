<%--
  Created by IntelliJ IDEA.
  User: Cedrus
  Date: 2017/5/3
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="登录界面" />
    <script type="application/x-javascript">
        addEventListener("load", function() {setTimeout(hideURLbar, 0);}, false);function hideURLbar() {window.scrollTo(0,1);}
    </script>
    <link href="/static/css/style-login.css" rel="stylesheet" type="text/css" media="all" />
    <script src="/static/js/jquery-2.1.4.min.js"></script>
    <script src="/static/js/layer.js"></script>
    <script src="/static/js/script.js"></script>

</head>
<body>
<!-- main -->
<div class="main-agile">
    <h1>欢迎来到本网站</h1>
    <div id="w3ls_form" class="signin-form">
        <!-- Sign In Form -->
        <form id="signin" action="login" method="post" onsubmit="return checkLoginForm()" >
            <div class="ribbon"><a href="#" id="flipToRecover" class="flipLink" title="点击注册">注册</a></div>
            <h2>登录</h2>
            <input type="text" id="username" name="username" placeholder="用户名" required=""/>
            <input type="password" id="password" name="password" placeholder="密码" required=""/>
            <input type="checkbox" id="brand" value="">
            <label for="brand"><span></span> 记住我 ?</label>
            <input type="submit" id="submit1" value="登录">
            <div class="signin-agileits-bottom">
                <p><a href="#">忘记密码 ?</a></p>
            </div>
        </form>
        <!-- //Sign In Form -->
        <!-- Sign up Form-->
        <form id="signup" action="signIn" method="post">
            <div class="ribbon"><a href="#" id="flipToRecover1" class="flipLink" title="点击登录">登录</a></div>
            <h3>注册</h3>
            <input type="text" name="username" placeholder="用户名" required=""/>
            <input type="text" name="email" placeholder="你的邮箱" required=""/>
            <input type="password" name="password" placeholder="密码" required=""/>
            <input type="checkbox" id="brand1" value="">
            <label for="brand1"><span></span>我同意本网站的使用条例</label>
            <input type="submit" id="submit2"  value="注册">
        </form>
        <!-- Sign up Form-->
    </div>
</div>
<!-- //main -->

<script>
    $(function(){
        if("${error1}"){
            layer.open({
                offset: 't',
                title: '登陆失败',
                content: '${error1}'
            });

        }
        if("${error2}"){
            layer.open({
                offset: 't',
                title: '注册失败'
                ,content: '${error2}'
            });
        }

        if("${message}"){
            layer.msg('${message}', {
                offset: 0,
            });
        }

        $('.close').on('click', function(c){
            $('.login-form').fadeOut('slow', function(c){
                $('.login-form').remove();
            });
        });

        $('#username,#password').change(function(){
            $('#submit').attr('value','Login').css('background','#3ea751');
        });
    });

    /**
     * check the login form before user login.
     * @returns {boolean}
     */
    function checkLoginForm(){
        var username = $('#username').val();
        var password = $('#password').val();
        if(isNull(username) && isNull(password)){
            $('#submit').attr('value','请输入账号和密码!!!').css('background','red');
            return false;
        }
        if(isNull(username)){
            $('#submit').attr('value','请输入账号!!!').css('background','red');
            return false;
        }
        if(isNull(password)){
            $('#submit').attr('value','请输入密码!!!').css('background','red');
            return false;
        }
        else{
            $('#submit').attr('value','Logining~');
            return true;
        }
    }

    function isNull(input){
        if(input == null || input == '' || input == undefined){
            return true;
        }
        else{
            return false;
        }
    }
</script>
</body>
</html>