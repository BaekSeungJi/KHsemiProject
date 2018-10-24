<%@page import="model.reserve.ReserveService"%>
<%@page import="control.ReserveControl"%>
<%@page import="model.reserve.iReserveManager"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	request.setCharacterEncoding("utf-8");
%>

<%
	MemberDto memdto = (MemberDto) session.getAttribute("login");
	session.setAttribute("login", memdto);
	session.setMaxInactiveInterval(30 * 60);

	Object ologin = session.getAttribute("login");

	List<ReserveDto> list = (List<ReserveDto>) request.getAttribute("list");
%>

<%!public String two(String msg) {
		return msg.trim().length() < 2 ? "0" + msg : msg.trim();
	}

	// Date -> String	18/10/02	
	// String -> Date   yyyy-mm-dd
	public String toDates(String mdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");

		// 201810021607	-> 2018-10-02 16:07 
		String s = mdate.substring(0, 4) + "-" // yyyy 
				+ mdate.substring(4, 6) + "-" // MM
				+ mdate.substring(6, 8) + " " // dd
				+ mdate.substring(8, 10) + ":" // hh
				+ mdate.substring(10, 12) + ":00";

		Timestamp d = Timestamp.valueOf(s);

		return sdf.format(d);
	}%>

<%
	MemberDto user = (MemberDto) session.getAttribute("login");

	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String day = request.getParameter("day");

	String dates = year + two(month + "") + two(day + "");
	Calendar cal = Calendar.getInstance();

	ReserveService reservice = ReserveService.getInstance();

	/*  
	for(int i = 0;i < list.size(); i++){
		ReserveDto dto = list.get(i);
		System.out.println(dto.toString());	
	} */
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reservelist.jsp</title>
</head>
<body>

	<form action="MemberControl">
		<input type="hidden" name="command" value="logout.jsp"> <input
			type="submit" value="로그아웃">
	</form>

	<h2><%=year%>년
		<%=month%>월
		<%=day%>일 일정
	</h2>

	<table border="1">
		<col width="100">
		<col width="200">
		<col width="450">
		<col width="50">

		<tr bgcolor="#09bbaa">
			<td>번호</td>
			<td>시간</td>
			<td>제목</td>
			<td>삭제</td>
		</tr>

		<%
			for (int i = 0; i < list.size(); i++) {
				ReserveDto reservedto = list.get(i);
		%>

		<tr>
			<td><%=i + 1%></td>
			<td><%=toDates(reservedto.getRequest())%></td>
			<td><a href="reservedetail.jsp?seq=<%=reservedto.getSeq()%>">
					<%=reservedto.getHotelname()%>
			</a></td>
			<td>
				<form action="reservedel.jsp" method="post">
					<input type="hidden" name="seq" value="<%=reservedto.getSeq()%>">
					<input type="submit" value="일정삭제">
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>

	<%
		String url = String.format("%s?year=%s&month=%s", "reserve.jsp", year, month);
	%>

	<a href="<%=url%>">일정보기</a>



</body>
</html>








