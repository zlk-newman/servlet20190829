<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录主页</title>
<!-- Bootstrap core CSS -->
    <link href="static/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/plugins/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Bootstrap core JavaScript-->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--import jquery before bootstrap javascript-->
    <script src="static/plugins/jquery-1.12.4/jquery-1.12.4.min.js"></script>
    <script src="static/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <style>
        #maindiv{
            width: 80%;
            padding-top: 10px;
        }
    </style>
</head>
<body>
<div id="maindiv">
	<!-- 使用绝对路径表示action的值， / 是表示web浏览器中的绝对路径的根目录；在浏览器rul中表示从 "http://localhost:8090/" 开始 -->
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/LoginControl" method="post">
        <div class="form-group">
            <label class="col-sm-2 control-label">用户名:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="userName" name="userName" placeholder="登录用户名">
            </div>

            <label class="col-sm-2 control-label">密码:</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="userPsw" name="userPsw" placeholder="密码">
            </div>
        </div>
       
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-2">
                <button type="submit" class="btn btn-default">登录</button>
            </div>
            <div class="col-sm-offset-2 col-sm-2">
                <button type="reset" class="btn btn-default">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>