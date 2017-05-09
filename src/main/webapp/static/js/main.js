$(function () {
    // 初始化
    function init() {
        $('[data-toggle="tooltip"]').tooltip({
            container: 'body'
        });
        addFriend();
        repLeave()
        deleteLeaveMsg()
        switchRepitem()
    };
    // 添加好友
    function addFriend() {
        var $addFriendBtn = $('.addFriend');
        $addFriendBtn.click(function () {
            var $uid = $addFriendBtn.prev().val();
            var $that = $(this);
            $.post('/test/addfriend', { //TODO URL还没有填写真实的
                uid: $uid
            }, function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.code) {
                    layer.open({
                        title: '添加好友',
                        content: '添加好友成功'
                    });
                    $that.text('已添加好友').removeClass('btn-primary').addClass('btn-success disabled').unbind("click");
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
            $.post('/test/deleteLeaveMsg', {
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
            var $repUid = $Thisfooter.find('input').val();
            var $repText = $Thisfooter.find('textarea').val();
            $.post('/test/repLeave', {
                uid: $repUid, //回复的留言的uid
                repText: $repText //回复内容
            }, function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData) {
                    // 不处理，你直接刷新
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
    init();
})