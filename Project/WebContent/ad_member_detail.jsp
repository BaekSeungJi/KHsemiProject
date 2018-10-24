<%@page import="dto.ReviewDto"%>
<%@page import="dto.ReserveDto"%>
<%@page import="java.util.List"%>
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
<link rel="stylesheet" href="css/style1.css">

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

  
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
section{
  margin: 50px;
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





  




<%
MemberDto user = (MemberDto)session.getAttribute("login");


String hotelname =(String)session.getAttribute("hotelname");

List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("reserveList");

List<ReviewDto> list2 = (List<ReviewDto>)request.getAttribute("reviewList");

%>



<body>

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



  <h1>예약 내역</h1>

  <div class="tbl-header">
    <table>
      <thead>
        <tr>
          <th>체크인 날짜</th>
          <th>체크아웃 날짜</th>
          <th>호텔 이름</th>
          <th>요구 사항</th>
          <th>등록일</th>
      	<th>수정</th>
          <th>취소</th>
        </tr>
      </thead>
    </table>
  </div>
  
 
  <div class="tbl-content">
  
    <table cellpadding="0" cellspacing="0" border=" ">
      <tbody>
 	  <% 
       for(int i= 0; i<list.size();i++){
    	   ReserveDto dto = list.get(i);
      %>
      <%if(dto.getHotelname().equals(hotelname)){ %>
      <tr onmouseover="this.style.background='#f0f0f0'"
        	onmouseout="this.style.background='white'">
          <td>
      
          <input type="hidden" name="command" value="ad_reserveUpdate">
          <input type="hidden" id="id" value="<%=dto.getId()%>">
          
          
          <input type="text" value="<%=dto.getCheckin() %>" id="checkin"></td>
        
             <td>   <input type="text" value="<%=dto.getCheckout() %>" id="checkout"></td></td>
          <td><%=dto.getHotelname() %></td>
          <td><input type="text" value="<%=dto.getRequest() %>" id="request"></td>
          <td><%=dto.getRegdate() %></td>
           <td>
            <%if(dto.getDel() == 0){
        	  %><button onclick="location.href='ad_memberUpdateGo.jsp?seq=<%=dto.getSeq() %>&id=<%=dto.getId()%>'">수정</button>
        	  
        <%  }else{
        	%> 취소된 예약입니다
        	<%
      		 }
          %>
           
           </td>
         
          <td>
          <%if(dto.getDel() == 0){
        	  %> <button onclick="location.href='ReserveControl?command=ad_reserveDelete&seq=<%=dto.getSeq() %>&id=<%=dto.getId()%>'">예약취소</button>
        	  
        <%  }else{
        	%> 취소된 예약입니다
        	<%
      		 }
          %>

          </td>
          
        </tr>
        <%} %>
       <%
       }
       %>
      </tbody>
    </table>
   
  </div>


<div style="padding-bottom: 100px;">

 <h1>작성한 후기</h1>

  <div class="tbl-header">
    <table>
      <thead>
        <tr>
          <th>호텔이름</th>
          <th>제목</th>
          <th>내용</th>
          <th>작성 날짜</th>
          <th>삭제</th>
        </tr>
      </thead>
    </table>
  </div>
  
  
  
  <div class="tbl-content">
    <table>
      <tbody>
     <% 
       for(int i= 0; i<list2.size();i++){
    	   ReviewDto dto2 = list2.get(i);
      %>
       <tr onmouseover="this.style.background='#f0f0f0'"
        	onmouseout="this.style.background='white'">
          <td><%=dto2.getHotelname() %></td>
          <td><%=dto2.getTitle() %></td>
          <td><%=dto2.getContent() %></td>
          <td><%=dto2.getRegdate() %></td>
          <td>
           <%if(dto2.getDel() == 0){
        	  %><input type="button" value="삭제" onclick="location.href='ReviewControl?command=ad_reviewDelete&seq=<%=dto2.getNum() %>&id=<%=dto2.getId()%>'">
        	<%  }else{
        	%> 삭제된 후기입니다
        	<%
      		 }
          %>
          </td>
         
          
        </tr>
       <%
       }
       %>
      </tbody>
    </table>
  </div>
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
