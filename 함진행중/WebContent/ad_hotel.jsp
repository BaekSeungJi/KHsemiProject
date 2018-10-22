<%@page import="dto.MemberDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.List"%>
<%@page import="dto.util"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
 <!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
 
 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>
<link rel='stylesheet' href='http://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/jvectormap/2.0.4/jquery-jvectormap.min.css'>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">
<link rel="stylesheet" href="css/style1.css">

<style type="text/css">
._tablee{
background-color: #FFFAFA;
  display:inline-block;


}

._tr{
background-color: #FA8072;
}
tr,td{
border: 1px solid #F5F5F5	;

}


</style>
  
</head>

<body>
<%
MemberDto user= (MemberDto)session.getAttribute("login");


String hotelname = (String)session.getAttribute("hotelname");

List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("list"); 

%>


  <section>
	<header>
		<nav class="rad-navigation">
			<div class="rad-logo-container rad-nav-min">
				
			</div>
			<a href="MemberControl?command=ad_admin" class="rad-logo-hidden">Admin</a>

			
		</nav>
	</header>
</section>
<aside>
	<nav class="rad-sidebar rad-nav-min">
		<ul>
			<li>
				<a href="MemberControl?command=memberGo&id=<%=user.getId() %>" class="inbox">
					<i class="fas fa-user-alt"><span class="icon-bg rad-bg-success"></span></i>
					<span class="rad-sidebar-item">회원 관리</span>
				</a>
			</li>
			<li>
				<a href="HotelControl?command=ad_hotel">
				<i class="fas fa-hotel">
						<span class="icon-bg rad-bg-danger"></span>
					</i>
					<span class="rad-sidebar-item">호텔 관리</span>
				</a>
			</li>
			<li><a href="HotelControl?command=ad_chart" class="snooz"><i class="fas fa-chart-pie"><span class="icon-bg rad-bg-primary"></span></i><span class="rad-sidebar-item">매출 관리</span></a></li>
			<li><a href="PdsControl?command=ad_noticeGo" class="done"><i class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span class="rad-sidebar-item">공지사항</span></a></li>
			
		</ul>
	</nav>
</aside>


<section>
		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">

		<header class="rad-page-title">
					<span><%=hotelname %> </span>
					<small><a href="HotelControl?command=ad_GohotelUpdate&hotelname=<%=hotelname%>"> 정보 수정하기</a></small>
								
				</header>

<div align="center">

<table class="_tablee">
<col width="100"><col width="100"><col width="100"><col width="100">
<col width="100"><col width="100"><col width="100">

<%



int dayOfWeek = (int)request.getAttribute("dayOfWeek");
int year = (int)request.getAttribute("year");
int month = (int)request.getAttribute("month");


Calendar cal = (Calendar)request.getAttribute("cal");
			
			// <<
			String pp = String.format("<a href='%s?command=%s&year=%d&month=%d&hotelname=%s'>"
										+ "<img src='image/left.gif'></a>", 
											"HotelControl","ad_hotel", year-1, month,hotelname);

			// <
			String p = String.format("<a href='%s?command=%s&year=%d&month=%d&hotelname=%s'>"
										+ "<img src='image/prec.gif'></a>", 
											"HotelControl","ad_hotel", year, month-1,hotelname,"ad_hotel");

			// >
			String n = String.format("<a href='%s?command=%s&year=%d&month=%d&hotelname=%s'>"
										+ "<img src='image/next.gif'></a>", 
											"HotelControl","ad_hotel", year, month+1,hotelname,"ad_hotel");

			// >>
			String nn = String.format("<a href='%s?command=%s&year=%d&month=%d&hotelname=%s'>"
										+ "<img src='image/last.gif'></a>", 
										"HotelControl","ad_hotel", year+1, month,hotelname,"ad_hotel");


%>



<tr height="100" class="_tr">
	<td colspan="7" align="center">
		<%=pp %>&nbsp;<%=p %>
		<font color="black" style="font-size: 50px">
			<%=String.format("%d년&nbsp;&nbsp;%d월", year, month) %>
		</font>
		<%=n %>&nbsp;<%=nn %>
	</td>
</tr>

<tr height="40" bgcolor="#F5F1E9">
	<td align="center" style="padding-top: 7px; color: red;">일</td>
	<td align="center">월</td>
	<td align="center">화</td>
	<td align="center">수</td>
	<td align="center">목</td>
	<td align="center">금</td>
	<td align="center" style="color: red;">토</td>
</tr>

<tr height="100" align="left" valign="top"  class="_t">
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
	<td><%= util.callist(year, month, i,hotelname) %>
	<%=util.makeTable(year, month, i, list) %>
	</td>
	<%
	if((i + dayOfWeek - 1) % 7 == 0 && i != lastDay){
		%>
		</tr><tr height="100" align="left" valign="top"  class="_t">
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
	
<div>

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

  



</body>

</html>
