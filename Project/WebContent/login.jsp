<%@page import="model.member.iMemberManager"%>
<%@page import="dto.MemberDto"%>
<%@page import="model.member.MemberService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

%>
<% 
MemberService dao = MemberService.getInstance();

MemberDto mem = dao.manager.login(new MemberDto(id, pwd, null, null, null, 0, 3));

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login.jsp</title>
</head>
<body>
<!-- 
DB 접속
bbslist.jsp
 -->
<%
System.out.println("로그인 확인");
if(mem != null && !mem.getId().equals("")){
	session.setAttribute("login", mem);
	session.setMaxInactiveInterval(30*60);

%>
	<script type="text/javascript">
	alert("안녕하세요 <%=mem.getId() %> 님");
	location.href = "loginview.jsp";
	</script>	
<%
}else{
%>
	<script type="text/javascript">
	alert("ID나 Password를 확인하세요");
	location.href = "loginview.jsp";
	</script>
<%
}
%> 
 
 
</body>
</html>



