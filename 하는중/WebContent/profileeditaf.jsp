<%@page import="model.member.MemberService"%>
<%@page import="dto.MemberDto"%>
<%@page import="model.member.MemberManager"%>
<%@page import="model.member.iMemberManager"%>
<%@page import="control.MemberControl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	request.setCharacterEncoding("utf-8");
%>

<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
%>


<%
	MemberService service = MemberService.getInstance();
	MemberDto dto = (MemberDto) request.getAttribute("dto");
	session.setAttribute("login", dto);
	session.setMaxInactiveInterval(30 * 60);

	Object ologin = session.getAttribute("login");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>profileeditaf.jsp</title>
</head>
<body>

	<%
		boolean isS = service.profileedit(id, pwd, name, email, phone);

		if (isS) {
	%>
	<script type="text/javascript">
	alert("변경 완료!");
	location.href = "MemberControl?command=profileedit&id=<%=id%>";
	 </script>


	<%
		} else {
	%>
	<script type="text/javascript">
	alert("변경 실패!");
	location.href = "MemberControl?command=profileedit&id=<%=id%>";
	</script>
	<%
		}
	%>


	</script>

</body>
</html>






