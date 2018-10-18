<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suchidview.jsp</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

<link rel="stylesheet" href="./css/Signup.css">


</head>
<body>
<%
System.out.println("suchid");
%>
		<fieldset>
			<h1>
				E-mail정보를 입력 해 주세요
			</h1>
			<form action="ControlMember">
			
				<label
				for="mail">Email:</label> <input type="email" id="email"
				name="email" required  value="E-mail"
				onBlur="if(this.value=='')this.value='E-mail'"
				onFocus="if(this.value=='E-mail')this.value='' "> 
				
				<button type="submit" name = "command" id = "Login" value="IDSUCH">아이디 확인</button>
				
			<!-- 	<input type="submit" name = "command" id = "Login" value="IDSUCH"> -->

			</form>
</body>
</html>