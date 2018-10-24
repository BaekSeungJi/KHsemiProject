<%@page import="model.member.iMemberManager"%>
<%@page import="dto.MemberDto"%>
<%@page import="model.member.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>


    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login view</title>
   <link rel="stylesheet" href="css/login.css">
</head>

<body>

	<div id="login">

	<form action="MemberControl">

			<span class="fontawesome-user"></span>
			<input type="text"  id = "id" name = "id"value="User_ID" onBlur="if(this.value=='')this.value='User_Id'" 
			onFocus="if(this.value=='User_ID')this.value='' "> 
		
			<span class="fontawesome-lock"></span>
			<input type="password" id = "pwd" name = "pwd" value="Password" onBlur="if(this.value=='')this.value='Password'" 
			onFocus="if(this.value=='Password')this.value='' ">
			
			
			<input type="submit" name = "command" id = "Login" value="Login">
			<br>
			</form>
			<br>
		
		
		 <form action="MemberControl">
			<input type="submit" name="command" id = "Signup" value="Signup">
			<br>
		 	</form>
			<a href="MemberControl?command=아이디찾기" style="margin-left: 20px;" name="command" id = "Suchid" value="아이디찾기">아이디찾기</a>
			<a href="MemberControl?command=비밀번호찾기" style="margin-left: 40px;" name="command" id = "Suchpwd" value="비밀번호찾기">비밀번호찾기</a>

	</div> 
<%
System.out.println("로그인뷰 완료");
%>
</body>	
</html>
  
  
</body>
</html>