package com.yuw.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginControl
 */
/* 注解和servlet的web配置，只能二选一，一个servlet配置不能同时使用注解和web配置 */
@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置request和response的编码格式和jsp页面一致
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		// 转发控制，调用对应的model层进行业务逻辑处理
		System.out.println("进入UserLogincontrl进行处理");
		// 使用request对象获取客户端form表单的标签控件的值
		// 对应关系：要求request方法中的参数值和获取的标签控件的name属性值要一致
		String strUserName = request.getParameter("userName");
		String strUserPsw = request.getParameter("userPsw");
		System.out.println("userName=" + strUserName + ";userPsw=" + strUserPsw);
		// 调用model层进行登录验证
		if (true) {
			// 使用重定向或者转发跳转到登录成功页面
			// 将登陆成功的信息缓存到缓存对象中（一般缓存对象常用的是request和session）
			
			request.getSession().setAttribute("loginedUserName", strUserName);
			// 转发到登录成功页面
			request.getRequestDispatcher("WEB-INF/logined.jsp").forward(request, response);
			// 使用重定向:重定向不能直接访问web项目的WEB-INF目录
			//response.sendRedirect("WEB-INF/logined.jsp");
			/*
			 * web项目的WEB-INF目录是站点安全目录；只能通过转发才能访问其中的资源文件；
			 */
		} else {
			// 重定向到登录页面继续登录，因为登录页面没有在web-info目录下，所有使用重定向和转发都可以访问
			response.sendRedirect("login.jsp");
		}
	}

}
