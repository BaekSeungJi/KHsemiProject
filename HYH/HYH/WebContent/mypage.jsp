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
 




<section>





		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">



  <h1>예약 내역</h1>

  <div class="tbl-header">
    <table>
      <thead>
        <tr>
          <th>예약 날짜</th>
          <th>호텔 이름</th>
          <th>요구 사항</th>
          <th>등록일</th>
          <th>수정</th>
          <th>취소</th>
        </tr>
      </thead>
    </table>
  </div>
  
   <form action="ReserveControl">
  <div class="tbl-content">
  
    <table cellpadding="0" cellspacing="0" border=" ">
      <tbody>
 	  <% 
       for(int i= 0; i<list.size();i++){
    	   ReserveDto dto = list.get(i);
      %>
      <tr onmouseover="this.style.background='#f0f0f0'"
        	onmouseout="this.style.background='white'">
          <td>
      
          <input type="hidden" name="command" value="ad_reserveUpdate">
          <input type="hidden" name="seq" value="<%=dto.getSeq()%>">
          <input type="hidden" name="id" value="<%=dto.getId()%>">
          
          
          <input type="text" value="<%=dto.getRealdate() %>" name="regdate"></td>
          <td><%=dto.getHotelname() %></td>
          <td><input type="text" value="<%=dto.getRequest() %>" name="request"></td>
          <td><%=dto.getRegdate() %></td>
           
          <td>
           <%if(dto.getDel() == 0){
        	  %><input type="submit" value="수정">
        	   <%  }else{
        	%> 취소된 예약입니다
        	<%
      		 }
          %>
          </td>
          <td>
          <%if(dto.getDel() == 0){
        	  %><input type="button" value="예약취소" onclick="location.href='ReserveControl?command=ad_reserveDelete&seq=<%=dto.getSeq() %>&id=<%=dto.getId()%>'">
        <%  }else{
        	%> 취소된 예약입니다
        	<%
      		 }
          %>

          </td>
          
        </tr>
       <%
       }
       %>
      </tbody>
    </table>
   
  </div>
</form>



</body>
</html>