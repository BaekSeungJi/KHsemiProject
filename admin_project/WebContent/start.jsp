<%@page import="dto.MemberDto"%>
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
// 일단 임의로 로그인
String id = "yuriv";
String pwd = "1234";
String name = "김유리";
String email = "yuriv@naver.com";
String phone = "010-8728-2222";
int blacklist = 0;
int auth=1;

MemberDto dto = new MemberDto(id,pwd,name,email,phone,blacklist,auth);
session.setAttribute("login", dto);
%>

<a href="MemberControl?command=ad_admin">관리자 모드 이동</a>

</body>
</html>