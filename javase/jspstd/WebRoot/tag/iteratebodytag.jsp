<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mytag" uri="yahuitag" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <title>My JSP 'iteratetag.jsp' starting page</title>

  </head>
  
  <body>
    <%
    	//此代码只为测试，实际内容应由servlet传递
    	List<String> all = new ArrayList<String>();
    	all.add("吕亚辉1");
    	all.add("吕亚辉2");
    	all.add("吕亚辉3");
    	request.setAttribute("all", all);
     %>
    
     <mytag:bodyiterate name="all" scope="request" id="name">
     	<h4>姓名：${pageScope.name },<%=name.length() %></h4><!-- 这个时候name已经成为变量了，就像Jsp:useBean标签的ID一样 -->
     </mytag:bodyiterate>
    
  </body>
</html>
