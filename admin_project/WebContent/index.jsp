<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script> -->
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<!-- <script src='http://code.jquery.com/ui/1.11.4/jquery-ui.js'></script> -->

<script src='http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jQuery-slimScroll/1.3.3/jquery.slimscroll.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.8.0/lodash.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jvectormap/2.0.3/jquery-jvectormap.js'></script>
<script src='http://jvectormap.com/js/jquery-jvectormap-1.2.2.min.js'></script>


<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>


</head>

<body>

<%

MemberDto mem = (MemberDto)request.getAttribute("dto");

session.setAttribute("login", mem);
session.setMaxInactiveInterval(30*60);


String hotelname = (String)request.getAttribute("hotelname");
System.out.println(hotelname);


%>
  <section>
	<header>

		<nav class="rad-navigation">
			<div class="rad-logo-container rad-nav-min">
			
			
			</div>
			<a href="index.jsp" class="rad-logo-hidden">Admin</a>

			
		</nav>
	</header>
</section>
<aside>
	<nav class="rad-sidebar rad-nav-min">
		<ul>
			<li>
			
			
				<a href="MemberControl?command=memberGo&id=<%=mem.getId() %>" class="inbox">
					<i class="fas fa-user-alt"><span class="icon-bg rad-bg-success"></span></i>
					<span class="rad-sidebar-item">회원 관리</span>
				</a>
			</li>
			
			<li>
				<a href="ad_hotel.jsp">
				<i class="fas fa-hotel">
						<span class="icon-bg rad-bg-danger"></span>
					</i>
					<span class="rad-sidebar-item">호텔 관리</span>
				</a>
			</li>
			<li><a href="ad_chart.jsp" class="snooz"><i class="fas fa-chart-pie"><span class="icon-bg rad-bg-primary"></span></i><span class="rad-sidebar-item">매출 관리</span></a></li>
			<li><a href="ad_notice.jsp" class="done"><i class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span class="rad-sidebar-item">공지사항</span></a></li>
			
		</ul>
	</nav>
</aside>



<main>
	<section>
		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">
			
				<header class="rad-page-title">
					<span><%=mem.getName() %> 님 환영합니다</span>
					<small class="md-txt"><%=hotelname %> <a href="https://www.google.com/maps/place/3720+Emerald+St,+Torrance,+CA+90503/@33.8403836,-118.3543828,17z/data=!4m18!1m15!4m14!1m6!1m2!1s0x80c2b4d407f58b11:0xdedca55964c89054!2s3720+Emerald+St,+Torrance,+CA+90503!2m2!1d-118.3520761!2d33.8403792!1m6!1m2!1s0x80c2b4d407f58b11:0xdedca55964c89054!2s3720+Emerald+St,+Torrance,+CA+90503!2m2!1d-118.3520761!2d33.8403792!3m1!1s0x80c2b4d407f58b11:0xdedca55964c89054" target="_blank"><i class="fa fa-map-marker color-main"></i> 서울</a></small>					
				</header>
				

				
				<div class="row">
					<div class="col-lg-3 col-xs-6">
						<div class="rad-info-box rad-txt-success">
							<i class="fas fa-user-alt rad-info-box_i"></i>
							<a href="MemberControl?command=memberGo&id=<%=mem.getId() %>">
								<span class="heading">회원 관리</span>
							</a>
							<span class="value"><span><i class="fas fa-arrow-alt-circle-right"></i></span></span>
						
						</div>
					</div>
					
					
				
					
				<div class="col-lg-3 col-xs-6">
					<div class="rad-info-box rad-txt-danger">
						<i class="fas fa-hotel rad-info-box_i"></i>
						<a href="ad_hotel.jsp">
							<span class="heading">호텔 관리</span>
							<span class="value"><span><i class="fas fa-arrow-alt-circle-right"></i></span></span>
						</a>
						</div>
					</div>
					<div class="col-lg-3 col-xs-6">
						<div class="rad-info-box rad-txt-primary">
							<i class="fas fa-chart-pie rad-info-box_i"></i>
							<a href="ad_chart.jsp">
								<span class="heading">매출 관리</span>
								<span class="value"><span><i class="fas fa-arrow-alt-circle-right"></i></span></span>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-xs-6">
						<div class="rad-info-box rad-bg-warning">
							<i class="fas fa-list-ul rad-info-box_i"></i>
							<a href="ad_notice.jsp">
								<span class="heading">공지사항</span>
								<span class="value"><span><i class="fas fa-arrow-alt-circle-right"></i></span></span>
							</a>
						</div>
					</div>
				</div>
				<div class="row">
				
				</div>
				
				<div class="col-xs-12 col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">나의 정보<ul class="rad-panel-action">
																	<li><i class="fa fa-chevron-down"></i></li>
															
																</ul></h3>
							</div>
							<div class="panel-body">
							
							<div>
							<div style="float: left; padding-right: 40px"><img alt="" src="72294141bf017fd8c2098303afdb7878.jpg" width="230" height="250"></div>
							<div style="float: left;">
							<form action="MemberControl">
							<table>
							<col width="80">
								<tr height="40">
								<td>
								<input type="hidden" value="ad_memberUpdate" name="command">
								아이디  </p></td><td><input type="text" value=<%=mem.getId() %> name="id"></td>
							</tr>
							
							<tr height="40">
								<td>비밀번호 </p></td><td><input type="text" value=<%=mem.getPwd() %> name="pw"></td>
							</tr>
							
								<tr height="40">
								<td>이름  </p></td><td><input type="text" value=<%=mem.getName() %> name="name"></td>
							</tr>
							
							<tr height="40" >
								<td>이메일  </td><td><input type="text" value=<%=mem.getEmail() %> name="email"></td>
							</tr>
							
							<tr height="120">
								<td>전화번호  </td><td><input type="text" value=<%=mem.getPhone() %> name="phone"></td>
							</tr>
							
							<tr height="10">
							<td></td>	<td align="right">
							<input type="submit" value="수정">
							</td>
							</tr>
							
							
							</table>
							</form>
							
							
							</div>
							</div>
						
							
							</div>

						</div>
					</div>
				
				
				<div class="row">
					<div class="col-xs-12 col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Area Chart<ul class="rad-panel-action">
																	<li><i class="fa fa-chevron-down"></i></li>
														
											
																</ul></h3>
							</div>
							<div class="panel-body">
								<div id="container" style="height: 400px"></div>
							</div>
						</div>
					</div>
					
					<div class="row">
					<div class="col-md-6 col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">매출 현황<ul class="rad-panel-action">
																	<li><i class="fa fa-chevron-down"></i></li>
																	<li><i class="fa fa-rotate-right"></i></li>
																	<li><i class="fa fa-cog"></i>
																	<li><i class="fa fa-close"></i>
																	</li>
																</ul></h3>
							</div>
							<div class="panel-body">
						
<div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
								
							</div>
						</div>
					</div>
				
					
				</div>
			</div>
		</div>
	</section>
</main>


<script type="text/javascript">

Highcharts.chart('container', {
    chart: {
        type: 'pie',
        options3d: {
            enabled: true,
            alpha: 45
        }
    },
    title: {
        text: '호텔 이용자 후기 평점'
    },
    subtitle: {
        text: '히든베이호텔'
    },
    plotOptions: {
        pie: {
            innerSize: 100,
            depth: 45
        }
    },
    series: [{
        name: 'Delivered amount',
        data: [
            ['Bananas', 8],
            ['Kiwi', 3],
            ['Mixed nuts', 1],
            ['Oranges', 6],
            ['Apples', 8],
            ['Pears', 4],
            ['Clementines', 4],
            ['Reddish (bag)', 1],
            ['Grapes (bunch)', 1]
        ]
    }]
});

</script>


 
<script type="text/javascript">

Highcharts.chart('container2', {
    chart: {
        type: 'column'
    },
    title: {
        text: '월별 매출'
    },
    subtitle: {
        text: '히든베이호텔'
    },
    xAxis: {
        categories: [
            'Jan',
            'Feb',
            'Mar',
            'Apr',
            'May',
            'Jun',
            'Jul',
            'Aug',
            'Sep',
            'Oct',
            'Nov',
            'Dec'
        ],
        crosshair: true
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Rainfall (mm)'
        }
    },
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },
    series: [{
        name: '매출',
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

    }, {
        name: '후기글',
        data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]

    }, {
        name: 'London',
        data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]

    }, {
        name: 'Berlin',
        data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]

    }]
});


</script>

</body>

</html>
