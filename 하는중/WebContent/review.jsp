<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>review.jsp</title>


</head>
<body>

<form action="reviewaf.jsp">
<div align="center">
<Br>
<input type="text" value="~호텔" readonly="readonly">
<select id="star">
<option value="1">★☆☆☆☆</option>
<option value="2">★★☆☆☆</option>
<option value="3">★★★☆☆</option>
<option value="4">★★★★☆</option>
<option value="5" selected="selected">★★★★★</option>
</select>
<input type="text" value="후기를 작성해 주세요">
<input type="submit" value="등록">
</div>
</form>

</body>
</html>