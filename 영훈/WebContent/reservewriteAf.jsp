<%@page import="dto.ReserveDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.List"%>
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

<%!
public String two(String msg){
	return msg.trim().length()<2?"0"+msg:msg.trim();
}
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");

String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
String hour = request.getParameter("hour");
String min = request.getParameter("min");

// 201802140923

String rdate = year + two(month) + two(day) + two(hour) + two(min);

iCalendar dao = CalendarDao.getInstance();
boolean isS = dao.addCalendar(new CalendarDto(id, title, content, rdate));

String url = String.format("%s?year=%s&month=%s", 
						"calendar.jsp", year, month); 

if(isS){
	%>
	<script type="text/javascript">
	alert("성공적으로 일정을 추가하였습니다");
	location.href="<%=url %>";	
	</script>
	<%
}else{	
%>
	<script type="text/javascript">
	alert("일정이 추가되지 않았습니다");
	location.href="<%=url %>";	
	</script>
<%
}
%>
	

</body>
</html>












