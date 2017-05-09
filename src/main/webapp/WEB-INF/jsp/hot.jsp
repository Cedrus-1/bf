
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
    <script src="../../static/js/jquery-2.1.4.min.js"></script>
    <script src="../../static/js/bootstrap.min.js"></script>
    <script src="../../static/js/layer.js"></script>
    <script src="../../static/js/main.js"></script>

</head>

<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-5">
            <form action="/publishDynamic" method="post" class="dynamic-form form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <textarea class="form-control" rows="6" id="dynamicText" name="dynamicText" placeholder="动态内容"></textarea>
                </div>
                <div class="form-group">
                    <input type="file" name="dynamicImg" id="dynamicImg" class="form-control" placeholder="选择图片">
                </div>
                <div class="form-group">
                    <button type="submit" class="center-block btn btn-primary">发布动态</button>
                </div>
            </form>
        </div>
        <div class="col-md-7">
            <h3 class="dynamicH3">动态</h3>
            <!--动态列表-->
            <div class="dynamicItem">
                <div class="dynamicBody">
                    <img class="dynamicImg img-responsive img-rounded" src="../../static/img/large.gif" alt="imgPath">
                    <div class="dynamicBodyContent">波太带岳母大人今天在青岛休假，今天早上在餐厅吃饭时一位自称龙泉寺（没错就是北京那个）的清华物理系出身的高僧要给她们指点迷津。波太大喜，赶紧请教了我以前给她刨的大坑“玻色子跟中微子的关系”，只见大师目光一闪，便如概率波一样闪现消失了。[捂脸]</div>
                </div>
                <div class="dynamicFooter clearfix">
                    <div class="dynamicItem-info pull-left">
                        <span class="glyphicon glyphicon-user"></span><a href="#">tom</a>
                    </div>
                    <div class="dynamicItem-info pull-left">
                        <span class="glyphicon glyphicon-time"></span><span>2017-02-02</span>
                    </div>
                </div>
            </div>
            <div class="dynamicItem">
                <div class="dynamicBody">
                    <img class="dynamicImg img-responsive img-rounded" src="../../static/img/large.gif" alt="imgPath">
                    <div class="dynamicBodyContent">波太带岳母大人今天在青岛休假，今天早上在餐厅吃饭时一位自称龙泉寺（没错就是北京那个）的清华物理系出身的高僧要给她们指点迷津。波太大喜，赶紧请教了我以前给她刨的大坑“玻色子跟中微子的关系”，只见大师目光一闪，便如概率波一样闪现消失了。[捂脸]</div>
                </div>
                <div class="dynamicFooter clearfix">
                    <div class="dynamicItem-info pull-left">
                        <span class="glyphicon glyphicon-user"></span><a href="#">tom</a>
                    </div>
                    <div class="dynamicItem-info pull-left">
                        <span class="glyphicon glyphicon-time"></span><span>2017-02-02</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>