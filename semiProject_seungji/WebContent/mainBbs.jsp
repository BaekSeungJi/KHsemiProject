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
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
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
			// 페이지 실행되자마자, 검색된 맵 불러오기.
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
			$("#btn_search").click(function () {
				
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
				
			});

		</script>
		
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
							<li><a href="login.jsp">로그인</a></li>	
							<li><a href="signUp.jsp">회원가입</a></li>
							<!-- 찬수씨가 로그인/회원가입 만들면 링크이름 맞게 바꾸기 -->
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
						<input type="text" value="<%=keyword%>" id="searchText">
						<button type="button" id="btn_search">검색</button>
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
					<section id="box1">
						<h2>검색옵션</h2>
						<ul class="style2">
							<li class="first">
								<p><img src="images/pics07.jpg" alt=""><input type="text" ></p>
							</li>
							<li>
								<p><img src="images/pics08.jpg" alt=""></p>
									
								
							</li>
							<li>
								<p><img src="images/pics09.jpg" alt="">Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum. </p>
							</li>
							<li>
								<p><img src="images/pics10.jpg" alt="">Donec leo, vivamus fermentum nibh in augue praesent a lacus at urna congue rutrum. </p>
							</li>
						</ul>
						<p><a href="#" class="button"><span>Read More</span></a></p>
					</section>

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