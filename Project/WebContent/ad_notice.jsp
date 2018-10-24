<%@page import="dto.MemberDto"%>
<%@page import="dto.PdsDto"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
// 댓글용
 public String arrow (int depth){
	String rs = "<img src ='image/arrow.png' width='20px' height='20px'/>";
	String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
	String ts = "";
	
	for(int i = 0; i< depth; i++){
		ts += nbsp;
	}
	return depth == 0 ? "":ts + rs;
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
      <link rel="stylesheet" href="css/style1.css">
      
<style type="text/css">
._tr{
border: 1px solid #C0C0C0	;
text-align: center;


}
</style>

  
<style type="text/css">



h1{
  font-size: 30px;
  color: #FA8072;
  text-transform: uppercase;
  font-weight: 300;
  text-align: center;
  margin-bottom: 15px;
}
table{
	margin-bottom: 60px;
  width:100%;
  table-layout: fixed;
}
.tbl-header{
  background-color: #FA8072;
 }
.tbl-content{
  height:300px;
  overflow-x:auto;
  margin-top: 0px;
  border: 1px solid #FA8072;
    background-color: white;
}
th{
  padding: 20px 15px;
  text-align: left;
  font-weight: 500;
  font-size: 12px;
  color: #fff;
  text-transform: uppercase;
}
td{
  padding: 15px;
  text-align: left;
  vertical-align:middle;
  font-weight: 300;
  font-size: 12px;
  color: #FA8072;
  border-bottom: solid 1px #FA8072;

}


/* demo styles */

@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
body{
  background: -webkit-linear-gradient(left, #25c481, #2 5b7c4);
  background: linear-gradient(to right, #25c481, #2 5b7c4);
  font-family: 'Roboto', sans-serif;
}


}

</style>
  
</head>

<body>

<%
MemberDto mem = (MemberDto)session.getAttribute("login");
List<PdsDto> list = (List<PdsDto>)request.getAttribute("list");
%>  


  <section>
	<header>
		<nav class="rad-navigation">
			<div class="rad-logo-container rad-nav-min">
				
			</div>
			<%if(mem.getAuth()==2){ %>
			<a href="MemberControl?command=ad_admin" class="rad-logo-hidden">Admin</a>
				<a href="index.jsp"><div style="float: right; margin-right: 70px; margin-top: 40px"><i class="fas fa-home"></i>Home</div></a>
			
			<%}else{ %>
			<a href="#" class="rad-logo-hidden">Admin</a>
				<a href="index.jsp"><div style="float: right; margin-right: 70px; margin-top: 40px"><i class="fas fa-home"></i>Home</div></a>
			
			<%} %>
		</nav>
	</header>
</section>
<aside>
	<nav class="rad-sidebar rad-nav-min">
		<ul>
			<li>
				<%if(mem.getAuth()==2){ %>
				<a href="MemberControl?command=memberGo&id=<%=mem.getId() %>" class="inbox">
				<i class="fas fa-user-alt"><span class="icon-bg rad-bg-success"></span></i>
					<span class="rad-sidebar-item">회원 관리</span>
				</a>
				<%}else{ %>
				<a href="MemberControl?command=ad_memberDeleteGo" class="inbox">
				<i class="fas fa-user-alt"><span class="icon-bg rad-bg-success"></span></i>
					<span class="rad-sidebar-item">회원 관리</span>
				</a>
				<%} %>
					
			</li>
			<li>
				<%if(mem.getAuth()==2){ %>
				<a href="HotelControl?command=ad_hotel">
				<i class="fas fa-hotel">
						<span class="icon-bg rad-bg-danger"></span>
					</i>
					<span class="rad-sidebar-item">호텔 관리</span>
				</a>
				<%}else{ %>
				<a href="PdsControl?command=ad_noticeGo" class="done"><i class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span class="rad-sidebar-item">공지사항</span></a>
 
			<%} %>
			</li>
				<%if(mem.getAuth()==2){ %>
			<li><a href="HotelControl?command=ad_chart" class="snooz"><i class="fas fa-chart-pie"><span class="icon-bg rad-bg-primary"></span></i><span class="rad-sidebar-item">매출 관리</span></a>
			<%} %>
 		
			</li>
			
				<%if(mem.getAuth()==2){ %>
			<li><a href="PdsControl?command=ad_noticeGo" class="done"><i class="fas fa-list-ul"><span class="icon-bg rad-bg-warning"></span></i><span class="rad-sidebar-item">공지사항</span></a>
			<%}%>
		
			</li>
			
		</ul>
	</nav>
</aside>

<section>
		<div class="rad-body-wrapper rad-nav-min">
			<div class="container-fluid">

		<header class="rad-page-title">
					<span>공지사항 </span>
					<small><%=mem.getName()%> 회원님</small>
								
				</header>

<div align="center">

<table border="1" style="background-color: white;">
<col width="50"><col width="100"><col width="400"><col width="80">
<col width="50"><col width="50"><col width="100">

<tr bgcolor="#FA8072" align="center" height="25">
	<td style="color: white;">번호</td>	<td style="color: white;">작성자</td>	<td style="color: white;">제목</td>	<td style="color: white;">다운로드</td>
		<td style="color: white;">조회수</td>	<td style="color: white;">다운수</td>	<td style="color: white;">작성일</td>
</tr>

<%
for(int i = 0;i < list.size(); i++){
	PdsDto pds = list.get(i);
	String bgcolor = "";
	if(i % 2 == 0){
		bgcolor = "white";
	}else{
		bgcolor = "#FFFFFF";
	}
	%>
	<tr  height="5px" class="_tr">
		<td><%=i + 1 %></td>
		<td><%=pds.getId() %></td>
		<td align="left">
		
		<%=arrow(pds.getDepth()) %>
		 <%if(pds.getDel()==1){ %>
		삭제된 글입니다
		<%}else{ %> 
			<a href="PdsControl?command=pdsdetail&seq=<%=pds.getSeq()%>">
					<%=pds.getTitle() %>
			</a>
			 <%} %> 
		</td>
		<td>
<%if(pds.getFilename().equals("answer")==false && pds.getDel()==0){ %>
			<input type="button" name="btnDown" value="파일"
				onclick="location.href='filedown?filename=<%=pds.getFilename() %>&seq=<%=pds.getSeq()%>'">
		<%} %>
		</td>
		<td><%=pds.getReadcount() %></td>
		<td><%=pds.getDowncount() %></td>
		<td><%=pds.getRegdate().substring(0, 10) %></td>	
	</tr>
	<%
}
%>
</table>
</div>
<%if(mem.getAuth()==1){ %>
<a href="ad_notice_write.jsp">자료 올리기</a>
<br><br>
<%} %>

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
