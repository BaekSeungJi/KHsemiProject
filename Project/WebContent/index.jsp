<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오늘의 숙소</title>

<link rel="stylesheet" type="text/css" href="css/first_search.css">

</head>
<body>
 

<!-- 로그인여부 확인 / 로그인한 사람의 정보 가져오기. -->
<%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){   // 로그인 정보가 안넘어왔을때. 혹은 기간이 만료했을때(로그인하고 한참 지남)
   %>
   <script type="text/javascript">
   alert("오늘의 숙소에 오신 것을 환영합니다");
   </script>
   <%
}else{
   mem = (MemberDto)ologin;
}
%>


<div class="container">

  <header>
    <h2><a href="#"><i class="ion-plane"></i> 오늘의 숙소</a></h2>
    <nav>
    <% if(mem == null){ %>
					<ul>
						<li>
				          <a class="btn" href="Signup.jsp" title="Register">Register</a>
				        </li>
				        <li>
				          <a class="btn" href="loginview.jsp" title="Login">Login</a>
				        </li>
					</ul>
				<%}else if(mem.getAuth() == 1 || mem.getAuth() == 2){ %>
					<ul>
						<li>
				          <a class="btn" href="start.jsp" title="Management">Management</a>
				        </li>
				        <li>
				          <a class="btn" href="chatBox.jsp" title="Chatting">Chatting</a>
				        </li>
				        <li>
				          <a class="btn" href="logout.jsp" title="Logout">Logout</a>
				        </li>
					</ul>
				<%}else if(mem.getAuth() == 3){ %>
					<ul>
						<li>
				          <a class="btn" href="mypage.jsp" title="My Page">My Page</a>
				        </li>
				        <li>
				          <a class="btn" href="chatBox.jsp" title="Chatting">Chatting</a>
				        </li>
				        <li>
				          <a class="btn" href="logout.jsp" title="Logout">Logout</a>
				        </li>
					</ul>
				<%} %>
    </nav>
  </header>

  <div class="cover">
    <h1>Discover your house of today.</h1>
    <form class="flex-form" action="mainBbs.jsp">
      <label for="from">
        <i class="ion-location"></i>
      </label>
      <input type="search" placeholder="Where do you want to go?" name="keyword">
      <input type="submit" value="Search">
            
    </form>
    <div id="madeby">
      <span>
        Design by <a href="http://www.iei.or.kr/main/main.kh" target="_blank">KH CLASS_3 semi project 6조</a>
      </span>
    </div>
  </div>

</div>


</body>
</html>