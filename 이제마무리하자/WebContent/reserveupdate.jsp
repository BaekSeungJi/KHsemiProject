	

<%@page import="dto.MemberDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="dto.util"%>
<%@page import="java.util.List"%>
<%@page import="dto.ReserveDto"%>
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

List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("list");

/* 
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
 */

System.out.println("memdto.getid :" + memdto.getId());

 
 System.out.println("reserveupdate.jsp로 들어왔음");
 
 
 
String hotelname = request.getParameter("hotelname"); 
System.out.println("hotelname = " +hotelname);

String command = request.getParameter("command");
System.out.println("command = " +command);

int seq = Integer.parseInt(request.getParameter("seq"));
System.out.println("seq = " +seq);

String id = request.getParameter("id");
System.out.println("id = " +id);

String request1 = request.getParameter("request");
System.out.println("request1 = " +request1);

String checkin = request.getParameter("checkin");
System.out.println("checkin = " +checkin);

String checkout = request.getParameter("checkout");
System.out.println("checkout = " +checkout);


Calendar cal = Calendar.getInstance();

int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1; 
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR_OF_DAY);
int tmin = cal.get(Calendar.MINUTE);



String syear1 = checkin.substring(0,4);
int year1 = Integer.parseInt(checkin.substring(0,4));
System.out.println("year1 = " +year1);

String smonth1 = checkin.substring(4,6);
int month1 = Integer.parseInt(checkin.substring(4,6));
System.out.println("month1 = " +month1);

String sday1 = checkin.substring(6,8);
int day1 = Integer.parseInt(checkin.substring(6,8));
System.out.println("day1 = " +day1);


String syear2 = checkout.substring(0,4);
int year2 = Integer.parseInt(checkout.substring(0,4));
System.out.println("year2 = " +year2);

String smonth2 = checkout.substring(4,6);
int month2 = Integer.parseInt(checkout.substring(4,6));
System.out.println("month2 = " +month2);

String sday2 = checkout.substring(6,8);
int day2 = Integer.parseInt(checkout.substring(6,8));
System.out.println("day2 = " +day2);


%>     



    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reserveupdate</title>
</head>
<body>


<h1>예약 수정</h1>
<hr>

<div align="center">

<form action="reserveupdateaf.jsp" method="post">
<input type="hidden" name="seq" value="<%=seq %>">
<table border="1">
<col width="200"><col width="500">

<tr>
	<td>아이디</td>
	<td>
		<%=id%>
		<input type="hidden" name="id" value="<%=id %>">
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
				<option <%=syear1.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>년
		
		<select name="month1">
		<%
			for(int i = 1; i <= 12; i++){
				%>
				<option <%=smonth1.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>월
		
		<select name="day1">
		<%
			for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
				%>
				<option <%=sday1.equals(i + "")?"selected='selected'":"" %>
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
				<option <%=syear1.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>년
		
		<select name="month2">
		<%
			for(int i = 1; i <= 12; i++){
				%>
				<option <%=smonth1.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>월
		
		<select name="day2">
		<%
			for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
				%>
				<option <%=sday1.equals(i + "")?"selected='selected'":"" %>
					value="<%=i %>"><%=i %></option>
				<%
			}		
		%>		
		</select>일
	
</tr>

<tr>
	<td>요청사항</td>
	<td>
		<textarea rows="20" cols="60" name="request"></textarea>
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





