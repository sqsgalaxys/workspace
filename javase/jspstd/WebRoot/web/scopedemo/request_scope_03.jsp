<%@ page language="java" contentType="text/html;charset=UTF-8"%><!--配置指令-->
<%@ page import="java.util.*"%>
<html>
	<head>
		<title>
			lvyahui
		</title>
	</head>
	<body>
		<center>
			<% 
				request.setAttribute("name","吕亚辉");
				request.setAttribute("birthday",new Date());
			%>
			<a href="request_scope_02.jsp">通过超链接获取</a><!--获取不到本页面设置的请求属性范围的属性，因为这是属于客户端跳转，客户端相当于发送了第二次请求，故第一次请求已经失效了-->
		</center>
	</body>
</html>