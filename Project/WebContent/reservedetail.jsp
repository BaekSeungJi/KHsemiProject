<%@page import="java.util.Calendar"%>
<%@page import="dto.ReserveDto"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.List"%>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>caldetail.jsp</title>
</head>
<body>

	<form action="MemberControl">
		<input type="hidden" name="command" value="logout.jsp"> <input
			type="submit" value="로그아웃">
	</form>

	<%!public String toDates(String mdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");

		String s = mdate.substring(0, 4) + "-" // yyyy
				+ mdate.substring(4, 6) + "-" // MM
				+ mdate.substring(6, 8) + " " // dd
				+ mdate.substring(8, 10) + ":" // hh
				+ mdate.substring(10, 12) + ":00";
		Timestamp d = Timestamp.valueOf(s);

		return sdf.format(d);
	}

	public String toOne(String msg) { // 08 -> 8
		return msg.charAt(0) == '0' ? msg.charAt(1) + "" : msg.trim();
	}%>

	<%
		String sseq = request.getParameter("seq");
		int seq = Integer.parseInt(sseq);
	%>




	<h1>일정 보기</h1>
	<hr>

	<div align="center">

		<table border="1">
			<col width="200">
			<col width="500">

			<tr>
				<td>아이디</td>
				<td><%=%></td>
			</tr>

			<tr>
				<td>제목</td>
				<td><%=%></td>
			</tr>

			<tr>
				<td>일정</td>
				<td>
					<%-- <%=  toDates(dto.getRdate()) %> --%>
				</td>
			</tr>

			<tr>
				<td>내용</td>
				<td><textarea rows="20" cols="60" readonly="readonly"><%-- <%=dto.getContent() %> --%>
		</textarea></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="button" value="수정"
					onclick="location.href='reserveupdate.jsp?seq=<%-- <%=dto.getSeq() %> --%>'">
					<input type="button" value="삭제"
					onclick="location.href='reservedel.jsp?seq=<%-- <%=dto.getSeq() %> --%>'">
				</td>
			</tr>

		</table>

	</div>



</body>
</html>



