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

<form action="profileeditaf.jsp">
<section>
  <!--for demo wrap-->
  <h1>������ ����</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>����</th>
          <th>��</th>	
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <tr>
          <td>ȸ����ȣ</td>
          <td>code</td>
        </tr>
        <tr>
          <td>�̸�</td>
          <td><input type="text" name="name" size="20" value="name"></td>
        </tr>
        <tr>
          <td>�̸���</td>
          <td><input type="text" name="email" size="20" value="email"></td>
        </tr>
        <tr>
          <td>��ȭ��ȣ</td>
          <td><input type="text" name="phone" size="20" value="phone"></td>
        </tr>
        <tr>
          <td>������Ʈ</td>
          <td>blacklist</td>
        </tr>
        <tr>
          <td>����</td>
          <td>auth</td>
        </tr>
       
      </tbody>
    </table>
  </div>

<input type="submit" value="����">
<a href="mypage.jsp">���</a>
</section>
</form>






</body>
</html>