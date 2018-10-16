<%@page import="java.util.Calendar"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
<%!
// nvl 함수
public boolean nvl(String msg){
	return msg == null || msg.trim().equals("")?true:false;
}

// 날짜를 클릭하면, 그날의 일정이 모두 보이게 하는 callist.jsp로 이동시키는 함수
public String callist(int year, int month, int day){
	String s = "";
	
	s += String.format("<a href='%s?year=%d&month=%d&day=%d'>", 
						"callist.jsp", year, month, day);
	s += String.format("%2d", day);
	s += "</a>";

	return s;
}

// 일정을 기입하기 위해서 pen이미지를 클릭하면, calwrite.jsp로 이동
public String showPen(int year, int month, int day){
	String s = "";
	String url = "calWrite.jsp";
	String image = "<img src='./image/pen.gif'>";
	
	s = String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>", 
							url, year, month, day, image);
	return s;
}

// 1 -> 01		2018112
public String two(String msg){
	return msg.trim().length() < 2 ? "0"+msg : msg.trim(); 
}

// 각 날짜별로 테이블을 생성하는 함수
public String makeTable(int year, int month, int day, List<ReserveDto> list)
{
	String s = "";
	String dates = (year + "") + two(month + "") + two(day + ""); // 20181001
	
	s += "<table>";
	s += "<col width='98' >";
	int w = 0;

	for(ReserveDto dto : list){
		if(dto.getRealdate().substring(0, 8).equals(dates)){
			
			s += "<tr bgcolor='pink'>";
			s += "<td>";
			s += "<a href='calDetail.jsp?seq=" + dto.getSeq() + "'>";
			s += "<font style='font-size: 4px; color:red'>";
			s += dot3(dto.getId());
			s += "</font>";
			s += "</a>";
			s += "</td>";
			s += "</tr>";			
			
		
	}	
	}
	s += "</table>";
	
	return s;
}

// 제목이 너무 길면, 제목 + ...으로 처리하는 함수		철수와 광화문에서 식사 -> 철수와 광...
public String dot3(String msg){
	String s = "";
	if(msg.length() >= 10){
		s = msg.substring(0, 10);
		s += "...";
	}else{
		s = msg.trim();
	}
	return s;
}
%>    
        
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>
<link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/jvectormap/2.0.4/jquery-jvectormap.min.css'>
 <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>

  <section>
	<header>
		<nav class="rad-navigation">
			<div class="rad-logo-container rad-nav-min">
				<a href="#" class="rad-logo"> Admin</a>
				<a href="#" class="rad-toggle-btn pull-right"><i class="fa fa-bars"></i></a>
			</div>
			<a href="#" class="rad-logo-hidden">Admin</a>

			
		</nav>
	</header>
</section>
<aside>
	<nav class="rad-sidebar rad-nav-min">
		<ul>
			<li>
				<a href="#" class="inbox">
					<i class="fas fa-user-alt"><span class="icon-bg rad-bg-success"></span></i>
					<span class="rad-sidebar-item">회원 관리</span>
				</a>
			</li>
			<li>
				<a href="#">
				<i class="fas fa-hotel">
						<span class="icon-bg rad-bg-danger"></span>
					</i>
					<span class="rad-sidebar-item">호텔 관리</span>
				</a>
			</li>
			<li><a href="#" class="snooz"><i class="fas fa-chart-pie"><span class="icon-bg rad-bg-primary"></span></i><span class="rad-sidebar-item">매출 관리</span></a></li>
			<li><a href="#" class="done"><i class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span class="rad-sidebar-item">공지사항</span></a></li>
			
		</ul>
	</nav>
</aside>


<section>
		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">

	
	
<%
Calendar cal = Calendar.getInstance();
cal.set(Calendar.DATE, 1);

String syear = request.getParameter("year");
String smonth = request.getParameter("month");

int year = cal.get(Calendar.YEAR);
if(nvl(syear) == false){	// 파라미터로 넘어온 데이터가 있을 시
	year = Integer.parseInt(syear);
}

int month = cal.get(Calendar.MONTH) + 1;
if(nvl(smonth) == false){
	month = Integer.parseInt(smonth);
}

if(month < 1){
	month = 12;
	year--;
}

if(month > 12){
	month = 1;
	year++;
}

cal.set(year, month - 1, 1);	// 연월일 셋팅완료

// 요일
int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

// <<
String pp = String.format("<a href='%s?year=%d&month=%d'>"
							+ "<img src='image/left.gif'></a>", 
								"calendar.jsp", year-1, month);

// <
String p = String.format("<a href='%s?year=%d&month=%d'>"
							+ "<img src='image/prec.gif'></a>", 
								"calendar.jsp", year, month-1);

// >
String n = String.format("<a href='%s?year=%d&month=%d'>"
							+ "<img src='image/next.gif'></a>", 
								"calendar.jsp", year, month+1);

// >>
String nn = String.format("<a href='%s?year=%d&month=%d'>"
							+ "<img src='image/last.gif'></a>", 
							"calendar.jsp", year+1, month);

/* MemberDto user = (MemberDto)session.getAttribute("login");

iCalendar dao = CalendarDao.getInstance();
List<CalendarDto> list = dao.getCalendarList(user.getId(), year + two(month + ""));
 */
%>

<div align="center">

<table style="border-color: black;">
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
	<td><%=callist(year, month, i) %>&nbsp;<%=showPen(year, month, i) %>	
		<%-- <%=makeTable(year, month, i, list) %> --%>
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
	
					
		</div>
	</div>


</section>



  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js'></script>
<script src='http://code.jquery.com/ui/1.11.4/jquery-ui.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jQuery-slimScroll/1.3.3/jquery.slimscroll.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.8.0/lodash.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jvectormap/2.0.3/jquery-jvectormap.js'></script>
<script src='http://jvectormap.com/js/jquery-jvectormap-1.2.2.min.js'></script>

  

    <script  src="js/index.js"></script>




</body>

</html>
