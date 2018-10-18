<%@page import="model.member.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SuchidAf.jsp</title>
</head>
<body>
<%
String email = request.getParameter("email");
MemberService service = MemberService.getInstance();

service = service.manager.getId(id);

%>





</body>
</html>