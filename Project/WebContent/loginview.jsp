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
   
    <!--상단고정 Home&logout-->
   <link rel="stylesheet" href="css/style1.css">
    <nav class="rad-navigation">
    <a href="index.jsp">
    <div class="fontawesome-home" style="float: right; margin-right: 70px; margin-top: 40px"><i class="fas fa-home"></i>Home</div></a>
    <%
    
    Object ologin = session.getAttribute("login");
    if(ologin != null){   //로그인이 되어있다면 logout 표시
       %>
       <script type="text/javascript">
       <a herf="logout.jsp">
       <div class="fontawesome-sign-out-alt" style="float: right; margin-right: 70px; margin-top: 40px"><i class="fas fa-logout"></i>Logout</div></a>
       </script>
       <%
     }
    %> 
    
    
   
    
    </nav>
</head>

<body>
<br>
<br>
<br>
<br>

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