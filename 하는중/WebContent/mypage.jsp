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
  <h1>���� ������</h1>
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
          <td>code</td> <!-- �α������� loginid.id ������ �޾ƿ��� -->
        </tr>
        <tr>
          <td>�̸�</td>
          <td>name</td>
        </tr>
        <tr>
          <td>�̸���</td>
          <td>email</td>
        </tr>
        <tr>
          <td>��ȭ��ȣ</td>
          <td>phone</td>
        </tr>
        <tr>
          <td>������Ʈ</td>
          <td>blacklist</td> <!-- 1�Ͻ� �Ϲ�, 2�Ͻ� ����ȸ�� -->
        </tr>
        <tr>
          <td>����</td>
          <td>1</td> <!-- 1�Ͻ� �Ϲ�, 2�Ͻ� ������, 3�Ͻ� ��� -->
        </tr>
       
      </tbody>
    </table>
  </div>
  
  <a href="profileedit.jsp">profileedit.jsp</a>	
  <h1>�ֱ� �̿��� ȣ��</h1>
  <br>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>ȣ�� �̸�</th>
          <th>����</th>
          <th>���� ��¥</th>
          <th>�̿� ���� �ð�</th>
          <th>�̿� �Ϸ� �ð�</th>	
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <tr><!-- bbslist �����ϱ� -->
          <td><a href="index.jsp">ȣ�� �̸�</a></td>
          <td>����</td>
          <td>��¥</td>
          <td>�ð�</td>
          <td>�ð�</td>
        </tr>
        
      <!--   ����� bbslist ���� ����! -->
       
      </tbody>
    </table>
  </div>
</section>







</body>
</html>