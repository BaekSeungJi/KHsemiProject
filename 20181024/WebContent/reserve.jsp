<%@page import="model.reserve.ReserveManager"%>
<%@page import="control.ReserveControl"%>
<%@page import="model.reserve.ReserveService"%>
<%@page import="model.reserve.iReserveManager"%>
<%@page import="dto.ReserveDto"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="dto.util"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
request.setCharacterEncoding("utf-8");
%>  

<%
MemberDto memdto = (MemberDto)session.getAttribute("login");

List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("list");
String hotelname = (String)request.getAttribute("hotelname");

/* List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("reserveList");
 */

String id = request.getParameter("id");
%>    
    
 <%!
 
	public static String callist(int year, int month, int day,String hotelname){
		String s = "";
		
		s += String.format("<a href='%s?command=%s&hotelname=%s&year=%d&month=%d&day=%d'>", 
							"ReserveControl","reservedetail",hotelname, year, month, day);
		s += String.format("%2d", day);
		s += "</a>";

		return s;
	}
 
//일정을 기입하기 위해서 pen이미지를 클릭하면, calwrite.jsp로 이동
	public static String showPen(int year, int month, int day,String hotelname){
		String s = "";
		String url = "reservewrite.jsp";
		String image = "<img src='./image/pen.gif'>";
		
		s = String.format("<a href='%s?year=%d&month=%d&day=%d&hotelname=%s'>%s</a>", 
								url, year, month, day, hotelname,image);
		return s;
	}

	// 1 -> 01		2018112
	public static String two(String msg){
		return msg.trim().length() < 2 ? "0"+msg : msg.trim(); 
	}
	// 제목이 너무 길면, 제목 + ...으로 처리하는 함수		철수와 광화문에서 식사 -> 철수와 광...
	public static String dot3(String msg){
		String s = "";
		if(msg.length() >= 10){
			s = msg.substring(0, 10);
			s += "...";
		}else{
			s = msg.trim();
		}
		return s;
	}
	// 각 날짜별로 테이블을 생성하는 함수
	public static String makeTable(int year, int month, int day, List<ReserveDto> list)
	{
		String s = "";
		String dates = (year + "") + two(month + "") + two(day + ""); // 20181001
		
		s += "<table>";
		s += "<col width='98' >";
		int w = 0;

		for(ReserveDto dto : list){
			if(dto.getCheckin().substring(0, 8).equals(dates) || dto.getCheckout().substring(0, 8).equals(dates)){
				
				s += "<tr bgcolor='pink'>";
				s += "<td>";
		
				s += "<font style='font-size: 13px; color:red'>";
				s += dot3(dto.getId());
				s += "</font>";
	
				s += "</td>";
				s += "</tr>";			
				
			
		}	
		}
		s += "</table>";
		
		return s;
	}
 %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reserve.jsp</title>
</head>
<body>

<form action="MemberControl">
	<input type="hidden" name="command" value="logout.jsp">
	<input type="submit" value="로그아웃"> 
</form>

<h2>예약</h2>

<%

int dayOfWeek = (int)request.getAttribute("dayOfWeek");
int year = (int)request.getAttribute("year");
int month = (int)request.getAttribute("month");

Calendar cal = (Calendar)request.getAttribute("cal");
// <<
String pp = String.format("<a href='%s?command=%s&year=%d&month=%d&hotelname=%s'>"
							+ "<img src='image/left.gif'></a>", 
								"ReserveControl","reserve", year-1, month,hotelname);

// <
String p = String.format("<a href='%s?command=%s&year=%d&month=%d&hotelname=%s'>"
							+ "<img src='image/prec.gif'></a>", 
							"ReserveControl","reserve", year, month-1,hotelname);

// >
String n = String.format("<a href='%s?command=%s&year=%d&month=%d&hotelname=%s'>"
							+ "<img src='image/next.gif'></a>", 
							"ReserveControl","reserve", year, month+1,hotelname);

// >>
String nn = String.format("<a href='%s?command=%s&year=%d&month=%d&hotelname=%s'>"
							+ "<img src='image/last.gif'></a>", 
							"ReserveControl","reserve", year+1, month,hotelname);

 
%>



<div align="center">

<table border="1">
<col width="100"><col width="100"><col width="100"><col width="100">
<col width="100"><col width="100"><col width="100">

<tr height="100">
	<td colspan="7" align="center">
		<%=pp %>&nbsp;<%=p %>
		<font color="black" style="font-size: 50px">
			<%=String.format("%d년&nbsp;&nbsp;%d월", year, month) %>
		</font>
		<%=n %>&nbsp;<%=nn %>
	</td>
</tr>

<tr height="100">
	<td align="center">일</td>
	<td align="center">월</td>
	<td align="center">화</td>
	<td align="center">수</td>
	<td align="center">목</td>
	<td align="center">금</td>
	<td align="center">토</td>
</tr>

<tr height="100" align="left" valign="top">
<%
//위쪽 빈칸
for(int i = 1;i < dayOfWeek; i++){
	%>
	<td>&nbsp;</td>	
	<%
}



// 날짜
int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
for(int i = 1;i <= lastDay; i++){
	%>
	<td><%=callist(year, month, i, hotelname) %>&nbsp;<%=showPen(year, month, i,hotelname) %>	
		<%=makeTable(year, month, i, list)%>
	</td>
	<%
	if((i + dayOfWeek - 1) % 7 == 0 && i != lastDay){
		%>
		</tr><tr height="100" align="left" valign="top">
		<%
	}
}

// 밑칸
for(int i = 0;i < (7 - (dayOfWeek + lastDay - 1) % 7 ) % 7; i++){
	%>
	<td>&nbsp;</td>	
	<%
}


%>
</tr>



</table>

</div>


</body>
</html>





