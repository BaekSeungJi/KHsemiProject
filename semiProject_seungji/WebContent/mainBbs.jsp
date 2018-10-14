<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String keyword = request.getParameter("keyword");
%>

	<head>
		<title>mainBbs.jsp</title>
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
		
		<script src="js/skel.min.js"></script>
		<script src="js/skel-panels.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel-noscript.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
		
		
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=916ea874e228791dbf525372ff0244e5&libraries=services"></script>
		
		
		<script type="text/javascript">
		$(function () {
			// 페이지 실행되자마자, 검색된 맵 불러오기.(아래 함수와 실행내용은 동일함.)
			var keyword = $('#searchText').val();
			
			$.ajax({
				url : "Map.jsp",
				type : "get",
				data : "keyword="+keyword,
				success : function(data){
					alert("통신성공!");
					$("#mainMap").html(data);
				},
				error : function(){
					alert("통신실패!");
				}
			});
			
			// 검색버튼 클릭하면 Map 업데이트
			/* $("#btn_search").click(function () {
				
				var keyword = $('#searchText').val();
				
				$.ajax({
					url : "Map.jsp",
					type : "get",
					data : "keyword="+keyword,
					success : function(data){
						alert("통신성공!");
						$("#mainMap").html(data);
					},
					error : function(){
						alert("통신실패!");
					}
				});
				
			}); */
			
			$("#btn_search").click(function () {
				var place = $("#searchText").val().trim();
				var price = $("#opa").text().replace(/[^0-9\.]+/g, "");
				var people = $("#sel_people").val();
				var date1 = $("#date1").val();
				var date2 = $("#date2").val();
				
				/* alert("place = " + place + " price = " + price + " people = " + people 
						+ " date1 = "+ date1 + " date2 = " + date2); */
						
				$.ajax({
					url : "HotelControl",
					type : "get",
					data : "command=search"+"&place="+place+"&price="+price+"&people="+people+"&date1="+date1+"&date2="+date2,
					success : function(data){
						alert("통신성공!");
						alert(data);
					},
					error : function(){
						alert("통신실패!");
					}
				});
				
			});
			
			// 가격 슬라이더
			$("#slider1").slider({
				animate:true,
				min : 10000,
				max : 100000,
				slide:function(event, ui){	// ui : 수치
					console.log("move");
					
					$("#opa").text(ui.value + "원");
				}
			});
			
			
			// 달력 만들기
			$("#date1").datepicker({
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
					alert("date1 : "+date.getDay() );	// 0(일요일)~6(토요일)
					
					var week = new Array("일", "월", "화", "수", "목", "금", "토");
					$("#date1").append( week[ date.getDay() ] );
				}
			});
			
			// 달력 만들기
			$(".date").datepicker({
				dateFormat:"yy/mm/dd",
				dayNamesMin:["일", "월", "화", "수", "목", "금", "토"],	// 배열을 잡은것.
				monthNames:["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
				onSelect:function( d ){
					// 연,월,일 구하기
//					alert(d + "선택됐습니다");
					var arr = d.split("/");
					$(this).text(arr[0]);
					$(this).append(arr[1]);
					$(this).append(arr[2]);
					
					// 요일 구하기
					var date = new Date( $(this).datepicker({dateFormat:'yy/mm/dd'}).val() );
					alert("this : "+date.getDay() );	// 0(일요일)~6(토요일)
					
					var week = new Array("일", "월", "화", "수", "목", "금", "토");
					$(this).append( week[ date.getDay() ] );
				}
			});
			
			
			// 스크롤 따라오는 박스
			var currentPosition = parseInt($("#sidebox").css("top"));
			$(window).scroll(function() { 
				var position = $(window).scrollTop(); 
				$("#sidebox").stop().animate({"top":position+currentPosition+"px"},1000); 
			});
			
			
			



		});

		</script>
		
		
		<style type="text/css">
		
		/* 검색옵션 탭마다 붙는 분홍네모 아이콘 */
		ul.style2 li{
			list-style-type : none;
			padding-left : 1.5em;
			background-image : url('./css/images/header-wrapper-bg.png');
			background-size : 1em;
			background-repeat : no-repeat;
		}
		
		#sidebox{
			top:200px;
			position:absolute; 
		}
		</style>
		
	</head>
	<body class="homepage">
		<!-- Header(로고) -->
		<div id="header-wrapper">
		
			<div class="container">
				<div id="header">
					<div id="logo">
						<h1><a href="#">Today's home</a></h1>
						<span>by KHsemiProject</span>
					</div>
					<nav id="nav">
						<ul>
							<li class="current_page_item"><a href="index.jsp">Homepage</a></li>
							<li><a href="loginview.jsp">로그인</a></li>	
							<li><a href="Signup.jsp">회원가입</a></li>
						</ul>
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
						<p id="mainMap"><a href="#"><img src="images/pics02.jpg" alt=""></a></p>
					</section>
					
					
					<!-- Two Column Area(왼쪽 하단. 게시판 부분) -->
					<section>
						<div id="two-column" class="5grid">
							<div class="row">
								<div class="6u">
									<section>
										<h2>Tristique eleifend</h2>
										<p>Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum.</p>
										<ul class="style3">
											<li class="first">
												<p><span class="date"><a href="#">20012.10.25</a></span><span class="heading-title">Etiam posuere augue</span></p>
												<p><a href="#">Integer nisl risus, sagittis convallis, rutrum id, elementum congue, nibh. Suspendisse dictum porta lectus. </a></p>
											</li>
											<li>
												<p><span class="date"><a href="#">20012.10.25</a></span><span class="heading-title">Etiam posuere augue</span></p>
												<p><a href="#">Quisque dictum. Integer nisl risus, sagittis elementum congue, nibh. Suspendisse dictum porta lectus. </a></p>
											</li>
										</ul>
									</section>
								</div>
								<div class="6u">
									<section>
										<h2>Donec dictum metus</h2>
										<ul class="style4">
											<li class="first">
												<h3>Mauris vulputate dolor sit amet</h3>
												<p><a href="#">Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum.</a></p>
											</li>
											<li>
												<h3>Fusce ultrices fringilla metus</h3>
												<p><a href="#">Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum.</a></p>
											</li>
											<li>
												<h3>Donec dictum metus in sapien</h3>
												<p><a href="#">Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum.</a></p>
											</li>
										</ul>
									</section>
								</div>
							</div>
						</div>					
					</section>			

	
				</div>
	
				<!-- Sidebar Area(오른쪽 콘텐츠들) -->
				<div id="sidebar" class="4u">
				
					<!-- Sidebar Section 1 -->
					<!-- <section id="box1"> -->
						
						<!-- Two Column Content Area -->
						<!-- <div class="5grid">
							<div class="row">
								<div class="6u">
									<section>
										<h2>검색옵션</h2>
										<ul class="style4">
											<li class="first"><a href="#">Maecenas luctus sapien</a></li>
											<li><a href="#">Etiam rhoncus volutpat erat</a></li>
											<li><a href="#">Donec dictum metus sapien</a></li>
											<li><a href="#">Integer gravida quis urna</a></li>
											<li><a href="#">Etiam rhoncus volutpat erat</a></li>
											<li><a href="#">Donec dictum metus sapien</a></li>
										</ul>
									</section>
								</div>
							</div>
						</div> -->
						<!-- Two Column Content Area Ends Here -->
					
					</section>
	
					<!-- Sidebar Section 2 -->
					<div id="sidebox" style="margin-top: 5%">
					<section id="box1">
						<h2 >검색옵션</h2>
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