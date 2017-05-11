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
                        <li class="contact-item list-group-item clearfix">
                            <div class="chat-item-info">
                                <img class="chat-avatar img-responsive img-circle pull-left" src="${item.photo}" alt="回复人头像">
                                <span class="chat-nickname pull-left">${item.userName}</span>
                                <span class="chat-status label label-success">在线</span>
                                <input id="${item.userID}" type="hidden" name="toUserId" value="${item.userID}">
                            </div>
                        </li>
                        </c:forEach>
                        <c:forEach var="item" items="${offline}">
                        <li class="contact-item list-group-item clearfix">
                            <div class="chat-item-info">
                                <img class="chat-avatar img-responsive img-circle pull-left" src="${item.photo}" alt="回复人头像">
                                <span class="chat-nickname pull-left">${item.userName}</span>
                                <span class="chat-status label label-default">离线</span>
                                <input id="${item.userID}" type="hidden" name="toUserId" value="${item.userID}">
                            </div>
                        </li>
                        </c:forEach>
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
                            <div class="chat-text">发现了一份名为《流动的盛宴》的清单，于是他们将手稿编订为与此书名匹配的一本书，后来的事大家都知道了，这本书成为了经典。最近还有一个新译本出来，主要卖点就是恢复了当时没有收录的部分。这个故事表明：编辑最初步也最核心的就是做编辑方案，其实就是做一个产品化的方案。</div>
                        </div>
                    </div>
                    <div class="chat-item chat-item-me clearfix">
                        <div class="avatar">
                            <img class="chat-avatar img-responsive img-circle" src="../../static/img/large.gif" alt="回复人头像">
                        </div>
                        <div class="content">
                            <div class="arrow-left"></div>
                            <div class="chat-text">发现了一份名为《流动的盛宴》的清单，于是他们将手稿编订为与此书名匹配的一本书，后来的事大家都知道了，这本书成为了经典。最近还有一个新译本出来，主要卖点就是恢复了当时没有收录的部分。这个故事表明：编辑最初步也最核心的就是做编辑方案，其实就是做一个产品化的方案。</div>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <div class="chat-text-input">
                        <textarea rows="6" cols="" class="form-control" name="chatText" id="chatText" placeholder="按Ctrl+Enter发送"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script>
    $(function () {
        function chat() {
            var websocket;
            var myAvatarUrl = $('#myAvatar').attr('src'); //记录自己的头像url
            var otherAvatarUrl = '';
            // 渲染聊天记录
            function renderChatHistory(data) {
                var $chatBoxCentent = $('.chat-box .panel-body');
                if (data.length) { //如果有聊天数据
                    for (i in data) {
                        if (data[i].me) { //如果是我发送给别人的消息
                            var html = '<div class="chat-item chat-item-me clearfix"><div class="avatar"><img class="chat-avatar img-responsive img-circle" src="' + myAvatarUrl + '" alt="回复人头像"></div><div class="content"><div class="arrow-left"></div><div class="chat-text">' + data[i].chatText + '</div></div></div>'
                        } else { //如果是别人发送给我的消息
                            var html = '<div class="chat-item chat-item-other clearfix"><div class="avatar"><img class="chat-avatar img-responsive img-circle" src="' + otherAvatarUrl + '" alt="回复人头像"></div><div class="content"><div class="arrow-right"></div><div class="chat-text">' + data[i].chatText + '</div></div></div>'
                        }
                        $chatBoxCentent.append($(html));
                        var scrollHeight = $chatBoxCentent.get(0).scrollHeight;
                        $chatBoxCentent.scrollTop(scrollHeight);
                    }
                }
            }
            // 切换聊天对象
            function switchChatObj() {
                var $chatListLi = $('.contact-item');
                var $chatBox = $('.chat-box');
                $chatListLi.click(function () { //点击一个项目，记录它的uid，移除原来的avtive，添加active到点击的这个li上面
                    if ($chatBox.css('display') == 'none') {
                        $chatBox.css('display', 'block');
                    }
                    var toUserId = $(this).find('input').val(); //聊天对象的id
                    otherAvatarUrl = $(this).find('img').attr('src'); //聊天对象的头像url
                    if ($(this).className != 'active-chat') { //如果这个不是当前已经选中的（再次点击当前选中的）
                        // 将已有的聊天记录删除
                        $('.chat-box .panel-body').empty();
                        // 渲染聊天记录
                        $.post('/getChatRecord', {
                            userID: toUserId
                        }, function (data) {
                            var jsonData = $.parseJSON(data);
                            renderChatHistory(jsonData);
                        });
                        $chatListLi.removeClass('active-chat');
                        $(this).addClass('active-chat');
                    }
                    $(this).find('.chat-tips').remove(); //移除新消息提醒，如果有的话，没有不会成功执行
                })
            }
            // 发送聊天信息
            function sendMsg() {
                var $chatTextarea = $('.chat-text-input').find('textarea');
                $chatTextarea.keydown(function (e) {
                    var toUserId = $('.active-chat').find('input').val(); //聊天对象的id
                    var sendChatText = $(this).val();
                    if (e.ctrlKey && e.keyCode == 13) {
                        var socketData = {
                            to: toUserId,
                            content: sendChatText,
                        };
                        renderChatHistory([{
                            chatText: sendChatText,
                            me: true
                        }]);
                        // 发送socket消息
                        if (websocket != null) {
                            websocket.send(JSON.stringify(socketData));
                        } else {
                            alert('未与服务器链接.');
                        }
                        $(this).val(''); //清空这个输入框
                    }
                })
            }
            //接收聊天消息
            function receiveMsg(data) {
                var toUserId = $('.active-chat').find('input').val(); //聊天对象的id
                if (data.from == toUserId) { //如果收到的消息是当前聊天对象的就直接在聊天窗口中渲染
                    renderChatHistory([{
                        chatText: data.content,
                        me: false
                    }])
                } else { //否则进行提醒
                    $('#' + data.from).parents('.chat-item-info').append('<span class="chat-tips label label-info">新消息</span>');
                }
            }
            switchChatObj(); //聊天对象选择
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://" + location.host + "/websocket");
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://websocket");
            } else {
                websocket = new SockJS("http://" + location.host + "/sockjs/websocket");
            }
            websocket.onopen = function (evnt) {};
            websocket.onmessage = function (evnt) {
                var jsonData = $.parseJSON(evnt.data);
                receiveMsg(jsonData);
            };
            websocket.onerror = function (evnt) {};
            websocket.onclose = function (evnt) {}
            sendMsg();
        }
        chat();
    })
</script>
</body>

</html>