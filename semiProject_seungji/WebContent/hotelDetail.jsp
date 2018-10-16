<%@page import="dto.HotelDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String seq = request.getParameter("seq");
String id = request.getParameter("id");
String hotelname = request.getParameter("hotelname");
String region = request.getParameter("region");
String maxpeople = request.getParameter("maxpeople");
String price = request.getParameter("price");
String hotelphone = request.getParameter("hotelphone");
String readcount = request.getParameter("readcount");
/* System.out.println("seq = "+ seq);
System.out.println("id = "+ id);
System.out.println("hotelname = "+ hotelname);
System.out.println("region = "+ region);
System.out.println("maxpeople = "+ maxpeople);
System.out.println("price = "+ price);
System.out.println("hotelphone = "+ hotelphone);
System.out.println("readcount = "+ readcount); */

// 호텔 디테일 db에서 쏴준거 받기
HotelDto detailDto = (HotelDto)request.getAttribute("detailDto");
if(detailDto != null){
	request.setAttribute("detail", detailDto);
	
}

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial" rel="stylesheet" />
<link href="default.css" rel="stylesheet" type="text/css" media="all" />
<link href="fonts.css" rel="stylesheet" type="text/css" media="all" />
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

<script type="text/javascript">
	$(function () {
		
		// 이 페이지 켜자마자 디테일 가져오기
		$.ajax({
			url : "HotelControl",
			type : "post",
			data : {
				command : "detail",
				seq : <%=seq%>
			},
			success : function(data){
				alert("디테일 통신성공!");
				alert(${detail.description });
				/* $("#three-column").append(
						'<div class="tbox">' + 
							'<div class="title">' + 
								'<h2>호텔설명</h2>' + 
							'</div>' + 
							'<p>' + ${detail.description } + '</p>' + 
						'<a href="#" class="button">예약하기</a></div>'
							
					); */
			},
			error : function(){
				alert("통신실패!");
			}
		});
		
		
		$("#btn_detail").click(function () {
			alert("정보내놔");
			$("#three-column").append(
				'<div class="tbox">' + 
					'<div class="title">' + 
						'<h2>호텔설명</h2>' + 
					'</div>' + 
					'<p>' + ${detail.description } + '</p>' + 
				'<a href="#" class="button">예약하기</a></div>'
					
			);
			
		});
		$("#btn_review").click(function () {
			alert("리뷰내놔");
		});
		$("#btn_place").click(function () {
			alert("길내놔");
		});
	});
</script>
</head>
<body>

<!-- 맨 위 이미지 / 탭부분 -->
<div class="wrapper">
	<div id="banner" class="container"><img src="images/banner.jpg" width="1200" height="500" alt="" /></div>
	<div id="header-wrapper" style="background-color: #FA8072">
	<div id="header" class="container">
		<div id="logo">
        	<!-- <span class="icon icon-cog"></span> -->
			<h1><a href="#"><%=hotelname %></a></h1>
		</div>
		<div id="menu">
			<ul>
				<li class="active"><a accesskey="1" title="호텔정보" id="btn_detail">호텔정보</a></li>
				<li><a accesskey="2" title="사용자 후기" id="btn_review">사용자 후기</a></li>
				<li><a accesskey="3" title="오시는 길" id="btn_place">오시는 길</a></li>
			</ul>
		</div>
	</div>
</div>
</div>

<!-- 중간 콘텐츠 부분(핵심) -->
<div class="wrapper">
<div class="title">
<div id="welcome" class="container">
	  <h2>Welcome to our hotel</h2>
		</div>
		<p id="description"></p>	<!-- 이거 필요 없을듯.. -->
	</div>
	<div id="three-column" class="container">
		<div><span class="arrow-down"></span></div>
		<!-- <div id="tbox1">
			<div class="title">
				<h2>Maecenas luctus</h2>
			</div>
			<p>Nullam non wisi a sem semper eleifend. Donec mattis libero eget urna. Duis pretium velit ac suscipit mauris. Proin eu wisi suscipit nulla suscipit interdum.</p>
			<a href="#" class="button">Learn More</a> </div>
		<div id="tbox2">
			<div class="title">
				<h2>Integer gravida</h2>
			</div>
			<p>Proin eu wisi suscipit nulla suscipit interdum. Nullam non wisi a sem semper suscipit eleifend. Donec mattis libero eget urna. Duis  velit ac mauris.</p>
			<a href="#" class="button">Learn More</a> </div>
		<div id="tbox3">
			<div class="title">
				<h2>Praesent mauris</h2>
			</div>
			<p>Donec mattis libero eget urna. Duis pretium velit ac mauris. Proin eu wisi suscipit nulla suscipit interdum. Nullam non wisi a sem suscipit  eleifend.</p>
			<a href="#" class="button">Learn More</a> </div> -->
	</div>
</div>


<!-- 페이지 하단 주소 / 번호 / 이메일 나오는 부분 -->
<div id="footer">
	<div class="container">
		<div class="fbox1">
		<span class="icon icon-map-marker"></span>
			<span><strong><%=region %></strong></span>
		</div>
		<div class="fbox1">
			<span class="icon icon-phone"></span>
			<span><strong>
				Hotel phone: <%=hotelphone %>
			</strong></span>
		</div>
		<div class="fbox1">
			<span class="icon icon-envelope"></span>
			<span>businessname@business.com</span>	<!-- 여기에 관리자와 채팅기능 추가하면 좋을듯. -->
		</div>
	</div>
</div>
<!-- 맨 밑 배너. 기능은 거의 없고, 그냥 뷰 이쁘게 해줄라고 넣음.. -->
<div id="copyright">
	<p>&copy; Untitled. All rights reserved. | Design by <a href="http://www.iei.or.kr/main/main.kh" rel="nofollow" target="_blank">KH CLASS_3 semi project 6조</a>.</p>
	<ul class="contact">
		<li><a href="#" class="icon icon-twitter"><span>Twitter</span></a></li>
		<li><a href="#" class="icon icon-facebook"><span></span></a></li>
		<li><a href="#" class="icon icon-dribbble"><span>Pinterest</span></a></li>
		<li><a href="#" class="icon icon-tumblr"><span>Google+</span></a></li>
		<li><a href="#" class="icon icon-rss"><span>Pinterest</span></a></li>
	</ul>
</div>

</body>
</html>