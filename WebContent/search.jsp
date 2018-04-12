<%@page import="model.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%  ArrayList<UserBean> users = (ArrayList<UserBean>)request.getAttribute("foundUsers");
if(users.size()!=0){
%>
<h1>Users found:</h1><br><%
for(UserBean u: users){ String username = u.getUsername();
%>
<form action="getuserpage" method="get">
<input type="hidden" name="userid" value=<%= u.getId()%>>
<button type="submit"><%=username%></button>
</form><br>

<% }
} else { request.setAttribute("error", "There are no such user/s." );
request.getRequestDispatcher("error.jsp").forward(request, response); %> <%} %>

</body>
</html>