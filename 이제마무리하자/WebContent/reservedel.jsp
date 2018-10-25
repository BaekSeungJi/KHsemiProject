<%@page import="model.reserve.ReserveService"%>
<%@page import="model.reserve.ReserveManager"%>
<%@page import="model.reserve.iReserveManager"%>
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

String hotelname = (String)request.getAttribute("hotelname");


%>     

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reservedel.jsp</title>
</head>
<body>

<form action="MemberControl">
	<input type="hidden" name="command" value="logout.jsp">
	<input type="submit" value="로그아웃"> 
</form>

<%!
public String toOne(String msg){	// 08 -> 8
	return msg.charAt(0)=='0'?msg.charAt(1) + "": msg.trim();
}

public String two(String msg){		// 8 -> 08
	return msg.trim().length()<2?"0"+msg:msg.trim();	// 1 ~ 9 -> 01
}
%>

<%
int seq = Integer.parseInt(request.getParameter("seq"));

ReserveService reservice = ReserveService.getInstance();

ReserveDto reservedto = reservice.getDay(seq);

boolean isS = reservice.reservedelete(seq);


if(isS){
	%>
	<script type="text/javascript">
	alert("성공적으로 삭제 되었습니다");
	location.href="index.jsp";
	</script>	
	<%
}else{	
	%>
	<script type="text/javascript">
	alert("삭제하지 못했습니다");
	location.href="index.jsp";
	</script>
	<%
}	
%>


</body>
</html>




