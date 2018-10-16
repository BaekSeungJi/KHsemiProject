

<%@page import="control.MemberControl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
%>
<%
String name = request.getParameter("name");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
%>      
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>profileeditaf.jsp</title>
</head>
<body>
<%-- 
<%
MemberControl memcon = MemberControl.
iMemberDao dao = MemberDao.getInstance();

boolean isS = dao.addMember(new MemberDto(id, pwd, name, email, 0));

if(isS){
%>
	<script type="text/javascript">
	alert("성공적으로 가입하셨습니다");
	location.href = "index.jsp";
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert("다시 가입해 주십시오");
	location.href = "regi.jsp";
	</script>
<%
}
%>

 --%>
 
<%
String str = name + email + phone; 
 
%>

<script type="text/javascript">
alert("수정 구현중");

/* dao.update 이용해서 멤버정보 변경 */

location.href = 'profileedit.jsp';
</script>

</body>
</html>






