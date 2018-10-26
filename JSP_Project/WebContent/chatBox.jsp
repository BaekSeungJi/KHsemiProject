<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin != null){
	mem = (MemberDto)ologin;
}
String userID = mem.getId();
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrapCss/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrapCss/custom.css">
	<title>JSP Ajax 실시간 회원제 채팅 서비스 메시지함</title>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrapJs/bootstrap.js"></script>
</head>

	<script type="text/javascript">
		function chatBoxFunction() {
			var userID = "<%=userID%>";
			$.ajax({
				type : "POST",
				url : "ChatControl",
				data : {
					command : "chatBox",
					userID : encodeURIComponent(userID)
				},
				success : function (data) {
					if(data == "" ) return;
					$("#boxTable").html('');
					var parsed = JSON.parse(data);
					var result = parsed.result;
					for(var i=0; i < result.length; i++){
						if(result[i][0].value == userID){
							result[i][0].value = result[i][1].value;
						} else {
							result[i][1].value = result[i][0].value;
						}
						addBox(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value)
					}
				}
			});
		}
		
		function addBox(lastID, toID, chatContent, chatTime) {
			console.log("addBox toId = " + toID);
			$("#boxTable").append(
					'<tr onclick="location.href=\'chat.jsp?toID=' + encodeURIComponent(toID) + '\'">' +
					'<td style="width : 150px;"><h5>' + toID + '</h5></td>' +
					'<td>' +
					'<h5>' + chatContent + '</h5>' +
					'<div class="pull-right">' + chatTime + '</div>' +
					'</td>' +
					'</tr>'
					);
		}
		function getInfiniteBox() {
			setInterval(function () {
				chatBoxFunction();
			}, 3000);
		}
	</script>
	
<body>


<nav class="navbar navbar-default">
	<div class="navbar-header">
			<a class="navbar-brand" href="#">실시간 회원제 채팅 서비스</a>
	</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">Home</a>
				<li class="active"><a href="chatBox.jsp">메시지함</a>
				<li><a href="mypage.jsp">마이페이지</a>
				<li><a href="logout.jsp">로그아웃</a>
			</ul>
			
		</div>
</nav>



<div class="container">
	<table class="table" style="margin:  0 auto;">
		<thead>
			<tr>
				<th><h4>주고받은 메시지 목록</h4></th>
			</tr>
		</thead>
		<div style="overflow-y: auto; width: 100%; max-height: 450px;">
			<table class="table table-bordered table-hover" style="text-aligh: center; border: 1px solid #dddddd; margin: 0 auto;">
				<tbody id="boxTable">
				</tbody>
			</table>
		</div>
	</table>
</div>


<script type="text/javascript">
$(document).ready(function () {
	chatBoxFunction();
	getInfiniteBox();
});
</script>

</body>
</html>