$(function () {
    // 初始化
    function init() {
        $('[data-toggle="tooltip"]').tooltip({
            container: 'body'
        });
        switchRepitem()
        addFriend();
        repLeave()
    };
    // 添加好友
    function addFriend() {
        var $addFriendBtn = $('.addFriend');
        $addFriendBtn.click(function () {
            var $uid = $addFriendBtn.prev().val();
            var $that = $(this);
            $.post('/test/addfriend', {
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
    // 回复
    function repLeave() {

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