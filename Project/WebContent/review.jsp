<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/review.css" type="text/css" />
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>



</head>
<body>


	<!-- 아직 다른 뷰와 연결을 안해서 이 로그인여부 확인 부분은 나중에 병합하면서 주석풀고 적용하기. -->

	<!-- 로그인여부 확인 / 로그인한 사람의 정보 가져오기. -->
	<%--
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){	// 로그인 정보가 안넘어왔을때. 혹은 기간이 만료했을때(로그인하고 한참 지남)
	--%>
	<!-- <script type="text/javascript">
	alert("로그인해 주십시오");
	location.href="loginview.jsp";
	</script> -->
	<%--
	return;
}

mem = (MemberDto)ologin;
--%>


	<%
		// 마이페이지 예약내역에서 여기에 넘겨줘야할 값.(seq, hotelname, checkin, checkout 총 4개 값 넘겨받기.)
		String seq = request.getParameter("seq");
	%>

	<!-- 리뷰페이지 : 마이페이지 예약내역에서 '리뷰쓰기'버튼을 클릭하면 여기로 들어온다. -->

	<div id="container">
		<h1>&bull; 리뷰를 남겨주세요 &bull;</h1>
		<div class="underline"></div>
		<div class="icon_wrapper">
			<svg class="icon" viewBox="0 0 145.192 145.192"> <path
				d="M126.82,32.694c-2.804,0-5.08,2.273-5.08,5.075v2.721c-1.462,0-2.646,1.185-2.646,2.647v1.995    c0,1.585,1.286,2.873,2.874,2.873h20.577c1.462,0,2.646-1.185,2.646-2.647v-3.041c0-1.009-0.816-1.825-1.823-1.825v-2.722    c0-2.802-2.276-5.075-5.079-5.075h-1.985v-3.829c0-3.816-3.095-6.912-6.913-6.912h-0.589h-20.45c0-2.67-2.164-4.835-4.833-4.835    H56.843c-2.67,0-4.835,2.165-4.835,4.835H34.356v-3.384h-9.563v3.384v1.178h-7.061v1.416c-2.67,0.27-10.17,1.424-13.882,5.972    c-1.773,2.17-2.44,4.791-1.983,7.793c0.463,3.043,1.271,6.346,2.128,9.841c2.354,9.616,5.024,20.515,0.549,28.077    C2.647,79.44-3.125,90.589,2.201,99.547c4.123,6.935,13.701,10.44,28.5,10.44c1.186,0,2.405-0.023,3.658-0.068v9.028h-0.296    c-2.516,0-4.558,2.039-4.558,4.558v4.566h100.04v-4.564c0-2.519-2.039-4.558-4.558-4.558h-0.297V84.631h0.297    c2.519,0,4.558-2.037,4.558-4.556v-0.009c0-2.516-2.039-4.556-4.556-4.556l-36.786-0.009V61.973c0-2.193-1.777-3.971-3.972-3.971    v-4.711h0.456c1.629,0,2.952-1.32,2.952-2.949h14.227V34.459h1.658c2.672,0,4.834-2.165,4.834-4.834h20.45v3.069H126.82z     M34.06,75.511c-2.518,0-4.558,2.04-4.558,4.556v0.009c0,2.519,2.042,4.556,4.558,4.556h0.296v24.12l-0.042-1.168    c-15.994,0.574-26.122-2.523-30.106-9.229C-0.464,90.5,4.822,80.347,6.55,77.423c4.964-8.382,2.173-19.774-0.29-29.825    c-0.843-3.442-1.639-6.696-2.088-9.638c-0.354-2.35,0.129-4.3,1.484-5.958c3.029-3.714,9.509-4.805,12.076-5.1v1.233h7.061v1.49    v2.684c-2.403,1.114-4.153,2.997-4.676,5.237H18.15c-0.584,0-1.056,0.474-1.056,1.056v0.83c0,0.584,0.475,1.056,1.056,1.056h1.984    c0.561,2.18,2.304,3.999,4.658,5.092v0.029c0,0-2.282,20.823,16.479,22.099v1.102c0,1.177,0.955,2.133,2.133,2.133h3.297    c1.178,0,2.133-0.956,2.133-2.133V50.135c0-1.177-0.955-2.132-2.133-2.132h-3.297c-1.178,0-2.133,0.955-2.133,2.132    c-1.575-0.235-5.532-1.17-6.635-4.547c2.36-1.092,4.109-2.913,4.669-5.097h1.308c0.722,0,1.309-0.584,1.309-1.308v-0.578    c0-0.584-0.475-1.056-1.056-1.056h-1.539c-0.542-2.332-2.416-4.271-4.968-5.363v-2.559h17.651c0,2.67,2.166,4.835,4.836,4.835 h2.392v15.88h13.639c0,1.629,1.321,2.949,2.951,2.949h0.899v4.711c-2.194,0-3.972,1.778-3.972,3.971v13.529L34.06,75.511z     M95.188,101.78c0,8.655-7.012,15.665-15.664,15.665c-8.653,0-15.667-7.01-15.667-15.665c0-8.647,7.014-15.664,15.667-15.664    C88.177,86.116,95.188,93.132,95.188,101.78z M97.189,45.669h-9.556c0-0.896-0.726-1.62-1.619-1.62H74.494    c-0.896,0-1.621,0.727-1.621,1.62h-8.967v-11.21h33.283V45.669z"></path>
			<path
				d="M70.865,101.78c0,4.774,3.886,8.657,8.66,8.657c4.774,0,8.657-3.883,8.657-8.657c0-4.773-3.883-8.656-8.657-8.656    C74.751,93.124,70.865,97.006,70.865,101.78z"></path>
			</svg>
		</div>
		<form action="ReviewControl" method="post" id="contact_form">
			<input type="hidden" value="addReview" name="command"> <input
				type="hidden" value="<%=seq%>" name="seq">
			<!-- 리뷰에 input할때 num을 이 seq로 설정해야함. 꼭필요! -->
			<div class="name">
				<label for="name"></label> <input type="text" name="name"
					value="mem.getId()" readonly="readonly">
			</div>
			<div class="email">
				<label for="email"></label> <input type="email" name="date"
					value="숙박일 - 마이페이지에서 넘겨받자" readonly="readonly">
			</div>
			<div class="telephone">
				<label for="name"></label> <input type="text" name="hotel"
					value="호텔이름 - 마이페이지에서 넘겨받자" readonly="readonly">
			</div>
			<div class="telephone">
				<label for="name"></label> <input type="text"
					placeholder="제목을 입력해주세요" name="title" id="title_input"
					maxlength="30" required>
			</div>
			<div class="subject">
				<label for="score">별점</label> <select
					placeholder="Select our hotel's score" name="score"
					id="score_input" required>
					<option disabled hidden selected>Subject line</option>
					<option value="1">★☆☆☆☆</option>
					<option value="2">★★☆☆☆</option>
					<option value="3">★★★☆☆</option>
					<option value="4">★★★★☆</option>
					<option value="5" selected="selected">★★★★★</option>
				</select>
			</div>
			<div class="message">
				<label for="message"></label>
				<textarea name="message" placeholder="I'd like to chat about"
					id="message_input" cols="30" rows="5" required></textarea>
			</div>
			<div class="submit">
				<input type="button" value="Add Review" id="send_button" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="Back" id="back_button"
					style="margin-left: 70%" />
			</div>
		</form>
		<!-- // End form -->
	</div>
	<!-- // End #container -->

	<script type="text/javascript">
		$(document).ready(
				function() {

					$("#send_button").click(
							function() {
								var title = $("#title_input").val();
								var score = $("#score_input").val();
								var content = $("#message_input").val();

								//alert("title = " + title + " score = " + score + " content = " + content);

								if (title == null || title == ""
										|| score == null || score == ""
										|| content == null || content == "") {
									alert("빈칸이 있습니다. 모든 칸을 전부 입력해주세요.");
								} else {
									$("#contact_form").submit();
								}
							});

					$("#back_button").click(function() {
						alert("뒤로가기");
						location.href = "mypage.jsp";
					});

				});
	</script>

</body>
</html>