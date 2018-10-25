<%@page import="dto.MemberDto"%>
<%@page import="dto.HotelDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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


</head>

<body>

	<%
		MemberDto user = (MemberDto) session.getAttribute("login");

		HotelDto dto = (HotelDto) request.getAttribute("dto");
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
					href="MemberControl?command=memberGo&id=<%=user.getId()%>"
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

	<main>
	<section>
		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">

				<div class="col-xs-12 col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								호텔 정보
								<ul class="rad-panel-action">
									<li><i class="fa fa-chevron-down"></i></li>

								</ul>
							</h3>
						</div>
						<div class="panel-body">

							<div>
								<div style="float: left; padding-right: 20px">
									<img alt="" src="hotelImage/<%=dto.getImage()%>" width="230"
										height="300">
								</div>
								<div style="float: left;">
									<form action="HotelControl">
										<table>
											<col width="80">
											<tr height="40">
												<td><input type="hidden" value="ad_hotelUpdate"
													name="command"> 호텔이름</td>
												<td><input type="text" value="<%=dto.getHotelname()%>"
													readonly="readonly" name="hotelname"></td>
											</tr>


											<tr height="40">
												<td>최대인원</td>
												<td><input type="text" value="<%=dto.getMaxpeople()%>"
													name="maxpeople"></td>
											</tr>

											<tr height="40">
												<td>가격</td>
												<td><input type="text" value="<%=dto.getPrice()%>"
													name="price"></td>
											</tr>

											<tr height="40">
												<td>전화번호</td>
												<td><input type="text"
													value="<%=dto.getHotelphone()%>" name="hotelphone"></td>
											</tr>

											<tr height="40">
												<td>설명</td>
												<td></td>
											</tr>

											<tr height="10">
												<td colspan="2"><textarea rows="7" cols=30
														" name="description"><%=dto.getDescription()%></textarea></td>
											</tr>
											<tr height="10">
												<td></td>
												<td align="right"><input type="submit" value="수정">
												</td>
											</tr>


										</table>
									</form>


								</div>
							</div>


						</div>

					</div>
				</div>


			</div>
		</div>
	</section>
	</main>

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
