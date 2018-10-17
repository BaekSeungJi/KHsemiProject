<%@page import="dto.ReviewDto"%>
<%@page import="java.util.List"%>
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


// 리뷰 리스트 받아오기
List<ReviewDto> reviewList = (List<ReviewDto>)request.getAttribute("reviewList");



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
		
		// 디테일 페이지 로딩되자마자 호텔디테일 가져오기
		getDetail();
		
		$("#btn_detail").click(function () {
			// '호텔정보' 탭 누르면 디테일 가져오기(그냥 페이지 새로고침만 해줘도 된다. 자동으로 getDetail 불러와질것.)
			// alert("정보내놔");
				window.location.reload();
			
		});
		
		// 디테일 가져오는 함수
		function getDetail() {
			$.ajax({
				url : "HotelControl",
				type : "post",
				data : {
					command : "detail",
					seq : <%=seq %>
				},
				success : function(data){
					if(data == "") return;
					alert("디테일 통신성공!");
					// DESCRIPTION, READCOUNT, REGDATE 중에서 description만 찾아와서 뿌리기(find('태그이름') 사용)
					$("#hotel_description").html($("#hotel_description").html(data).find('description').text());
					//$("#hotel_description").html(data).find('description').text();
					// READCOUNT, REGDATE 도 뿌려주기 추가해야함!!!!!!!!!!!
					
					// 기타 디테일들. 태그를 함께 생성하며 웹에 띄워주기
					$("#three-column").append(
							'<div class="tbox">' + 
								'<div class="title">' + 
									'<h2>가격</h2>' + 
								'</div>' + 
								'<p>' + <%=price %> + '원</p>' + 
								'<a href="#" class="button">예약하기</a></div>' +
							
							'<div class="tbox">' + 
							'<div class="title">' + 
								'<h2>최대인원</h2>' + 
							'</div>' + 
							'<p>' + <%=maxpeople %> + '인</p>' + 
						'<a href="#" class="button">예약하기</a></div>' +
						
						'<div class="tbox">' + 
						'<div class="title">' + 
							'<h2>호텔 매니저</h2>' + 
						'</div>' + 
						'<p><%=id %>님</p>' +
						'<a href="#" class="button">상담하기</a></div>'
						);
				},
				error : function(){
					alert("통신실패!");
				}
			});
		}
		
		
		
		
		$("#btn_review").click(function () {
			alert("리뷰내놔");
			getReview();
			
		});
		$("#btn_place").click(function () {
			alert("길내놔");
		});
		
		
		// 호텔 리뷰 가져오는 함수
		function getReview() {
			
			var hotelname = "<%=hotelname %>";
			
			$.ajax({
				url : "ReviewControl",
				type : "post",
				data : {
					command : "review",
					hotelname : hotelname
				},
				success : function(data){
					if(data == "") return;
					alert("리뷰 통신성공!");
					
					/* 여기 전부 바꾸기. */
					$("#centerContents").empty();
					// 설명 ==> remove : 요소 자체를 지운다, empty : 요소 자체가 아니라 요소의 내용을 지운다.
					// 중간 콘텐츠 부분 영역은 남겨두되, 정보내용만 지우고 그 위에 대신 리뷰내용을 깔려는 거니까, empty를 쓴다.
					
					var $div = $('<div class="title"><div id="welcome" class="container"><h2>호텔 리뷰를 꼼꼼히 확인하세요!</h2></div></div>');
					/* var div = document.createElement('div');
					var text = document.createTextNode('Hollo');
					div.appendChild(text); */
										
					$("#centerContents").append($div);
					// $div.appendTo($('#centerContents'));
					
					<%-- <%
					if(reviewList != null){
						for(int i=0; i<reviewList.size(); i++){
							%>
							$("#centerAgain").append(
									'<div class="wrapper" id="centerContents">' + 
									'<div class="title">' + 
									'<div id="welcome" class="container">' +
									'<h2>Welcome to our hotel</h2>' +
									'</div>' +
									'</div>' + 
									'</div>');
							<%
						}
					}
					%> --%>
					
					
				},
				error : function(){
					alert("통신실패!");
				}
			});
		}
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

<div id="centerAgain"></div>

<!-- 중간 콘텐츠 부분(핵심) -->
<div class="wrapper" id="centerContents">
<div class="title">
<div id="welcome" class="container">
	  <h2>Welcome to our hotel</h2>
		</div>
	</div>
	<div id="three-column" class="container">
		<div><span id="hotel_description" class="arrow-down"></span></div>
		<!-- ajax를 통해 이부분에 호텔정보가 출력됨. -->
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