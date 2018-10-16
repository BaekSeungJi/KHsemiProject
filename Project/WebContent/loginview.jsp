<%@page import="model.member.iMemberManager"%>
<%@page import="dto.MemberDto"%>
<%@page import="model.member.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

    
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

		<form action="MemberControl?command=login" >

			<span class="fontawesome-user"></span><input type="text" required value="Username" onBlur="if(this.value=='')this.value='Username'" 
			onFocus="if(this.value=='Username')this.value='' "> <!-- JS because of IE support; better: placeholder="Username" -->
		
			<span class="fontawesome-lock"></span><input type="password" required value="Password" onBlur="if(this.value=='')this.value='Password'" 
			onFocus="if(this.value=='Password')this.value='' "> <!-- JS because of IE support; better: placeholder="Password" -->
			
			
			<input type="submit" value="Login">
			<br>
			</form>
			<br>
		
		
			<form action="MemberControl?command=signup" >
			<input type="submit" value="회원가입">
			<br>
			</form>
	

	</div> <!-- end login -->

</body>	
</html>
  
  
</body>
</html>