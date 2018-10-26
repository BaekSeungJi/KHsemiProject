<%@page import="dto.MemberDto"%>

<%@page import="java.util.List"%>
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


<style type="text/css">
table {
	background-color: #ffffff;
}

h1 {
	font-size: 30px;
	color: #FA8072;
	text-transform: uppercase;
	font-weight: 300;
	text-align: center;
	margin-bottom: 15px;
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
	color: black;
	border-bottom: solid 1px #FA8072;
}

.t {
	background-color: #FA8072;
}

.spe {
	text-align: right;
}
}
</style>

</head>

<body>

	<%
		MemberDto mem = (MemberDto) session.getAttribute("login");
	%>



<section>
		<header>
			<nav class="rad-navigation">
				<div class="rad-logo-container rad-nav-min"></div>
				<%
					if (mem.getAuth() == 2) {
				%>
				<a href="MemberControl?command=ad_admin" class="rad-logo-hidden">Admin</a>
				<a href="index.jsp"><div
						style="float: right; margin-right: 70px; margin-top: 40px">
						<i class="fas fa-home"></i>Home
					</div></a>

				<%
					} else {
				%>
				<a href="#" class="rad-logo-hidden">Admin</a> <a href="index.jsp"><div
						style="float: right; margin-right: 70px; margin-top: 40px">
						<i class="fas fa-home"></i>Home
					</div></a>

				<%
					}
				%>
			</nav>
		</header>
	</section>
	<aside>
		<nav class="rad-sidebar rad-nav-min">
			<ul>
				<li>
					<%
						if (mem.getAuth() == 2) {
					%> <a
					href="MemberControl?command=memberGo&id=<%=mem.getId()%>"
					class="inbox"> <i class="fas fa-user-alt"><span
							class="icon-bg rad-bg-success"></span></i> <span
						class="rad-sidebar-item">회원 관리</span>
				</a> <%
 	} else {
 %> <a href="MemberControl?command=ad_memberDeleteGo"
					class="inbox"> <i class="fas fa-user-alt"><span
							class="icon-bg rad-bg-success"></span></i> <span
						class="rad-sidebar-item">회원 관리</span>
				</a> <%
 	}
 %>

				</li>
				<li>
					<%
						if (mem.getAuth() == 2) {
					%> <a href="HotelControl?command=ad_hotel">
						<i class="fas fa-hotel"> <span class="icon-bg rad-bg-danger"></span>
					</i> <span class="rad-sidebar-item">호텔 관리</span>
				</a> <%
 	} else {
 %> <a href="PdsControl?command=ad_noticeGo" class="done"><i
						class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span
						class="rad-sidebar-item">공지사항</span></a> <%
 	}
 %>
				</li>
				<%
					if (mem.getAuth() == 2) {
				%>
				<li><a href="HotelControl?command=ad_chart" class="snooz"><i
						class="fas fa-chart-pie"><span class="icon-bg rad-bg-primary"></span></i><span
						class="rad-sidebar-item">매출 관리</span></a> <%
 	}
 %></li>

				<%
					if (mem.getAuth() == 2) {
				%>
				<li><a href="PdsControl?command=ad_noticeGo" class="done"><i
						class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span
						class="rad-sidebar-item">공지사항</span></a> <%
 	}
 %></li>

			</ul>
		</nav>
	</aside>


	<section>
		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">


				<h2>자료 올리기</h2>

				<form action="fileupload.jsp" method="post"
					enctype="multipart/form-data">

					<div align="center" style="margin-bottom: 100px">

						<table border="1" bgcolor="pink">
							<col width="70">
							<col width="500">

							<tr>
								<td class="t">아이디</td>
								<td><%=mem.getId()%> <input type="hidden" name="id"
									value="<%=mem.getId()%>"></td>
							</tr>

							<tr>
								<td class="t">제목</td>
								<td><input type="text" name="title" size="50"></td>
							</tr>

							<tr>
								<td class="t">파일업로드</td>
								<td><input type="file" name="fileload" style="width: 400px">
								</td>
							</tr>

							<tr>
								<td class="t">내용</td>
								<td><textarea rows="20" cols="50" name="content"></textarea>
								</td>
							</tr>

							<tr align="center" class="spe">
								<td colspan="2"><input type="submit" value="올리기"></td>
							</tr>

						</table>

					</div>

				</form>




			</div>
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
