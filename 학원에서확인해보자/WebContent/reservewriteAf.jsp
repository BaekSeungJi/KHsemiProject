<%@page import="model.reserve.iReserveManager"%>
<%@page import="model.reserve.ReserveManager"%>
<%@page import="model.reserve.ReserveService"%>
<%@page import="control.ReserveControl"%>
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

<form action="MemberControl">
	<input type="hidden" name="command" value="logout.jsp">
	<input type="submit" value="로그아웃"> 
</form>

<%
String id = request.getParameter("id");
String hotelname = request.getParameter("hotelname");
String request1 = request.getParameter("request1");
System.out.println("호텔이름체크:"+hotelname);
String year1 = request.getParameter("year1");
String month1 = request.getParameter("month1");
String day1 = request.getParameter("day1");

String year2 = request.getParameter("year2");
String month2 = request.getParameter("month2");
String day2 = request.getParameter("day2");


String checkin = year1 + two(month1) + two(day1);
String checkout = year2 + two(month2) + two(day2);

System.out.println("checkin :" + checkin);
System.out.println("checkout :" + checkout);

ReserveService resservice = ReserveService.getInstance();

boolean isS = resservice.reserve(id, hotelname, request1, checkin, checkout);

/* String url = String.format("%s?year1=%s&month1=%s", 
						"reserve.jsp", year1, month1); 
 */
if(isS){
	%>
	<script type="text/javascript">
	alert("성공적으로 일정을 추가하였습니다");
	location.href="index.jsp";	
	</script>
	<%
}else{	
%>
	<script type="text/javascript">
	alert("일정이 추가되지 않았습니다");
	location.href="index.jsp";	
	</script>
<%
}
%>
	

</body>
</html>












