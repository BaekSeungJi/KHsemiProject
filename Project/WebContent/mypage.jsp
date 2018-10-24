<%@page import="dto.MemberDto"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
%>


<%
// 세션에 login정보 저장은 찬수씨가 맡은 로그인 부분임. 영훈씨가 또 세션에 login을 저장하면 당연히 오류남.
Object ologio = session.getAttribute("login");
MemberDto mem = null;
if(ologio != null){   //로그인이 되어있다면 logout 표시 --> 할필요 없음. 왜냐면 로그인을 안하면 아예 메인에서 mypage라는 버튼이 뜨지 않기 때문임.
   mem = (MemberDto)ologio;
}

// 로그인 시, script를 통해 로그아웃 버튼을 a태그로 감싸서 만들어주려고 하셨으나 해당코드 전체 삭제함.
// 왜냐면 여긴 위치 자체가 body 속이 아님. 여기서 script를 만들어서 append한것도 아니고 그냥 <a href=어쩌구>라고만 써놓으시면 body영역이 아니기때문에 아무것도 적용이 안됨.
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

	<form action="logout.jsp">
		<input type="submit" value="로그아웃">
		<!-- 영훈씨 원래 코드로는 지금 이 form부분 action에 멤버 컨트롤러로 가도록 하셨는데 왜 그렇게 만드셨는지 솔직히 이해를 못하겠음. -->
		<!-- 로그아웃은 그냥 logout.jsp로만 가도록 설정해달라고 분명 제가 logout.jsp만들면서 카톡으로 전부 말씀드렸는데 그냥 카톡을 안읽으신거같음 -->
		<!-- 게다가 영훈씨 원래 코드대로라면 서블릿으로 가서 command로 logout을 찾아가도록 하셨는데 심지어 해당 컨트롤러엔 command logout부분도 안만들어져있음. -->
		<!-- 따라서 지금 로그아웃버튼 누르면 그냥 하얀화면만 뜨는 버그가 있는데 왜 아직까지 안잡고 계셨는지 의문. -->
		<!-- 그냥 제가 고쳐놨으니 다른 부분에서도 이와같은 식의 버그가 있다면 전부 고쳐주길바람. -->
	</form>


	<!--for demo wrap-->
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
					<td><%=mem.getId() %></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><%=  pw2change(mem.getPwd()) %></td>
					<!-- 1일시 일반, 2일시 정지회원 -->
				</tr>
				<tr>
					<td>이름</td>
					<td><%=mem.getName()%></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><%=mem.getEmail() %></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><%=mem.getPhone()%></td>
				</tr>
				<tr>
					<td>권한</td>
					<td><%=whour(mem.getAuth()) %></td>
					<!-- 1일시 일반, 2일시 관리자, 3일시 운영자 -->
				</tr>

			</tbody>
		</table>
	</div>
	<a href="MemberControl?command=profileedit&id=<%=mem.getId()%>">프로필
		업데이트</a>


</body>
</html>