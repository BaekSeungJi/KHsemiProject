<%@page import="model.reserve.ReserveManager"%>
<%@page import="model.reserve.ReserveService"%>
<%@page import="control.ReserveControl"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="dto.MemberDto"%>
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
<title>reserveupdateAf.jsp</title>
</head>
<body>

	<form action="MemberControl">
		<input type="hidden" name="command" value="logout.jsp"> <input
			type="submit" value="로그아웃">
	</form>

	<%!
public String two(String msg){
	return msg.trim().length()<2?"0"+msg:msg.trim();	// 1 ~ 9 -> 01
}
%>

	<%
int seq = Integer.parseInt(request.getParameter("seq"));

String title = request.getParameter("title");
String content = request.getParameter("content");

String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
String hour = request.getParameter("hour");
String min = request.getParameter("min");

String rdate = year + two(month) + two(day) + two(hour) + two(min);

String regdate = year + two(month) + two(day) + two(hour) + two(min);

String checkin = request.getParameter("checkin");
String checkout = request.getParameter("checkout");
String requestt = request.getParameter("request");

ReserveService resservice = ReserveService.getInstance();

boolean isS = resservice.reserveUpdate(seq, checkin, checkout, requestt);


// String urlDetail = String.format("%s?seq=%d", "caldetail.jsp", seq);
String urllist = String.format("%s?year=%s&month=%s", "reserve.jsp", year, month);

if(isS){	
	%>
	<script type="text/javascript">
	alert("성공적으로 일정을 수정했습니다");
	location.href="<%=urllist %>";	
	</script>
	<%
}else{	
	%>
	<script type="text/javascript">
	alert("일정을 수정하지 못했습니다");
	location.href="<%=urllist %>";	
	</script>
	<%
}
%>


</body>
</html>





