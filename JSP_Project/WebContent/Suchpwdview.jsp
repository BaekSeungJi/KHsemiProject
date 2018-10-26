<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suchpwdview.jsp</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

<link rel="stylesheet" href="./css/Signup.css">


</head>
<body>
	<%
System.out.println("suchpwd확인");
%>

	<fieldset>
		<h1>ID와 PHONE번호를 입력해주세요</h1>

		<form action="MemberControl">

			<label for="user_id">ID:</label> <input type="text" id="id" name="id"
				required value="User_ID"
				onBlur="if(this.value=='')this.value='User_ID'"
				onFocus="if(this.value=='User_ID')this.value='' "> <label
				for="phone">Phone:</label> <input type="text" id="phone"
				name="phone" value="Phone"
				onBlur="if(this.value=='')this.value='Phone'"
				onFocus="if(this.value=='Phone')this.value='' ">

			<button type="submit" name="command" id="비밀번호확인" value="비밀번호확인">"비밀번호확인"</button>
			</from>
</body>
</html>