<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主页</title>
</head>
<body>

<%-- <%
	// 从缓存对象中取出 缓存的对象内容
	Object obj = request.getSession().getAttribute("loginedUserName");
	// 输出
	out.write(obj.toString());
%> --%>
${sessionScope.loginedUserName}登录成功 

${pageContext.request.contextPath}
<br>
${pageContext.servletContext.contextPath}
</body>
</html>