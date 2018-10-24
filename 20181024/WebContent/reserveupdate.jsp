<%@page import="java.util.Calendar"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.List"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
request.setCharacterEncoding("utf-8");
%>   

<%
MemberDto memdto = (MemberDto)session.getAttribute("login");

List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("list");

/* List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("reserveList");
 */

 
MemberDto user= (MemberDto)session.getAttribute("login");

String id = memdto.getId();
String hotelname = request.getParameter("hotelname");

%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reservewrite.jsp</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 

</head>
<body>

<form action="MemberControl">
	<input type="hidden" name="command" value="logout.jsp">
	<input type="submit" value="로그아웃"> 
</form>

<h3>일정 쓰기</h3>

<%



String syear = request.getParameter("year");
String smonth = request.getParameter("month");
String sday = request.getParameter("day");


String shotelname = request.getParameter("hotelname");

Calendar cal = Calendar.getInstance();

int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1; 
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR_OF_DAY);
int tmin = cal.get(Calendar.MINUTE);



System.out.println("memdto.getid :" + memdto.getId());


%>

<div align="center">

<form action="reserveupdateaf.jsp" method="post">

<table border="1">
<col width="200"><col width="500">

<tr>
	<td>아이디</td>
	<td>
		<%=memdto.getId()%>
		<input type="hidden" name="id" value="<%=memdto.getId() %>">
	</td>
</tr> 

<tr>
	<td>호텔이름</td>
	<td>
		<input type="text" size="60" name="hotelname" value="<%=hotelname%>" readonly="readonly">
	</td>
</tr>

<tr>
	<td>일정</td>
	<td>
		<select name="year1">
		<%
			for(int i = tyear - 5; i < tyear + 6; i++){
				%>
				<option <%=syear.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>년
		
		<select name="month1">
		<%
			for(int i = 1; i <= 12; i++){
				%>
				<option <%=smonth.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>월
		
		<select name="day1">
		<%
			for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
				%>
				<option <%=sday.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>일
		
		<input type="text" value="~" size="4" readonly="readonly">
		
		<select name="year2">
		<%
			for(int i = tyear - 5; i < tyear + 6; i++){
				%>
				<option <%=syear.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>년
		
		<select name="month2">
		<%
			for(int i = 1; i <= 12; i++){
				%>
				<option <%=smonth.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>월
		
		<select name="day2">
		<%
			for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
				%>
				<option <%=sday.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>일
	
</tr>

<tr>
	<td>요청사항</td>
	<td>
		<textarea rows="20" cols="60" name="request1"></textarea>
	</td>
</tr>

<tr>
	<td colspan="2">
		<input type="submit" value="예약하기">
	</td>
</tr>

</table>

</form>

</div>


</body>
</html>





