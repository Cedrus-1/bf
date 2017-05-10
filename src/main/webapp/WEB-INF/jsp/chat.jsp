<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>聊天</title>
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
        <div class="col-md-3">
            <div class="chat-panel panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">
                    <h3>好友列表</h3>
                </div>
                <div class="panel-body">
                    <!-- List group -->
                    <ul class="list-group">
                        <c:forEach var="item" items="${online}">
                            <li class="list-group-item clearfix">
                                <div class="chat-item-info">
                                    <img class="chat-avatar img-responsive img-circle pull-left" src="${item.photo}" alt="回复人头像">
                                    <span class="chat-nickname pull-left">${item.userName}</span>[在线]
                                </div>
                            </li>
                        </c:forEach>
                        <c:forEach var="item" items="${offline}">
                            <li class="list-group-item clearfix">
                                <div class="chat-item-info">
                                    <img class="chat-avatar img-responsive img-circle pull-left" src="${item.photo}" alt="回复人头像">
                                    <span class="chat-nickname pull-left">${item.userName}</span>[离线]
                                </div>
                            </li>
                        </c:forEach>
                       <%-- <li class="active-chat list-group-item clearfix">
                            <div class="chat-item-info">
                                <img class="chat-avatar img-responsive img-circle pull-left" src="../../static/img/large.gif" alt="回复人头像">
                                <span class="chat-nickname pull-left">tom</span>
                            </div>
                        </li>--%>

                    </ul>
                </div>
            </div>
        </div>
        <div class="chat-box col-md-9">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="chat-item chat-item-other clearfix">
                        <div class="avatar">
                            <img class="chat-avatar img-responsive img-circle" src="../../static/img/large.gif" alt="回复人头像">
                        </div>
                        <div class="content">
                            <div class="arrow-right"></div>
                            <div class="text">发现了一份名为《流动的盛宴》的清单，于是他们将手稿编订为与此书名匹配的一本书，后来的事大家都知道了，这本书成为了经典。最近还有一个新译本出来，主要卖点就是恢复了当时没有收录的部分。这个故事表明：编辑最初步也最核心的就是做编辑方案，其实就是做一个产品化的方案。</div>
                        </div>
                    </div>
                    <div class="chat-item chat-item-me clearfix">
                        <div class="avatar">
                            <img class="chat-avatar img-responsive img-circle" src="../../static/img/large.gif" alt="回复人头像">
                        </div>
                        <div class="content">
                            <div class="arrow-left"></div>
                            <div class="text">发现了一份名为《流动的盛宴》的清单，于是他们将手稿编订为与此书名匹配的一本书，后来的事大家都知道了，这本书成为了经典。最近还有一个新译本出来，主要卖点就是恢复了当时没有收录的部分。这个故事表明：编辑最初步也最核心的就是做编辑方案，其实就是做一个产品化的方案。</div>
                        </div>
                    </div>

                </div>
                <div class="panel-footer">
                    <div class="chat-text-input">
                        <textarea rows="6" cols="" class="form-control" name="chatText" id="chatText" placeholder="按Ctrl+Enter发送"></textarea>
                        <div class="form-group pull-right">
                            <button class="rep-btn btn btn-primary" type="button" onclick="sendMessage()">回复</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

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

    var wsServer = "ws://" + location.host+"${pageContext.request.contextPath}" + "/websocket";
    var ws ;
    var initWebSocket = function() {
        if (window.WebSocket) {
            if (window.WebSocket)
                ws = new WebSocket(wsServer);
            ws.onmessage = function(event) {
                var json = JSON.parse(event.data);
                document.getElementById("message").value = json.message;
            };
            ws.onopen = function(event) {
                alert("Web Socket opened!");
            };
            ws.onclose = function(event) {
                alert("Web Socket closed.");
            };
            ws.onerror = function(event) {
                alert("Web Socket error.");
            };
        } else {
            alert("Your browser does not support Web Socket.");
        }
    }
    initWebSocket();

    /**
     * 连接
     */
    function getConnection(){
        if(ws == null){
            ws = new WebSocket(wsServer); //创建WebSocket对象
            ws.onopen = function (evt) {
                layer.msg("成功建立连接!", { offset: 0});
            };
            ws.onmessage = function (evt) {
                analysisMessage(evt.data);  //解析后台传回的消息,并予以展示
            };
            ws.onerror = function (evt) {
                layer.msg("产生异常", { offset: 0});
            };
            ws.onclose = function (evt) {
                layer.msg("已经关闭连接", { offset: 0});
            };
        }else{
            layer.msg("连接已存在!", { offset: 0, shift: 6 });
        }
    }

    /**
     * 关闭连接
     */
    function closeConnection(){
        if(ws != null){
            ws.close();
            ws = null;
            $("#list").html("");    //清空在线列表
            layer.msg("已经关闭连接", { offset: 0});
        }else{
            layer.msg("未开启连接", { offset: 0, shift: 6 });
        }
    }

    /**
     * 检查连接
     */
    function checkConnection(){
        if(ws != null){
            layer.msg(ws.readyState == 0? "连接异常":"连接正常", { offset: 0});
        }else{
            layer.msg("连接未开启!", { offset: 0, shift: 6 });
        }
    }

    /**
     * 发送信息给后台
     */
    function sendMessage(){
        if(ws == null){
            layer.msg("连接未开启!", { offset: 0, shift: 6 });
            return;
        }
        var message = $("#chatText").val();
        var to = $("#sendto").text() == "全体成员"? "": $("#sendto").text();
        if(message == null || message == ""){
            layer.msg("请不要惜字如金!", { offset: 0, shift: 6 });
            return;
        }
        ws.send(JSON.stringify({
            message : {
                content : message,
                from : ${sessionScope.get("userID")},
                to : to,      //接收人,如果没有则置空,如果有多个接收人则用,分隔
                time : getDateFull()
            },
            type : "message"
        }));
    }

    /**
     * 解析后台传来的消息
     * "massage" : {
     *              "from" : "xxx",
     *              "to" : "xxx",
     *              "content" : "xxx",
     *              "time" : "xxxx.xx.xx"
     *          },
     * "type" : {notice|message},
     * "list" : {[xx],[xx],[xx]}
     */
    function analysisMessage(message){
        message = JSON.parse(message);
        if(message.type == "message"){      //会话消息
            showChat(message.message);
        }
        if(message.type == "notice"){       //提示消息
            showNotice(message.message);
        }
        if(message.list != null && message.list != undefined){      //在线列表
            showOnline(message.list);
        }
    }

    /**
     * 展示提示信息
     */
    function showNotice(notice){
        $("#chat").append("<div><p class=\"am-text-success\" style=\"text-align:center\"><span class=\"am-icon-bell\"></span> "+notice+"</p></div>");
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
    }

    /**
     * 展示会话信息
     */
    function showChat(message){
        var to = message.to == null || message.to == ""? "全体成员" : message.to;   //获取接收人
        var isSef = '${user.userID}' == message.from ? "am-comment-flip" : "";   //如果是自己则显示在右边,他人信息显示在左边
        var html = "<li class=\"am-comment "+isSef+" am-comment-primary\"><a href=\"#link-to-user-home\"><img width=\"48\" height=\"48\" class=\"am-comment-avatar\" alt=\"\" src=\"${ctx}/"+message.from+"/head\"></a><div class=\"am-comment-main\">\n" +
            "<header class=\"am-comment-hd\"><div class=\"am-comment-meta\">   <a class=\"am-comment-author\" href=\"#link-to-user\">"+message.from+"</a> 发表于<time> "+message.time+"</time> 发送给: "+to+" </div></header><div class=\"am-comment-bd\"> <p>"+message.content+"</p></div></div></li>";
        $("#chat").append(html);
        $("#message").val("");  //清空输入区
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
    }

    /**
     * 展示在线列表
     */
    function showOnline(list){
        $("#list").html("");    //清空在线列表
        $.each(list, function(index, item){     //添加私聊按钮
            for(var key in item){
                console.log(key + ": " + item[key]);
            }

            var li = "<li>"+item+"</li>";
            if('${user.userID}' != item['userID']){    //排除自己
                li = "<li>"+item['userName']+" <button type=\"button\" class=\"am-btn am-btn-xs am-btn-primary am-round\" onclick=\"addChat('"+item['userID']+"');\"><span class=\"am-icon-phone\"><span> 私聊</button></li>";
            }
            $("#list").append(li);
        });
        $("#onlinenum").text($("#list li").length);     //获取在线人数
    }

    function addChat(user){
        var sendto = $("#sendto");
        sendto.text(user);
    }

    function clearConsole(){
        $("#chat").html("");
    }
    function appendZero(s){return ("00"+ s).substr((s+"").length);}  //补0函数
    function getDateFull(){
        var date = new Date();
        var currentdate = date.getFullYear() + "-" + appendZero(date.getMonth() + 1) + "-" + appendZero(date.getDate()) + " " + appendZero(date.getHours()) + ":" + appendZero(date.getMinutes()) + ":" + appendZero(date.getSeconds());
        return currentdate;
    }

</script>
</body>

</html>