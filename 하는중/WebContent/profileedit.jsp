<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   

 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">


<link rel="stylesheet" type="text/css" href="css/main.css"> 
<link rel="stylesheet" type="text/css" href="js/main.js">


<title>profileedit</title>
</head>
<body>

<form method="get" action="profileeditaf.jsp">
<section>
  <!--for demo wrap-->
  <h1>프로필 수정</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>구분</th>
          <th>나</th>	
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <tr>
          <td>회원번호</td>
          <td>code</td>
        </tr>
        <tr>
          <td>이름</td>
          <td><input type="text" name="name" size="20" value="name"></td>
        </tr>
        <tr>
          <td>이메일</td>
          <td><input type="text" name="email" size="20" value="email"></td>
        </tr>
        <tr>
          <td>전화번호</td>
          <td><input type="text" name="phone" size="20" value="phone"></td>
        </tr>
        <tr>
          <td>블랙리스트</td>
          <td>blacklist</td>
        </tr>
        <tr>
          <td>권한</td>
          <td>1</td>
        </tr>
       
      </tbody>
    </table>
  </div>

<input type="submit" value="수정">
<a href="mypage.jsp">취소</a>
</section>
</form>






</body>
</html>