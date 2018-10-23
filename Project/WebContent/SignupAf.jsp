<%@page import="java.util.List"%>
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
MemberDto dto = (MemberDto)request.getAttribute("dto");

String id = dto.getId();
String pwd = dto.getPwd();
String name = dto.getName();
String email = dto.getEmail();
String phone = dto.getPhone();
int blacklist = dto.getBlacklist();
int auth = dto.getAuth();

%> 
<%
MemberService service = MemberService.getInstance();

boolean isS = service.manager.addMember(new MemberDto(id, pwd, name, email, phone, blacklist, auth));

if (isS == false){
	%>
	<script type="text/javascript">
	alert("다시 가입해 주십시오");
	location.href = "Signup.jsp";
	</script>
<%
}
else if(isS==true && id !="User_ID" && pwd!="Password" && name!="Name" && email!="E-mail" && phone!="Phone"){
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









