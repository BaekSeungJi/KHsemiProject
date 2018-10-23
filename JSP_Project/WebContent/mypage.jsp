<%@page import="dto.MemberDto"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("utf-8");
%>  

    
<%

MemberDto mem = (MemberDto)request.getAttribute("dto");
session.setAttribute("login", mem);
session.setMaxInactiveInterval(30*60);

List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("reserveList");

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


/* List<ReserveDto> reservelist = dao.getBbsList();
 */
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
	<input type="hidden" name="command" value="logout.jsp">
	<input type="submit" value="로그아웃"> 
</form>

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
          <td><%=mem.getId() %></td> 
        </tr>
        <tr>
          <td>비밀번호</td>
          <td><%=  pw2change(mem.getPwd()) %></td> <!-- 1일시 일반, 2일시 정지회원 -->
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
          <td><%=whour(mem.getAuth()) %></td> <!-- 1일시 일반, 2일시 관리자, 3일시 운영자 -->
        </tr>
       
      </tbody>
    </table>
  </div>
  <a href="MemberControl?command=profileedit&id=<%=id%>">프로필 업데이트</a>
 




 <h1>예약 내역</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>번호</th>
          <th>호텔이름</th>
          <th>체크인</th>	
          <th>체크아웃</th>	
          <th>등록일</th>
          <th>리뷰쓰기</th>	
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
       <%for(int i =0;i<list.size();i++){ %>
        <%ReserveDto dto = list.get(i); %>
        <tr>
        <td><%=i+1 %></td>
        <td><%=dto.getHotelname() %></td>
        <td><%=dto.getCheckin() %></td>
        <td><%=dto.getCheckout() %></td>
        
        <td><%=dto.getRegdate().substring(0, 10) %></td>
        <%if(dto.getDel()==1){ %>
        <td>취소된 예약입니다</td>
        <%}else{ %>
        <td><input type="button" value="리뷰쓰기" onclick="location.href='ReviewControl?command=writeReviewGo&seq=<%=dto.getSeq() %>&hotelname=<%=dto.getHotelname() %>&checkin=<%=dto.getCheckin() %>&checkout=<%=dto.getCheckout()%>'"></td>
    	<%} %>
        </tr>
       <%} %>
      </tbody>
    </table>
  </div>


</section>


</body>
</html>