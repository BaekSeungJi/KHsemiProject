<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
//1: 기존의 세션 데이터를 모두 삭제
session.invalidate();
%>

<script type="text/javascript">
alert("안녕히 가십시오");
//2: 초기 index 페이지로 이동시킴.
location.href = "index.jsp";
</script>

</body>
</html>