<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>

<link rel="stylesheet" type="text/css" href="./css/style.css">

<style type="text/css">
body {
	background: url(./image/back.jpg) no-repeat center top;
}
</style> 

</head>
<body>

<h1>index.jsp</h1>

<form action="MemberControl">
	<input type="hidden" name="command" value="logout.jsp">
	<input type="submit" value="로그아웃"> 
</form>


<br>
<%
//임의로 로그인
Object ologin = session.getAttribute("login");
MemberDto mem = null;
mem = (MemberDto)ologin;

String id = mem.getId();

%>

<a href="MemberControl?command=mypage&id=<%=id%>">마이페이지</a>
<br>

<a href="ReserveControl?command=reserve&id=<%=id%>">호텔예약</a>
 

 
</body>
</html>