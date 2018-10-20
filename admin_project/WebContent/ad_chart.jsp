<%@page import="dto.scoreDto"%>
<%@page import="dto.MonthlysalesDto"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

  
</head>

<%
MemberDto user= (MemberDto)session.getAttribute("login");

MonthlysalesDto dto = (MonthlysalesDto)request.getAttribute("Mdto");
scoreDto sdto = (scoreDto)request.getAttribute("sdto");
	
%>


<body>

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
					<span class="rad-sidebar-item">ȸ�� ����</span>
				</a>
			</li>
			<li>
				<a href="HotelControl?command=ad_hotel">
				<i class="fas fa-hotel">
						<span class="icon-bg rad-bg-danger"></span>
					</i>
					<span class="rad-sidebar-item">ȣ�� ����</span>
				</a>
			</li>
			<li><a href="HotelControl?command=ad_chart" class="snooz"><i class="fas fa-chart-pie"><span class="icon-bg rad-bg-primary"></span></i><span class="rad-sidebar-item">���� ����</span></a></li>
			<li><a href="PdsControl?command=ad_noticeGo" class="done"><i class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span class="rad-sidebar-item">��������</span></a></li>
			
		</ul>
	</nav>
</aside>
<main>
	<section>
		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">
							
			
			<div class="row">
					<div class="col-md-6 col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">���� ��Ȳ<ul class="rad-panel-action">
																	<li><i class="fa fa-chevron-down"></i></li>
																</ul></h3>
							</div>
							<div class="panel-body">
						
<div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
								
							</div>
						</div>
					</div>
			
				<div class="row">
					<div class="col-xs-12 col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">����<ul class="rad-panel-action">
																	<li><i class="fa fa-chevron-down"></i></li>
														
											
																</ul></h3>
							</div>
							<div class="panel-body">
								<div id="container" style="height: 400px"></div>
							</div>
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
        text: 'ȣ�� �̿��� �ı� '
    },
    subtitle: {
        text: '���纣��ȣ��'
    },
    plotOptions: {
        pie: {
            innerSize: 100,
            depth: 45
        }
    },
    series: [{
        name: '����',
        data: [
            ['5��', <%=sdto.getStar5()%>],
            ['4��', <%=sdto.getStar4()%>],
            ['3��', <%=sdto.getStar3()%>],
            ['2��', <%=sdto.getStar2()%>],
            ['1��', <%=sdto.getStar1()%>]
           
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
        text: '���� ����'
    },
    subtitle: {
        text: '���纣��ȣ��'
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
            '<td style="padding:0"><b>{point.y:f} ��</b></td></tr>',
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
        name: '����',
        data: [<%=dto.getJan()%>, <%=dto.getFeb()%>, <%=dto.getMar()%>, <%=dto.getApr()%>, <%=dto.getMay()%>,<%=dto.getJul()%>,<%=dto.getJul()%>,<%=dto.getAug()%>,<%=dto.getSep()%>,<%=dto.getOct()%>,<%=dto.getNov()%>,<%=dto.getDec()%>]

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
