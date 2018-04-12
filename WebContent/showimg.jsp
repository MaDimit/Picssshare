<%@page import="model.post.PostBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	ArrayList<PostBean> posts = (ArrayList<PostBean>)request.getAttribute("posts");
	for(int i = 0; i<posts.size(); i++){ %>
	<img src="<%=posts.get(i).getUrl()%>"><br>
		<%} %>
</body>
</html>