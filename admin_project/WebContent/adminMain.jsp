<%@page import="dto.MonthlysalesDto"%>
<%@page import="dto.scoreDto"%>
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

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>


<!-- 차트 -->
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>


</head>

<body>

<%
MemberDto mem = null;
String hotelname = null;
scoreDto sdto = null;
MonthlysalesDto Mdto = null;


mem = (MemberDto)session.getAttribute("login");

hotelname = (String)session.getAttribute("hotelname");

sdto = (scoreDto)request.getAttribute("sdto");

Mdto = (MonthlysalesDto)request.getAttribute("Mdto");
	


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
			
			
				<a href="MemberControl?command=memberGo&id=<%=mem.getId() %>" class="inbox">
					<i class="fas fa-user-alt">
					<span class="icon-bg rad-bg-success"></span>
					</i>
					<span class="rad-sidebar-item">회원 관리</span>
				</a>
			</li>
			
			<li>
				<a href="HotelControl?command=ad_hotel&hotelname=<%=hotelname%>">
				<i class="fas fa-hotel">
						<span class="icon-bg rad-bg-danger"></span>
					</i>
					<span class="rad-sidebar-item">호텔 관리</span>
				</a>
			</li>
			<li><a href="HotelControl?command=ad_chart&hotelname=<%=hotelname%>" class="snooz"><i class="fas fa-chart-pie"><span class="icon-bg rad-bg-primary"></span></i><span class="rad-sidebar-item">매출 관리</span></a></li>
			<li><a href="PdsControl?command=ad_noticeGo" class="done"><i class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span class="rad-sidebar-item">공지사항</span></a></li>
			
		</ul>
	</nav>
</aside>



<main>
	<section>
		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">
			
				<header class="rad-page-title">
					<span> <%=mem.getName() %> 님 환영합니다</span>
					<small class="md-txt"><%=hotelname %> </small>					
				</header>
				

				
				<div class="row">
					<div class="col-lg-3 col-xs-6">
						<div class="rad-info-box rad-txt-success">
							<i class="fas fa-user-alt rad-info-box_i"></i>
							<a href="MemberControl?command=memberGo&id=<%=mem.getId() %>">
								<span class="heading">회원 관리</span>
							
							<span class="value"><span><i class="fas fa-arrow-alt-circle-right"></i></span></span>
						</a>
						</div>
					</div>
					
					
				
					
				<div class="col-lg-3 col-xs-6">
					<div class="rad-info-box rad-txt-danger">
						<i class="fas fa-hotel rad-info-box_i"></i>
						<a href="HotelControl?command=ad_hotel&hotelname=<%=hotelname%>">
							<span class="heading">호텔 관리</span>
							<span class="value"><span><i class="fas fa-arrow-alt-circle-right"></i></span></span>
						</a>
						</div>
					</div>
					<div class="col-lg-3 col-xs-6">
						<div class="rad-info-box rad-txt-primary">
							<i class="fas fa-chart-pie rad-info-box_i"></i>
							<a href="HotelControl?command=ad_chart&hotelname=<%=hotelname%>">
								<span class="heading">매출 관리</span>
								<span class="value"><span><i class="fas fa-arrow-alt-circle-right"></i></span></span>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-xs-6">
						<div class="rad-info-box rad-bg-warning">
							<i class="fas fa-list-ul rad-info-box_i"></i>
							<a href="PdsControl?command=ad_noticeGo">
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
								아이디  </p></td><td><input type="text" value=<%=mem.getId() %> name="id" readonly="readonly"></td>
							</tr>
							
							<tr height="40">
								<td>비밀번호 </p></td><td><input type="password" value=<%=mem.getPwd() %> name="pw"></td>
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
        text: '호텔 이용자 후기 '
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
        name: '개수',
        data: [
            ['5점', <%=sdto.getStar5()%>],
            ['4점', <%=sdto.getStar4()%>],
            ['3점', <%=sdto.getStar3()%>],
            ['2점', <%=sdto.getStar2()%>],
            ['1점', <%=sdto.getStar1()%>]
           
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
            text: ''
        }
    },
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:f} 원</b></td></tr>',
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
        data: [<%=Mdto.getJan()%>, <%=Mdto.getFeb()%>, <%=Mdto.getMar()%>, <%=Mdto.getApr()%>, <%=Mdto.getMay()%>,<%=Mdto.getJul()%>,<%=Mdto.getJul()%>,<%=Mdto.getAug()%>,<%=Mdto.getSep()%>,<%=Mdto.getOct()%>,<%=Mdto.getNov()%>,<%=Mdto.getDec()%>]

    }]
});


</script>


<script type="text/javascript">

$(".fa-chevron-down").on("click", function() {
	var $ele = $(this).parents('.panel-heading');
	$ele.siblings('.panel-footer').toggleClass("rad-collapse");
	$ele.siblings('.panel-body').toggleClass("rad-collapse", function() {
		setTimeout(function() {
			initializeCharts();
		}, 200);
	});
});



</script>
</body>

</html>
