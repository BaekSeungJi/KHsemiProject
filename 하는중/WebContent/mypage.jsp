<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">


<link rel="stylesheet" type="text/css" href="css/main.css"> 
<link rel="stylesheet" type="text/css" href="js/main.js">


<title>profileedit</title>
</head>
<body>


<section>
  <!--for demo wrap-->
  <h1>나의 프로필</h1>
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
          <td>name</td>
        </tr>
        <tr>
          <td>이메일</td>
          <td>email</td>
        </tr>
        <tr>
          <td>전화번호</td>
          <td>phone</td>
        </tr>
        <tr>
          <td>블랙리스트</td>
          <td>blacklist</td>
        </tr>
        <tr>
          <td>권한</td>
          <td>auth</td>
        </tr>
       
      </tbody>
    </table>
  </div>
  
  <a href="profileedit.jsp">profileedit.jsp</a>	
  <h1>최근 이용한 호텔</h1>
  <br>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>호텔 이름</th>
          <th>지역</th>
          <th>예약 날짜</th>
          <th>이용 시작 시각</th>
          <th>이용 완료 시각</th>	
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <tr>
          <td><a href="index.jsp">호텔 이름</a></td>
          <td>지역</td>
          <td>날짜</td>
          <td>시각</td>
          <td>시각</td>
        </tr>
        
      <!--   강사님 bbslist 보고 수정! -->
       
      </tbody>
    </table>
  </div>
</section>







</body>
</html>