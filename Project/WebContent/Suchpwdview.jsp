<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suchpwdview.jsp</title>
</head>
<body>
<%
System.out.println("suchpwd확인");
%>

			<span class="fontawesome-user"></span>
			<input type="text"  id = "id" name = "id"value="User_ID" onBlur="if(this.value=='')this.value='User_Id'" 
			onFocus="if(this.value=='User_ID')this.value='' "> 

</body>
</html>