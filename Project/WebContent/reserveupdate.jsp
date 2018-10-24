<%@page import="model.reserve.ReserveService"%>
<%@page import="control.ReserveControl"%>
<%@page import="model.reserve.iReserveManager"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    

<%
request.setCharacterEncoding("utf-8");
%>   

<%
MemberDto memdto = (MemberDto)session.getAttribute("login");
session.setAttribute("login", memdto);
session.setMaxInactiveInterval(30*60);

Object ologin = session.getAttribute("login");

List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("list");

%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>calupdate</title>

</head>
<body>

<form action="MemberControl">
	<input type="hidden" name="command" value="logout.jsp">
	<input type="submit" value="로그아웃"> 
</form>

<%!
public String toDates(String mdate){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
	
	String s = mdate.substring(0, 4) + "-" 	// yyyy
			+ mdate.substring(4, 6) + "-"	// MM
			+ mdate.substring(6, 8) + " " 	// dd
			+ mdate.substring(8, 10) + ":"	// hh
			+ mdate.substring(10, 12) + ":00"; 
	Timestamp d = Timestamp.valueOf(s);
	
	return sdf.format(d);	
}

public String toOne(String msg){	// 08 -> 8
	return msg.charAt(0)=='0'?msg.charAt(1) + "": msg.trim();
}

%>

<%
Calendar cal = Calendar.getInstance();
int tyear = cal.get(Calendar.YEAR);

String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq);

ReserveService reservice = ReserveService.getInstance();

/* CalendarDto dto = dao.getDay(seq); */
ReserveDto reservedto = reservice.getDay(seq);

String year = reservedto.getRegdate().substring(0, 4);
String month = toOne(reservedto.getRegdate().substring(4, 6));
String day = toOne(reservedto.getRegdate().substring(6, 8));
String hour = toOne(reservedto.getRegdate().substring(8, 10));
String min = toOne(reservedto.getRegdate().substring(10, 12));

%>


<h1>일정 수정</h1>
<hr>

<div align="center">


<form action="reserveupdateAf.jsp" method="post">

<table border="1">
<col width="200"><col width="500">

<tr>
	<td>아이디<input type="hidden" name="seq" value="<%=reservedto.getSeq() %>">
	</td>
	<td>
		<input type="text" name="id" value="<%=reservedto.getId() %>" readonly="readonly">
	</td>
</tr> 

<tr>
	<td>제목</td>
	<td>
		<input type="text" size="60" name="title" value="<%=reservedto.getHotelname() %>">
	</td>
</tr>

<tr>
	<td>일정</td>
	<td>
	
	<select name="year">
	<%
	for(int i = tyear - 5;i < tyear + 6; i++){
		%>
		<option <%=year.equals(i + "")?"selected='selected'":"" %> 
			value="<%=i %>"><%=i %></option>		
		<%	
	}	
	%>	
	</select>년	
	
	<select name="month">
	<%
	for(int i = 1;i <= 12; i++){
		%>
		<option <%=month.equals(i + "")?"selected='selected'":"" %> 
			value="<%=i %>"><%=i %></option>		
		<%	
	}	
	%>	
	</select>월
	
	<select name="day">
	<%
	for(int i = 1;i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
		%>
		<option <%=day.equals(i + "")?"selected='selected'":"" %> 
			value="<%=i %>"><%=i %></option>		
		<%	
	}	
	%>	
	</select>일
	
	<select name="hour">
	<%
	for(int i = 0;i < 24; i++){
		%>
		<option <%=(hour + "").equals(i + "")?"selected='selected'":"" %> 
			value="<%=i %>"><%=i %></option>		
		<%	
	}	
	%>	
	</select>시
	
	<select name="min">
	<%
	for(int i = 0;i < 60; i++){
		%>
		<option <%=(min + "").equals(i + "")?"selected='selected'":"" %> 
			value="<%=i %>"><%=i %></option>		
		<%	
	}	
	%>	
	</select>분
	
	</td>
</tr>

<tr>
	<td>내용</td>
	<td>
		<textarea rows="20" cols="60" name="content"><%=reservedto.getRequest()%> </textarea>
	</td>
</tr>

<tr>
	<td colspan="2">
		<input type="button" value="수정" onclick="modify()">
	</td>
</tr>


</table>

</form>

</div>

<script type="text/javascript">
function modify() {
	var f = document.forms[0];
	f.submit();
}

</script>

<%
String url = String.format("%s?year=%s&month=%s", 
						"reserve.jsp", year, month);
%>

<a href="<%=url %>">일정보기</a>

</body>
</html>





