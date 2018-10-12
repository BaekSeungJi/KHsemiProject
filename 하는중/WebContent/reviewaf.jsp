<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%
String hotelname = request.getParameter("name");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
%>      
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reviewaf.jsp</title>


</head>
<body>


<script type="text/javascript">
alert("jsp:include 로 구현하자");

location.href = 'review.jsp';
</script>

</body>
</html>