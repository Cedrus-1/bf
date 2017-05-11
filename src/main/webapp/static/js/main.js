$(function () {
    // 初始化
    function init() {
        $('[data-toggle="tooltip"]').tooltip({
            container: 'body'
        });
        addFriend();
        repLeave();
        deleteLeaveMsg();
        switchRepitem();
        dynamicLike();
        ignoreMsg('leave-ignore-btn', '/readMessage', false); //忽略留言提醒
        ignoreMsg('dynamic-ignore-btn', '/readMessage', false); //忽略动态提醒
        ignoreMsg('apply-ref-btn', '/refuseApply', false); //拒绝添加好友
        ignoreMsg('apply-pass-btn', '/agreeApply', true); //通过添加好友
        chat();
    };

    // 添加好友
    function addFriend() {
        var $addFriendBtn = $('.addFriend');
        $addFriendBtn.click(function (e) {
            e.preventDefault();
            var $uid = $(this).prevAll('input').val();
            alert($uid);
            var $that = $(this);
            $.post('/addFriend', {
                userID: $uid
            }, function (data) {
                /* var jsonData = $.parseJSON(data);*/
                if (data) {
                    layer.open({
                        title: '好友申请',
                        content: '好友申请成功'
                    });
                    $that.text('已申请好友').removeClass('btn-primary').addClass('btn-success disabled').unbind("click");
                } else {
                    layer.open({
                        title: '好友申请',
                        content: '你已经申请过了'
                    });
                    $that.text('已申请好友').removeClass('btn-primary').addClass('btn-success disabled').unbind("click");
                }
            })
        });
    };

    // 删除留言
    function deleteLeaveMsg() {
        var $deleteLeaveMsgBtn = $('.deleteLeaveMsg-btn');
        $deleteLeaveMsgBtn.click(function () {
            var deleteUid = $(this).next().val();
            var $leaveMsgItem = $(this).parents('.leaveMsgItem');
            $.post('/deleteLeaveWord', {
                uid: deleteUid //删除留言的uid
            }, function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData) {
                    $leaveMsgItem.remove();
                    layer.open({
                        title: '删除留言',
                        content: '删除留言成功'
                    });
                } else {
                    layer.open({
                        title: '删除留言',
                        content: '删除留言失败，请稍后重试'
                    });
                }
            });
        });
    };

    // 回复留言
    function repLeave() {
        var $repLeaveBtn = $('.leaveMsgItem-footer .rep-btn');
        $repLeaveBtn.click(function () {
            var $Thisfooter = $(this).parents('.leaveMsgItem-footer');
            var $inputList = $Thisfooter.find('input');
            var $repText = $Thisfooter.find('textarea').val();
            $.post('/addLeaveWord', {
                leaveWordID: $inputList.eq(2).val(), //回复的留言的uid
                sendUserID: $inputList.eq(0).val(),
                receiveUserID: $inputList.eq(1).val(),
                content: $repText //回复内容
            }, function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData) {
                    location.reload();
                } else {
                    layer.open({
                        title: '删除留言',
                        content: '删除留言失败，请稍后重试'
                    });
                }
            });
        });
    };

    // 留言板回复内容切换显示
    function switchRepitem() {
        var $arrow = $('.arrow');
        $arrow.click(function () {
            var $afterRepItem = $(this).nextAll();
            $afterRepItem.slideToggle("slow");
        });
    };

    // 动态点赞
    function dynamicLike() {
        var $likeBtn = $('.like-btn');
        $likeBtn.click(function () {
            var $btnNum = $(this).next(),
                dynamicUid = $(this).prev().val();
            $.post('/dynamicLike', {
                dynamicUid: dynamicUid, //动态的id
            }, function (data) {
                /*var jsonData = $.parseJSON(data);*/
                if (data >= 0) {
                    $btnNum.text(data);
                } else {
                    layer.open({
                        title: '点赞',
                        content: '你已经点过赞了哦'
                    });
                }
            })
        })
    }

    // 忽略消息提醒
    /*
        params:{
            btnclass:进行忽略操作的按钮的class
            url:进行忽略操作的url
        }
    */
    function ignoreMsg(btnClass, url, isAdd) {
        var $IgnoreBtn = $('.' + btnClass);
        $IgnoreBtn.click(function () {
            var $thisMsg = $(this).parents('li');
            var $inputMsgId = $(this).prevAll('input');
            var That = $(this);
            $.post(url, {
                messageID: $inputMsgId.val() //要忽略的那条留言消息提醒的ID，数据库直接删除这条消息 
            }, function (data) {
                if (data) {
                    if (isAdd) {
                        That.text('已通过').addClass('disabled').off('click');
                        That.next().remove();
                        layer.open({
                            title: '消息提示',
                            content: '添加好友成功'
                        });
                    } else {
                        $thisMsg.remove(); //忽略成功，页面直接执行dom操作删除这条消息，不用刷新
                    }
                } else {
                    layer.open({
                        title: '消息提示',
                        content: '忽略失败，请稍后重试'
                    });
                }
            });
        });
    }

    // 渲染聊天记录
    function renderChatHistory(data, myAvatarUrl, otherAvatarUrl) {
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
    function switchChatObj(myAvatarUrl) {
        var $chatListLi = $('.contact-item');
        var $chatBox = $('.chat-box');
        $chatListLi.click(function () { //点击一个项目，记录它的uid，移除原来的avtive，添加active到点击的这个li上面
            if ($chatBox.css('display') == 'none') {
                $chatBox.css('display', 'block');
            }
            var toUserId = $(this).find('input').val(); //聊天对象的id
            var otherAvatarUrl = $(this).find('img').attr('src'); //聊天对象的头像url
            if ($(this).className != 'active-chat') { //如果这个不是当前已经选中的（再次点击当前选中的）
                // 将已有的聊天记录删除
                $('.chat-box .panel-body').empty();
                // 渲染聊天记录
                $.post('/test/switchChat', {
                    userID: toUserId
                }, function (data) {
                    var jsonData = $.parseJSON(data);
                    renderChatHistory(jsonData, myAvatarUrl, otherAvatarUrl);
                });
                $chatListLi.removeClass('active-chat');
                $(this).addClass('active-chat');
            }
        })
    }

    // 发送聊天信息
    function sendMsg(websocket, myAvatarUrl) {
        var $chatTextarea = $('.chat-text-input').find('textarea');
        $chatTextarea.keydown(function (e) {
            var toUserId = $('.active-chat').find('input').val(); //聊天对象的id
            var sendChatText = $(this).val();
            if (e.ctrlKey && e.keyCode == 13) {
                var SendData = [{
                    chatText: sendChatText,
                    me: true
                }];
                renderChatHistory(SendData, myAvatarUrl);
                $(this).val(''); //清空这个输入框
            }
            // 发送socket消息
            if (websocket != null) {
                websocket.send(message);
            } else {
                alert('未与服务器链接.');
            }
        })
    }

    //接收聊天消息
    function receiveMsg(data) {
        var data = {
            from: "这个人的ID2",
            to: "xxx",
            content: "xxx",
        };
        var toUserId = $('.active-chat').find('input').val(); //聊天对象的id
        if (data.from == toUserId) { //如果收到的消息是当前聊天对象的就直接在聊天窗口中渲染
            renderChatHistory(data, myAvatarUrl, otherAvatarUrl)
        } else { //否则进行提醒
            $('#' + data.from).parents('.chat-item-info').append('<span class="chat-tips label label-info">新消息</span>');
        }
    }

    function chat() {
        var websocket;
        var myAvatarUrl = $('#myAvatar').attr('src'); //记录自己的头像url
        switchChatObj(myAvatarUrl); //聊天对象选择
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://" + location.host + "/websocket");
        } else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://websocket");
        } else {
            websocket = new SockJS("http://" + location.host + "/sockjs/websocket");
        }
        websocket.onopen = function (evnt) {
            alert('初始化');
        };
        websocket.onmessage = function (evnt) {
            receiveMsg(evnt.data);
        };
        websocket.onerror = function (evnt) {};
        websocket.onclose = function (evnt) {}
        sendMsg(websocket, myAvatarUrl);
    }
    init();
})