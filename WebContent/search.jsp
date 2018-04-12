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
<style>
.btn {
  background: #2d83bd;
  background-image: -webkit-linear-gradient(top, #2d83bd, #2980b9);
  background-image: -moz-linear-gradient(top, #2d83bd, #2980b9);
  background-image: -ms-linear-gradient(top, #2d83bd, #2980b9);
  background-image: -o-linear-gradient(top, #2d83bd, #2980b9);
  background-image: linear-gradient(to bottom, #2d83bd, #2980b9);
  -webkit-border-radius: 55;
  -moz-border-radius: 55;
  border-radius: 55px;
  font-family: Georgia;
  color: #ffffff;
  font-size: 26px;
  padding: 5px 20px 5px 20px;
  text-decoration: none;
}

.btn:hover {
  background: #3cb0fd;
  background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
  background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
  text-decoration: none;
}
</style>
<%  ArrayList<UserBean> users = (ArrayList<UserBean>)request.getAttribute("foundUsers");
if(users.size()!=0){
%>
<h1>Users found:</h1><br><%
for(UserBean u: users){ String username = u.getUsername();
%>
<a href="#"><%=username%></a><br>

<% }
} else {  %> <h1>There are no such user/s.</h1> <button class="btn" onclick="history.back()">Go back!</button> <%} %>

</body>
</html>