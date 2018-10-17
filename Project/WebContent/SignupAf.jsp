<%@page import="model.member.MemberService"%>
<%@page import="model.member.MemberManager"%>
<%@page import="model.member.iMemberManager"%>
<%@page import="dto.MemberDto"%>
<%@page import="control.MemberControl" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
%>

     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SignupAf.jsp</title>
</head>
<body>
<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
int blacklist = Integer.parseInt("0");
int auth = Integer.parseInt("3");

System.out.println(id);
/*
System.out.println(pwd);
System.out.println(name);
System.out.println(email);
System.out.println(phone); */
%> 
<%
MemberService service = MemberService.getInstance();

boolean isS = service.manager.addMember(new MemberDto(id, pwd, name, email, phone, 0, 3));

if(isS==true){
%>
	<script type="text/javascript">
	alert("성공적으로 가입하셨습니다");
	location.href = "loginview.jsp";
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert("다시 가입해 주십시오");
	location.href = "Signup.jsp";
	</script>
<%
}
%>




</body>
</html>









