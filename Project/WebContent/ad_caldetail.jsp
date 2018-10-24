<%@page import="java.sql.Timestamp"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.List"%>
<%@page import="dto.util"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
public String toDates(String mdate){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
	
	// 201810021607	-> 2018-10-02 16:07 
	String s = mdate.substring(0, 4) + "-"	// yyyy 
			+ mdate.substring(4, 6) + "-"	// MM
			+ mdate.substring(6, 8) + " "	// dd
			+ mdate.substring(8, 10) + ":"	// hh
			+ mdate.substring(10, 12) + ":00";
	
	Timestamp d = Timestamp.valueOf(s);
	
	return sdf.format(d);
}

%>
        
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

String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
/* String yyyymmdd = request.getParameter("yyyymmdd"); */

List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("list");

%>


  <section>
	<header>
		<nav class="rad-navigation">
			<div class="rad-logo-container rad-nav-min">
				
			</div>
			<a href="MemberControl?command=ad_admin" class="rad-logo-hidden">Admin</a>
			<a href="index.jsp"><div style="float: right; margin-right: 70px; margin-top: 40px"><i class="fas fa-home"></i>Home</div></a>
			
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

<%-- <%= yyyymmdd%> --%>
<h2><%=year %>년 <%=month %>월 <%=day %>일 일정</h2>

<table border="1" style="text-align: center;">
<col width="80"><col width="150"><col width="150"><col width="400"><col width="100"><col width="50">

<tr bgcolor="#FA8072">
<td>번호</td><td>체크인</td><td>체크아웃</td><td>아이디</td><td>요청사항</td><td>삭제</td>
</tr>

<%
for(int i = 0;i < list.size(); i++ ){
	ReserveDto dto = list.get(i);
	%>
	
	<tr bgcolor="#FFFFFF" >	
		<td><%=i + 1 %></td>
		<td><%=dto.getCheckin()%></td>
		<td><%=dto.getCheckout()%></td>
		<td>
			<%=dto.getId() %>
			
		</td>
		<td>
			<%=dto.getRequest() %>
		</td>
		<td>
			<form action="HotelControl" method="post">
				<input type="hidden" name="command" value="ad_calDelete">
				<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
				<input type="hidden" name="hotelname" value="<%=hotelname %>">
				<input type="submit" value="일정삭제">
			</form>
		</td>	
	</tr>
	<%
}
%>
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

  



</body>

</html>
