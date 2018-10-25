<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="dto.MemberDto"%>
<%@page import="dto.HotelDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
// 첫 페이지에서 넘어온 맵 검색어
String keyword = request.getParameter("keyword");
%>

<head>
<title>mainBbs.jsp</title>

<style type="text/css">
/* 검색옵션 탭마다 붙는 분홍네모 아이콘 */
ul.style2 li{
	list-style-type : none;
	padding-left : 1.5em;
	background-image : url("./css/images/header-wrapper-bg.png");
	background-size : 1em;
	background-repeat : no-repeat;
}

#sidebox{
	top:30%;
	position:absolute; 
}
</style>
	
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link href='http://fonts.googleapis.com/css?family=Oswald:400,300' rel='stylesheet' type='text/css'>
	<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
	<!-- 맵 구동용 제이쿼리 버전 -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- 슬라이더용 제이쿼리 버전 -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	
	<!-- 맵 검색용 js -->
	<script src="js/skel.min.js"></script>
	<script src="js/skel-panels.min.js"></script>
	<script src="js/init.js"></script>
	
	<!-- 디자인 css -->
	<link rel="stylesheet" href="./css/skel-noscript.css" />
	<link rel="stylesheet" href="./css/style.css" />
	<link rel="stylesheet" href="./css/style-desktop.css" />
	
	<!-- 다음맵 api -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=916ea874e228791dbf525372ff0244e5&libraries=services"></script>
	
	
	<script type="text/javascript">
		$(document).ready(function () {
			// 페이지 실행되자마자, 검색된 맵 불러오기.
			updateMap(1);
			
			// 체크아웃 날짜칸 선택못하게
			$("#date2").attr("disabled", true);
			
			// 지도이미지 바로 아래 검색버튼 클릭하면 Map 실시간 업데이트
			$("#btn_map").click(function () {
				updateMap(2);
				
			});
			
			function updateMap( number) {
				var keyword = "";
				
				if(number == 1){
					keyword = $('#searchText').val() + " 호텔";
				}else if(number == 2){
					keyword = $('#newMapKeyword').val() + " 호텔";
				}
				
				$.ajax({
					url : "Map.jsp",
					type : "get",
					data : "keyword="+keyword,
					success : function(data){
						//alert("통신성공!");
						$("#mainMap").html(data);
					},
					error : function(){
						alert("통신실패!");
					}
				});
			}
			
			
			// 조건에 맞는 호텔목록 불러오기 (검색옵션 -> search more 버튼클릭) 
			$("#btn_search").click(function () {
				
				$("#hotelListTable").empty();
				
				//$("#hotelImage").empty();
				//$("#searchHotelList").empty();
				
				var place = $("#searchText").val();
				var price = $("#opa").text().replace(/[^0-9\.]+/g, "");
				var people = $("#sel_people").val();
				var date1 = $("#date1").val();
				var date2 = $("#date2").val();
				
				if(place == null || place=="" || price == null || price=="" ||
					people == null || people == "" || date1 == null || date1 == "" ||
					date2 == null || date2=="" ){
					alert("빈칸이 있습니다 .모든 칸을 전부 입력해주세요");
					return;
				}
						
				$.ajax({
					url : "HotelControl",
					type : "get",
					data : {
						"command" : "search",
						"place" : place,
						"price" : price,
						"people" : people,
						"date1" : date1,
						"date2" : date2
					},
					success : function(data){
						if(data == "") return;
						alert("검색 통신성공!");
						// alert(data);
						// json형태로 파싱한 데이터의 result부분을 가져온다.
						var parsed = JSON.parse(data);
						var result = parsed.result;
						// addList 함수를 통해 매개변수로 넘겨준다. 나머지 ul에 까는 작업은 해당 함수 내부에서 처리해줄것. 
						for(var i = 0; i< result.length; i++){
							addList(result[i][0].value, result[i][1].value, result[i][2].value,
									result[i][3].value, result[i][4].value, result[i][5].value,
									result[i][6].value, result[i][7].value, result[i][8].value,
									result[i][9].value);
						}
					},
					error : function(){
						alert("통신실패!");
					}
				});
				
			});
			
			
			// json으로 잘라온 리스트를 실제로 태그와 함께 동적생성하며 ul에 뿌려주는 함수
			function addList(SEQ, ID, HOTELNAME, REGION, MAXPEOPLE, PRICE, HOTELPHONE, DEL, READCOUNT, IMAGE) {
				
				
				// 0 == 노삭제
				if(DEL == 0) {
					
					$("#hotelListTable").append(
							'<tr>' +
								'<td>' + 
									'<ul class="style4" >' + 
										'<li>' +
										'<h3>' +
											'<a href="hotelDetail.jsp?seq=' + SEQ + 
											'&id='+ ID + '&hotelname='+ HOTELNAME + 
											'&region=' + REGION + '&maxpeople='+ MAXPEOPLE + 
											'&price='+ PRICE + '&hotelphone='+ HOTELPHONE + 
											'&readcount='+ READCOUNT + '&image=' + IMAGE +'">' 
												+ HOTELNAME + 
											'</a>' +
										'</h3>' +
										'<p>' + REGION + '</p>' +
										'</li>' +
									'</ul>' +
								'</td>' +
								'<td width="40%">' +
									'<img alt="" src="./hotelImage/' + IMAGE + '" width="100%" height="120px" style="margin-top: 40px">' +
								'</td>' +
							'</tr>'
					);
				// 1 == 삭제 
				}else if(DEL == 1){
					$("#hotelListTable").append(
							
							'<tr>' +
							'<td>' + 
								'<ul class="style4">' + 
									'<li>' +
									'<h3>관리자에 의해 삭제된 호텔입니다.</h3>' +
									'<p>더 좋은 호텔이 당신을 기다리고 있습니다!</p>' +
									'</li>' +
								'</ul>' +
							'</td>' +
							'<td width="36%" height="100%">' +
								'<img alt="" src="' + IMAGE + '" width="80%">' +
							'</td>' +
						'</tr>'
							
					);
				}
			};
			
			// 가격 슬라이더
			$("#slider1").slider({
				animate:true,
				min : 10000,
				max : 100000,
				slide:function(event, ui){	// ui : 수치
					//console.log("move");
					
					$("#opa").text(ui.value + "원");
				}
			});
			
			
			// 달력 만들기(체크인)
			// 오늘 이후 날짜만 선택 ==> minDate : 0
			$("#date1").datepicker({
				minDate : 0,
				dateFormat:"yy/mm/dd",
				dayNamesMin:["일", "월", "화", "수", "목", "금", "토"],	// 배열을 잡은것.
				monthNames:["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
				onSelect:function( d ){
					// 연,월,일 구하기
//					alert(d + "선택됐습니다");
					var arr = d.split("/");
					$("#date1").text(arr[0]);
					$("#date1").append(arr[1]);
					$("#date1").append(arr[2]);
					
					// 요일 구하기
					var date = new Date( $("#date1").datepicker({dateFormat:'yy/mm/dd'}).val() );
			//		alert("date1 : "+date.getDay() );	// 0(일요일)~6(토요일)
					
					var week = new Array("일", "월", "화", "수", "목", "금", "토");
					$("#date1").append( week[ date.getDay() ] );
				},
				onClose : function (selectedDate) {
					if( selectedDate != "" ) {
						// 체크아웃(퇴실일)을 체크인(입실일)의 다음날부터 가능하게
						var curDate = $("#date1").datepicker("getDate");  // Date return
						curDate.setDate( curDate.getDate() + 1 );
						$("#date2").datepicker("option", "minDate", curDate);
						// 체크아웃 태그 활성화
						$("#date2").attr("disabled", false);
					}
				}
			});
			
			// 달력 만들기(체크아웃)
			$("#date2").datepicker({
				dateFormat:"yy/mm/dd",
				dayNamesMin:["일", "월", "화", "수", "목", "금", "토"],	// 배열을 잡은것.
				monthNames:["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
				onSelect:function( d ){
					// 연,월,일 구하기
//					alert(d + "선택됐습니다");
					var arr = d.split("/");
					$("#date2").text(arr[0]);
					$("#date2").append(arr[1]);
					$("#date2").append(arr[2]);
					
					// 요일 구하기
					var date = new Date( $("#date2").datepicker({dateFormat:'yy/mm/dd'}).val() );
					//alert("this : "+date.getDay() );	// 0(일요일)~6(토요일)
					
					var week = new Array("일", "월", "화", "수", "목", "금", "토");
					$("#date2").append( week[ date.getDay() ] );
				},
				onClose : function( selectedDate ) {  // 날짜를 설정 후 달력이 닫힐 때 실행
                    if( selectedDate != "" ) {
                        // xxx의 maxDate를 yyy의 날짜로 설정
                        $("#date1").datepicker("option", "maxDate", selectedDate);
                    }
                }

			});
			
			
			// 스크롤 따라오는 박스
			var currentPosition = parseInt($("#sidebox").css("top"));
			$(window).scroll(function() { 
				var position = $(window).scrollTop(); 
				//console.log("스크롤 위치 = "+position);
				$("#sidebox").stop().animate({"top":position+currentPosition+"px"},500); 
				
				if(position > 370){
					$("#sidebox").stop();
				};
			});


		});
		</script>
	
	
	
</head>
<body class="homepage">

<!-- 로그인여부 확인 / 로그인한 사람의 정보 가져오기. -->
<%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){   // 로그인 정보가 안넘어왔을때. 혹은 기간이 만료했을때(로그인하고 한참 지남)
   %>
   <script type="text/javascript">
   alert("비회원 상태로 접속하셨습니다.");
   </script>
   <%
}else{
   mem = (MemberDto)ologin;
}
%>

	<!-- Header(로고) -->
	<div id="header-wrapper">
		<div class="container">
			<div id="header">
				<div id="logo">
					<h1><a href="#">Today's home</a></h1>
					<span>by KHsemiProject</span>
				</div>
				<nav id="nav">
				<% if(mem == null){ %>
					<ul>
						<li class="current_page_item"><a href="index.jsp">Homepage</a></li>
						<li><a href="loginview.jsp">로그인</a></li>	
						<li><a href="Signup.jsp">회원가입</a></li>
					</ul>
				<%}else if(mem.getAuth() == 3){ %>
					<ul>
						<li class="current_page_item"><a href="index.jsp">Homepage</a></li>
						<li><a href="chatBox.jsp">일대일 채팅</a></li>
						<li><a href="mypage.jsp">마이페이지</a></li>	
						<li><a href="logout.jsp">로그아웃</a></li>
					</ul>
				<%}else if(mem.getAuth() == 1 || mem.getAuth() == 2){ %>
					<ul>
						<li class="current_page_item"><a href="index.jsp">Homepage</a></li>
						<li><a href="chatBox.jsp">일대일 채팅</a></li>
						<li><a href="start.jsp">관리자 모드</a></li>
						<li><a href="logout.jsp">로그아웃</a></li>
					</ul>
				<%} %>
				</nav>
			</div>
		</div>
		
	</div>
	<!-- Page Wrapper(로고와 밑단을 제외한 중간 콘텐츠부분 전체) -->
	<div id="wrapper" class="container">

		<!-- Page Content(위와 마찬가지) -->
		<div id="page" class="row">
			
			<!-- Content Area(왼쪽 콘텐츠들) -->
			<div id="content" class="8u skel-cell-important">
				
				<!-- Main Content Area(왼쪽 상단. 지도부분) -->
				<section>
					<h2>어느 숙소에 묵으시겠습니까?</h2>
					<p id="mainMap"><a href="#"><img src="./css/images/pics02.jpg" alt=""></a></p>
					<input type="text" id="newMapKeyword">
					<button type="button" id="btn_map">검색</button>
				</section>
				
				
				<!-- Two Column Area(왼쪽 하단. 게시판 부분) -->
				<section>
					<div id="two-column" class="5grid">
						<div class="row">
							<h2>호텔 검색 결과</h2>
							<!-- <div id="hotelImage"></div> -->
							<!-- <ul class="style4" id="searchHotelList">
								<li class="first">
									<h3>Mauris vulputate dolor sit amet</h3>
									<p><a href="#">Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum. </a></p>
								</li>
							</ul> -->
							
							<table id="hotelListTable" style="margin-left: 7%; margin-top: -1%;">
							</table>
							
						</div>
					</div>					
				</section>				


			</div>

			<!-- Sidebar Area(오른쪽 콘텐츠들) -->
			<div id="sidebar" class="4u">
			
				<!-- Sidebar Section 1 -->
				

				<!-- Sidebar Section 2 -->
				<div id="sidebox" >
				<section id="box1">
					<h2 style="margin-top: 5%;">검색옵션</h2>
					<ul class="style2">
						<li>
							<p>지역 : <input type="text" value="<%=keyword%>" id="searchText" size="25px"></p>
						</li>
						<li>
							<p>가격 : <div id="slider1" style="width: 150px; margin-left: 50px;"></p>
							<p id="opa"></p>
						</li>
						<li>
							<p>인원 : <select id="sel_people" style="margin-left: 10px;">
										<option value="1" selected>1명</option>
										<option value="2">2명</option>
										<option value="3">3명</option>
										<option value="4">4명 이상</option>
									</select>
							</p>
						</li>
						<li>
							<p>날짜 : <input type="text" class="date" id="date1" placeholder="체크인" size="8px">~
									<input type="text" class="date" id="date2" placeholder="체크아웃" size="8px"></p>
						</li>
					</ul>
					<p><a href="#" class="button" id="btn_search"><span>Search More</span></a></p>
				</section>
				</div>

			</div>
			
		</div>
		<!-- Page Content -->

	</div>
	<!-- Wrapper Ends Here -->

	<!-- Copyright -->
	<div id="copyright">
		<div class="container">
			Design by <a href="http://www.iei.or.kr/main/main.kh" target="_blank">KH CLASS_3 semi project 6조</a>
		</div>
	</div>

		
</body>
</html>