<%@page import="dto.MemberDto"%>
<%@page import="model.member.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SuchidAf.jsp</title>
</head>
<body>
	<%
		System.out.println("아이디찾기AF뷰 완료");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		int blacklist = Integer.parseInt("0");
		int auth = Integer.parseInt("3");
	%>
	<%
		MemberService service = MemberService.getInstance();
		MemberDto mem = service.manager.suchid(new MemberDto(id, null, null, email, null, blacklist, auth));
	%>


	<%
		if (mem != null && !mem.getId().equals("")) {
			session.setAttribute("login", mem);
			session.setMaxInactiveInterval(30 * 60);
	%>
	<script type="text/javascript">
	alert("귀하의 ID는 : <%=mem.getId()%>입니다.");
		location.href = "loginview.jsp";
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		alert("E-mail을 확인하세요");
		location.href = "loginview.jsp";
	</script>
	<%
		}
	%>





</body>
</html>