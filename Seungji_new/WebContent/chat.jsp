<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
String userID = "hotel1";
String toID = request.getParameter("toID");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrapCss/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrapCss/custom.css">
	<title>Ajax 실시간 회원제채팅 서비스</title>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrapJs/bootstrap.js"></script>

</head>
	<script type="text/javascript">
		function autoClosingAlert(selector, delay) {
			var alert = $(selector).alert();
			alert.show();
			window.setTimeOut(function () { alert.hide() }, delay);
		}
		function submitFunction() {
			var fromID = "<%=userID%>";
			var toID = "<%=toID%>";
			var chatContent = $("#chatContent").val();
			$.ajax({
				type : "POST",
				url : "ChatControl",
				data : {
					"command" : "addChat",
					"fromID" : encodeURIComponent(fromID),
					"toID" : encodeURIComponent(toID),
					"chatContent" : encodeURIComponent(chatContent)
				},
				success : function (result) {
					if(result == 1){	// 입력 성공
						autoClosingAlert('#successMessage', 2000);	// successMessage를 2초동안 보여줘라
					}else if(result == -1){	// 입력 실패
						autoClosingAlert('#dangerMessage', 2000);
					}else{	// 데이터베이스 오류 발생
						autoClosingAlert('#warningMessage', 2000);
					}
				}
				
			});
			$("#chatContent").val("");	// 입력창을 비워줌
			
		}
		
		var lastID = 0;
		function chatListFunction( type ) {
			var fromID = '<%=userID%>';
			var toID = '<%=toID%>';
			$.ajax({
				type : "GET",
				url : "ChatControl",
				data : {
					"command" : "chatList",
					"fromID" : encodeURIComponent(fromID),
					"toID" : encodeURIComponent(toID),
					"listType" : type
				},
				success : function (data) {
					if(data == "") return;
					var parsed = JSON.parse(data);
					var result = parsed.result;
					for (var i = 0; i < result.length; i++) {
						if(result[i][0].value == fromID){
							result[i][0].value = "나";
						}
						addChat(result[i][0].value, result[i][2].value, result[i][3].value);
					}
					lastID = Number(parsed.last);	// 가장마지막으로 전달받은 seq
				}
			});
			
		}
		
		// 채팅창에 채팅내역 바로바로 출력
		function addChat(chatName, chatContent, chatTime) {
			$("#chatList").append('<div class="row">' + 
					'<div class="col-lg-12">' +
					'<div class="media">' +
					'<a class="pull-left" href="#">' + 
					'<img class="media-object img-circle" style="width : 30px; height : 30px;" src="css/images/icon.png" alt="">' +
					'<a>' +
					'<div class="media-body">' +
					'<h4 class="media-heading">' +
					chatName +
					'<span class="small pull-right">' +
					chatTime +
					'</span>' +
					'</h4>' +
					'<p>' +
					chatContent +
					'</p>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'<hr>'
			);
			$("#chatList").scrollTop($("#chatList")[0].scrollHeight);
			
		}
		
		// 3초에 한번씩 채팅창 목록 자동업뎃
		function getInfiniteChat() {
			setInterval(function () {
				chatListFunction(lastID);
			}, 3000);
		}
	</script>
<body>

<!-- <nav class="navbar navbar-default">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.jsp">실시간 회원제 채팅 서비스</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="active"><a href="index.jsp">메인</a>
			<li class="active"><a href="box.jsp">메시지함<span id="unread" class="label label-info"></span></a>
		</ul>
	</div>
</nav> -->

<div class="container">
	<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-xs-12">
				<div class="portlet portlet-default">
					<div class="portlet-heading">
						<div class="portlet-title">
							<h4><i class="fa fa-circle text-green"></i>실시간 채팅방</h4>
						</div>
						<div class="clearfix"></div>
					</div>
					<div id="chat" class="panel-collapse collapse in">
						<div id="chatList" class="portlet-body chat-widget" style="overflow-y: auto; width: auto; height: 600px;">
						</div>
						<div class="portlet-footer">
							<!-- <div class="row">
								<div class="form-group col-xs-4">
									<input style="height: 40px;" type="text" id="chatName" class="form-control" placeholder="이름" maxlength="8">
								</div>
							</div> -->
							<div class="row" style="height: 90px;">
								<div class="form-group col-xs-10">
									<textarea style="height: 80px;" id="chatContent" class="form-control" placeholder="메시지를 입력하세요" maxlength="100"></textarea>
								</div>
								<div class="form-group col-xs-2">
									<button type="button" class="btn btn-default pull-right" onclick="submitFunction();">전송</button>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="alert alert-success" id="successMessage" style="display: none;">
	<strong>메시지 전송에 성공했습니다.</strong>
</div>
<div class="alert alert-danger" id="dangerMessage" style="display: none;">
	<strong>이름과 내용을 모두 입력해주세요.</strong>
</div>
<div class="alert alert-warning" id="warningMessage" style="display: none;">
	<strong>데이터베이스 오류가 발생했습니다.</strong>
</div>

<script type="text/javascript">
	$(document).ready(function () {
		chatListFunction('0');
		getInfiniteChat();	// 3초간격으로 계속 가져올수 있도록
	});
</script>
	

</body>
</html>