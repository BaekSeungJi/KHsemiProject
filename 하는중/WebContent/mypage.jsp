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


<section>
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
          <td><%=dto.getId() %></td> 
        </tr>
        <tr>
          <td>비밀번호</td>
          <td><%=  pw2change(dto.getPwd()) %></td> <!-- 1일시 일반, 2일시 정지회원 -->
        </tr>
        <tr>
          <td>이름</td>
          <td>${dto.name }</td>
        </tr>
        <tr>
          <td>이메일</td>
          <td><%=dto.getEmail() %></td>
        </tr>
        <tr>
          <td>전화번호</td>
          <td><%=dto.getPhone()%></td>
        </tr>
        <tr>
          <td>권한</td>
          <td><%=whour(dto.getAuth()) %></td> <!-- 1일시 일반, 2일시 관리자, 3일시 운영자 -->
        </tr>
       
      </tbody>
    </table>
  </div>
  <a href="MemberControl?command=profileedit&id=<%=id%>">프로필 업데이트</a>
  
  
  <h1>최근 이용한 호텔</h1>
  <br>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>호텔 이름</th>
          <th>지역</th>
          <th>예약 날짜</th>
          <th>이용 시작 시각</th>
          <th>이용 완료 시각</th>	
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <tr><!-- bbslist 참고하기 -->
          <td><a href="index.jsp">호텔 이름</a></td>
          <td>지역</td>
          <td>날짜</td>
          <td>시각</td>
          <td>시각</td>
        </tr>
        
      <!--   강사님 bbslist 보고 수정! -->
       
      </tbody>
    </table>
  </div>
</section>







</body>
</html>