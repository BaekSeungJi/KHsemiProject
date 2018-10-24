<%@page import="dto.MemberDto"%>
<%@page import="dto.ReviewDto"%>
<%@page import="java.util.List"%>
<%@page import="dto.HotelDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
String seq = request.getParameter("seq");
String id = request.getParameter("id");
String hotelname = request.getParameter("hotelname");
String region = request.getParameter("region");
String maxpeople = request.getParameter("maxpeople");
String price = request.getParameter("price");
String hotelphone = request.getParameter("hotelphone");
String readcount = request.getParameter("readcount");
String image = request.getParameter("image");
System.out.println("디테일페이지 호텔이름 넘어온것 = " + image);
%>

<title>Insert title here</title>
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial" rel="stylesheet" />
<link href="./css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="./css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=916ea874e228791dbf525372ff0244e5&libraries=services"></script>


<style type="text/css">
/* 로그인, 로그아웃 버튼용 */
a.btn {
	font-size : 50px;
	color: black;
	padding: 10px;
	border: 1px solid rgba(255,255,255,0.5);
	-webkit-transition: all 0.2s;
	-moz-transition: all 0.2s;
	transition: all 0.2s;
}
a.btn:hover {
	background: #fa8072;
	border: 1px solid #fa8072;
	color: black;
}
</style>

<script type="text/javascript">
	$(document).ready(function () {
		
		// 디테일 페이지 로딩되자마자 호텔디테일 가져오기
		getDetail();
		// 리뷰 div("우리 호텔을 다녀간 사람들의 리뷰를 확인하세요!")안보이게
		$("#reviewDiv").hide();
		// 주소 div("저희 호텔은 이곳에 위치해 있습니다!") 안보이게
		$("#addressDiv").hide();
		
		// [1] '호텔정보' 탭
		$("#btn_detail").click(function () {
			
			// alert("정보내놔");
				window.location.reload();
				// 그냥 페이지 새로고침. 자동으로 getDetail 불러와질것.
			
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
					//alert("디테일 통신성공!");
					// DESCRIPTION, READCOUNT, REGDATE 중에서 description만 찾아와서 뿌리기(find('태그이름') 사용)
					$("#hotel_description").html($("#hotel_description").html(data).find('description').text());
					//$("#hotel_description").html(data).find('description').text();
					// READCOUNT, REGDATE 도 뿌려주기
					$("#hotelRegDate").html("호텔 등록일 : " + $("#hotelRegDate").html(data).find('regdate').text());
              		$("#hotelReadCount").html("조회수 : " + $("#hotelReadCount").html(data).find('readcount').text());
					
					// 기타 디테일들. 태그를 함께 생성하며 웹에 띄워주기
					$("#three-column").append(
							'<div class="tbox">' + 
								'<div class="title">' + 
									'<h2>가격</h2>' + 
								'</div>' + 
								'<p>' + <%=price %> + '원</p>' + 
								<%-- '<a href="reservewrite.jsp?seq='+<%=seq%>+'" class="button">예약하기</a></div>' + --%>
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
						'<a href="NewFile.jsp" class="button" id="chat">상담하기</a></div>'
						);
				},
				error : function(){
					alert("통신실패!");
				}
			});
		}
		
		
		// [2] '사용자후기' 탭
		$(document).on("click", "#btn_review", function () {
			//alert("리뷰내놔");
			
			// 기존 중간영역 콘텐츠(호텔정보/오시는길) 비우기
			//$("#centerContents").empty();
			$("#detailDiv").remove();
			$("#hotel_description").remove();
			$(".tbox").remove();
			
			$("#addressDiv").hide();
			$("#addressMapHere").hide();
			
			// 리뷰탭 선택 표시(클래스 삭제/클래스 추가)
			$("#tap1").removeAttr("class");
			$("#tap3").removeAttr("class");
			$("#tap2").attr("class", "active");
			
			// 리뷰 내용으로 다시 채우기
			$("#reviewDiv").show();
			getReview();
			
			
		});
		
		// 호텔 리뷰 가져오는 함수
		function getReview() {
			
			var hotelname = "<%=hotelname %>";
			
			$.ajax({
				url : "ReviewControl",
				type : "get",
				data : {
					"command":"review",
					"hotelname":hotelname
				},
				success : function(data){
					if(data == "[]") {
						$("#centerContents").append(
	                    		"<br><br><div class='addReviewList' align='center'><strong>"+
	                    		"아직 등록된 후기가 없습니다ㅠㅠ" + "</strong></div><br><br><br><br>");
						return;
					};
					//alert("리뷰 통신성공!");
					//alert(data);
					
					var parsed = JSON.parse(data);
					
					$.each(parsed, function (i, item) { // i는 iterator, item은 각 아이템
						// NUM, ID, TITLE, CONTENT, SCORE, DEL, REGDATE를 가져옴
						
						if(item.del == 0){
		                    $("#centerContents").append(
		                    		"<div class='addReviewList' align='center'> 번호 : " + (i+1) + "<br>" +
		                    		" 작성자 : " + item.id + "<br>" +
		                    		" 제목 : " + item.title + "<br>" +
		                    		" 내용 = " + item.content + "<br>" +
		                    		" 별점 = " + item.score + "<br>" +
		                    		" 등록일 = " + item.regdate + "</div><br><br>");
						}else if(item.del == 1){
							$("#centerContents").append(
		                    		"<div class='addReviewList' align='center'> 번호 : " + (i+1) + "<br>" +
		                    		" 작성자 : " + item.id + "<br>" +
		                    		" 해당 후기는 관리자에 의해 삭제됐습니다." + "</div><br><br>");
						}
	                });
					
				},
				error : function(){
					alert("통신실패!");
				}
			});
		};
		
		
		// [3] '오시는 길' 탭
		$("#btn_place").click(function () {
			//alert("길내놔");
			
			// 기존 중간영역 콘텐츠(호텔정보/리뷰정보) 비우기
			$("#detailDiv").hide();
			$("#hotel_description").remove();
			$(".tbox").remove();
			
			$("#reviewDiv").hide();
			$(".addReviewList").remove();
			//$("#centerContents").empty();
			
			// 리뷰탭 선택 표시(클래스 삭제/클래스 추가)
			$("#tap1").removeAttr("class");
			$("#tap2").removeAttr("class");
			$("#tap3").attr("class", "active");
			
			// 주소 div("저희 호텔은 이곳에 위치해 있습니다!") 보이게
			$("#addressDiv").show();
			
			// 맵 영역 보이게
			$("#addressMapHere").show();
			
			// 맵 깔아주기
			getAddressMap();
			
		});
		
		
		function getAddressMap() {
			var address = "<%=region %>";
			
			$.ajax({
				url : "detailMap.jsp",
				type : "get",
				data : "address="+address,
				success : function(data){
					//alert("통신성공!");
					$("#addressMapHere").html(data);
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


<!-- 로그인여부 확인 / 로그인한 사람의 정보 가져오기. -->
<%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){   // 로그인 정보가 안넘어왔을때. 혹은 기간이 만료했을때(로그인하고 한참 지남)
   %>
   <script type="text/javascript">
   $(document).ready(function () {
	   $("#logoutBtn").hide();
	   $("#loginBtn").show();
	   
	   $(document).on("click", ".button", function () {
			alert("로그인 후 이용가능합니다. 로그인해주세요.");
			/* $(".class").attr("href", "#"); */
		});
	});
   </script>
   <%
}else{
   mem = (MemberDto)ologin;
   %>
   	<script type="text/javascript">
   	$(document).ready(function () {
   		$("#loginBtn").hide();
   		$("#logoutBtn").show();
	   
	   $(document).on("click", ".button", function () {
		   alert("예약페이지로 넘어갑니다");
			location.href="reservewrite.jsp?seq=<%=seq%>";
		});
	});
   </script>
   <%
};
%>

<!-- 맨 위 이미지 / 탭부분 -->
<div class="wrapper">	
	<a class="btn" href="loginview.jsp" title="Login" id="loginBtn">Login</a>
	<a class="btn" href="logout.jsp" title="Logout" id="logoutBtn">Logout</a>
	<div id="banner" class="container"><img src="<%=image %>" width="1200" height="500" alt="" /></div>
	<div id="header-wrapper" style="background-color: #FA8072">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#"><%=hotelname %></a></h1>
			<h4 id="hotelRegDate"></h4>
         	<h4 id="hotelReadCount"></h4>
		</div>
		<div id="menu">
			<ul>
				<li class="active" id="tap1"><a accesskey="1" title="호텔정보" id="btn_detail">호텔정보</a></li>
				<li id="tap2"><a accesskey="2" title="사용자 후기" id="btn_review">사용자 후기</a></li>
				<li id="tap3"><a accesskey="3" title="오시는 길" id="btn_place">오시는 길</a></li>
			</ul>
		</div>
	</div>
</div>
</div>

<div id="centerAgain"></div>

<!-- 중간 콘텐츠 부분(핵심) -->
<div class="wrapper" id="centerContents">

<div class="title" id="detailDiv">
<div id="welcome" class="container">
	  <h2>Welcome to our hotel</h2>
		</div>
	</div>
	<div id="three-column" class="container">
		<div><span id="hotel_description" class="arrow-down"></span></div>
		<!-- ajax를 통해 이부분에 호텔정보가 출력됨. -->

<!-- '사용자 후기' 탭 상단노출 -->
<div id="reviewDiv">
	<div class="title">
	<div id="welcome" class="container">
	  <h2>우리 호텔을 다녀간 사람들의 리뷰를 확인하세요!</h2>
		</div>
	</div>
	<div id="three-column" class="container">
	</div>
</div>

<!-- '오시는 길' 탭 상단노출 -->
<div id="addressDiv">
	<div class="title">
	<div id="welcome" class="container">
	  <h2>저희 호텔은 이곳에 위치해 있습니다!</h2>
		</div>
	</div>
	<div id="three-column" class="container">
	</div>
	<div id="addressMapHere"></div>
</div>
	
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
			<span>businessname@business.com</span>	<!-- 관리자 이메일을 받아와야 하는데... -->
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