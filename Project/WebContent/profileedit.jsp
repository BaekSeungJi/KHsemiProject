<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
%>


<%

MemberDto dto = (MemberDto)request.getAttribute("dto");
Object ologin = session.getAttribute("login");
String id = request.getParameter("id");

session.setAttribute("login", dto);
session.setMaxInactiveInterval(30*60);

%>


<%!
//비밀번호 2자리 이후로는 * 처리
public String pw2change(String pwd){
	String s = "**";
	
	if(pwd.length() > 2){
		s += pwd.substring(2, pwd.length());
	}
		

	return s;
}

%>

<%!
//일반유저/관리자/운영자
public String whour(int Auth){
	String s = "";
	
	if(Auth == 1){
  	  s = "일반유저";
    }
    else if(Auth == 2){
  	  s = "관리자";
    }
    else if(Auth == 3){
  	  s = "운영자";
    }		

	return s;
}


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">


<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="js/main.js">


<title>mypage</title>


</head>
<body>

	<form action="MemberControl">
		<input type="hidden" name="command" value="profileeditaf">
		<section> <!--for demo wrap-->
		<h1>나의 프로필</h1>
		<div class="tbl-header">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th>구분</th>
						<th>나</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tbl-content">
			<table cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr>
						<td>ID</td>
						<td><input type="text" style="background-color: #FA8072"
							id="id" name="id" readonly="readonly" value="<%= id%>"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="text" id="pwd" name="pwd"
							value="<%= pw2change(dto.getPwd())%>"></td>
						<!-- 1일시 일반, 2일시 정지회원 -->
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" id="name" name="name"
							value="<%=dto.getName()%>"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" id="email" name="email"
							value="<%=dto.getEmail()%>"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" id="phone" name="phone"
							value="<%=dto.getPhone()%>"></td>
					</tr>
					<tr>
						<td>권한</td>
						<td><input type="text" id="auth"
							style="background-color: #FA8072" name="auth" readonly="readonly"
							value="<%=whour(dto.getAuth()) %>"></td>
					</tr>

				</tbody>
			</table>
		</div>
	</form>
	<input type="submit" id="edit" value="수정하기">

	<a href="MemberControl?command=mypage&id=<%=id%>">돌아가기</a>






</body>
</html>