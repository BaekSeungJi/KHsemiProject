<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("utf-8");
%>    
    
<%
/* 
String code = request.getParameter("code");
String name = request.getParameter("name");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
String black = request.getParameter("black");
String auth = request.getParameter("auth");
 */

MemberDto dto = (MemberDto)request.getAttribute("dto");
 
 
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">


<link rel="stylesheet" type="text/css" href="css/main.css"> 
<link rel="stylesheet" type="text/css" href="js/main.js">


<title>profileedit</title>
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
          <td>회원번호</td>
          <td><%=dto.getId() %></td> 
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
          <td>블랙리스트</td>
          <td><%=dto.getBlacklist() %></td> <!-- 1일시 일반, 2일시 정지회원 -->
        </tr>
        <tr>
          <td>권한</td>
          <td><%=dto.getAuth() %></td> <!-- 1일시 일반, 2일시 관리자, 3일시 운영자 -->
        </tr>
       
      </tbody>
    </table>
  </div>
  
  <a href="profileedit.jsp">profileedit.jsp</a>	
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