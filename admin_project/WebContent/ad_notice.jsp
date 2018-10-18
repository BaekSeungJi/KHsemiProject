<%@page import="dto.PdsfileDto"%>
<%@page import="java.util.List"%>
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

  
</head>

<body>
<%
List<PdsfileDto> list = (List<PdsfileDto>)request.getAttribute("list");
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


<h2>자료실</h2>

<div align="center">

<table border="1">
<col width="50"><col width="100"><col width="400"><col width="50">
<col width="50"><col width="50"><col width="100">

<tr bgcolor="#09bbaa" align="center">
	<td>번호</td><td>작성자</td><td>제목</td><td>다운로드</td>
	<td>조회수</td><td>다운수</td><td>작성일</td>
</tr>

<%
for(int i = 0;i < list.size(); i++){
	PdsfileDto pds = list.get(i);
	String bgcolor = "";
	if(i % 2 == 0){
		bgcolor = "#ddeebb";
	}else{
		bgcolor = "#ddddcc";
	}
	%>
	<tr  bgcolor="<%=bgcolor %> align="center" height="5">
		<td><%=i + 1 %></td>
		<td><%=pds.getId() %></td>
		<td align="left">
			<a href="pdsdetail.jsp?seq=1">
					<%=pds.getTitle() %>
			</a>
		</td>
		<td>
			<input type="button" name="btnDown" value="파일"
				onclick="location.href='filedown?filename=파일이름&seq=2'">
		</td>
		<td><%=pds.getReadcount() %></td>
		<td><%=pds.getDowncount() %></td>
		<td><%=pds.getRegdate() %></td>	
	</tr>
	<%
}
%>
</table>
</div>

<a href="ad_notice_write.jsp">자료 올리기</a>
<br><br>

<a href="bbslist.jsp">Home</a>


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
