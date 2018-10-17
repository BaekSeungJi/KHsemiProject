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
<!-- <body>

  <html>
<head>
	
	<title>Login_03</title>

	[if lt IE 9]>
		<script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]

</head> -->

<body>

	<div id="login">

	<form action="MemberControl">

			<span class="fontawesome-user"></span>
			<input type="text"  value="Username" onBlur="if(this.value=='')this.value='Username'" 
			onFocus="if(this.value=='Username')this.value='' "> 
		
			<span class="fontawesome-lock"></span>
			<input type="password"  value="Password" onBlur="if(this.value=='')this.value='Password'" 
			onFocus="if(this.value=='Password')this.value='' ">
			
			
			<input type="submit" name = "command" value="Login">
			<br>
			</form>
			<br>
		
		
		 <form action="MemberControl">
			<input type="submit" name="command" id = "Signup" value="Signup">
			<br>
		 	</form>

	</div> 

</body>	
</html>
  
  
</body>
</html>