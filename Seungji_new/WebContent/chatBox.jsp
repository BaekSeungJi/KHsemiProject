<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
Object ologio = session.getAttribute("login");
MemberDto mem = null;
if(ologio != null){   
   mem = (MemberDto)ologio;
}
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrapCss/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrapCss/custom.css">
	<title>Ajax 실시간 회원제 채팅 서비스 메시지함</title>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrapJs/bootstrap.js"></script>
</head>

	<script type="text/javascript">
		function chatBoxFunction() {
			var userID = "hotel1";
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
							result[i][0].value == result[i][1].value;
						} else {
							result[i][1].value == result[i][0].value;
						}
						addBox(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value)
					}
				}
			});
		}
		
		function addBox(lastID, toID, chatContent, chatTime) {
			$("#boxTable").append(
					'<tr onclick="location.href=\'chat.jsp?toID=' + encodeURIComponent(toID) + '\'">' +
					'<td style="width : 150px;"><h5>' + lastID + '</h5></td>' +
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