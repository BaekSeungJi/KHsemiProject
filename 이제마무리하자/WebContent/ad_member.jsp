<%@page import="dto.ReserveTableDto"%>
<%@page import="java.util.List"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='http://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css'>
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/jvectormap/2.0.4/jquery-jvectormap.min.css'>
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean"
	rel="stylesheet">
<link rel="stylesheet" href="css/style1.css">

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

<style type="text/css">
h1 {
	font-size: 30px;
	color: #FA8072;
	text-transform: uppercase;
	font-weight: 300;
	text-align: center;
	margin-bottom: 15px;
}

table {
	width: 100%;
	table-layout: fixed;
}

.tbl-header {
	background-color: #FA8072;
}

.tbl-content {
	height: 300px;
	overflow-x: auto;
	margin-top: 0px;
	border: 1px solid #FA8072;
	background-color: white;
}

th {
	padding: 20px 15px;
	text-align: left;
	font-weight: 500;
	font-size: 12px;
	color: #fff;
	text-transform: uppercase;
}

td {
	padding: 15px;
	text-align: left;
	vertical-align: middle;
	font-weight: 300;
	font-size: 12px;
	color: #FA8072;
	border-bottom: solid 1px #FA8072;
}

/* demo styles */
@import
	url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);

body {
	background: -webkit-linear-gradient(left, #25c481, #2 5b7c4);
	background: linear-gradient(to right, #25c481, #2 5b7c4);
	font-family: 'Roboto', sans-serif;
}

/* follow me template */
.made-with-love {
	margin-top: 40px;
	padding: 10px;
	clear: left;
	text-align: center;
	font-size: 10px;
	font-family: arial;
	color: #fff;
}

.made-with-love i {
	font-style: normal;
	color: #F50057;
	font-size: 14px;
	position: relative;
	top: 2px;
}

.made-with-love a {
	color: #fff;
	text-decoration: none;
}

.made-with-love a:hover {
	text-decoration: underline;
}

.sel {
	font-size: 14px;
}
</style>


</head>














<body>

	<%
// 검색어
String findWord = request.getParameter("sWord");
String choice = request.getParameter("selected");

System.out.println("findWord = " + findWord);

System.out.println("choice = " + choice);

if(choice == null || choice.equals("")){
	choice = "";	
}

if(findWord == null){
	findWord = "";
}

%>

	<%
MemberDto user = (MemberDto)session.getAttribute("login");


List<ReserveTableDto> list = (List<ReserveTableDto>)request.getAttribute("list");

%>



	<section>
		<header>
			<nav class="rad-navigation">
				<div class="rad-logo-container rad-nav-min"></div>
				<a href="MemberControl?command=ad_admin" class="rad-logo-hidden">Admin</a>
				<a href="index.jsp"><div
						style="float: right; margin-right: 70px; margin-top: 40px">
						<i class="fas fa-home"></i>Home
					</div></a>

			</nav>
		</header>
	</section>
	<aside>
		<nav class="rad-sidebar rad-nav-min">
			<ul>
				<li><a
					href="MemberControl?command=memberGo&id=<%=user.getId() %>"
					class="inbox"> <i class="fas fa-user-alt"><span
							class="icon-bg rad-bg-success"></span></i> <span
						class="rad-sidebar-item">회원 관리</span>
				</a></li>
				<li><a href="HotelControl?command=ad_hotel"> <i
						class="fas fa-hotel"> <span class="icon-bg rad-bg-danger"></span>
					</i> <span class="rad-sidebar-item">호텔 관리</span>
				</a></li>
				<li><a href="HotelControl?command=ad_chart" class="snooz"><i
						class="fas fa-chart-pie"><span class="icon-bg rad-bg-primary"></span></i><span
						class="rad-sidebar-item">매출 관리</span></a></li>
				<li><a href="PdsControl?command=ad_noticeGo" class="done"><i
						class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span
						class="rad-sidebar-item">공지사항</span></a></li>

			</ul>
		</nav>
	</aside>






	<section>
		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">


				<h1>회원 정보</h1>



			</div>
			<div class="tbl-header">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th>ID</th>
							<th>이름</th>
							<th>이메일</th>
							<th>전화번호</th>
							<th>체크인 날짜</th>
							<th>체크아웃 날짜</th>
							<th>권한</th>
						</tr>
					</thead>
				</table>
			</div>


			<div class="tbl-content">
				<table cellpadding="0" cellspacing="0" border=" ">
					<tbody>
						<% 
       for(int i= 0; i<list.size();i++){
    	   ReserveTableDto dto = list.get(i);
      %>

						<tr onmouseover="this.style.background='#f0f0f0'"
							onmouseout="this.style.background='white'"
							onclick="location.href='MemberControl?command=ad_member_detail&id=<%=dto.getId() %>'"
							style="cursor: pointer;">

							<td><%=dto.getId() %></td>
							<td><%=dto.getName() %></td>
							<td><%=dto.getEmail() %></td>
							<td><%=dto.getPhone() %></td>
							<td><%=dto.getCheckin() %></td>
							<td><%=dto.getCheckout() %></td>
							<td>
								<%if((dto.getBlacklist())==1){ %> 블랙리스트 회원입니다 <%}else{ %> 회원 <%} %>
							</td>
						</tr>

						<%
       }
       %>
					</tbody>
				</table>



			</div>

			<form action="MemberControl">
				<div align="center" style="margin-top: 20px;">
					<input type="hidden" name="command" value="memberGo"> <input
						type="hidden" name="id" value="<%=user.getId()%>"> <select
						name="sel">
						<option selected="selected">이름</option>
						<option>아이디</option>
					</select> <input type="text" name="txt"> <input type="submit"
						value="검색">

				</div>

			</form>

		</div>


	</section>









	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js'></script>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js'></script>
	<script src='http://code.jquery.com/ui/1.11.4/jquery-ui.js'></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jQuery-slimScroll/1.3.3/jquery.slimscroll.min.js'></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.8.0/lodash.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js'></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/jvectormap/2.0.3/jquery-jvectormap.js'></script>
	<script src='http://jvectormap.com/js/jquery-jvectormap-1.2.2.min.js'></script>




</body>

</html>
