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
        ignoreMsg('leave-ignore-btn', '/test/ignoreLeaveMsg', false); //忽略留言提醒
        ignoreMsg('dynamic-ignore-btn', '/test/ignoreDynamicMsg', false); //忽略动态提醒
        ignoreMsg('apply-ref-btn', '/test/refApply', false); //拒绝添加好友
        ignoreMsg('apply-pass-btn', '/test/passApply', true); //拒绝添加好友        
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
                msgId: $inputMsgId.val() //要忽略的那条留言消息提醒的ID，数据库直接删除这条消息 
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
    init();
})