<%@page import="dto.MemberDto"%>
<%@page import="model.member.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SuchpwdAf.jsp</title>
<%
	System.out.println("비밀번호찾기AF뷰 완료");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String phone = request.getParameter("phone");
	int blacklist = Integer.parseInt("0");
	int auth = Integer.parseInt("3");
%>

<%
	MemberService service = MemberService.getInstance();

	MemberDto mem = service.manager.suchpwd(new MemberDto(id, pwd, null, null, phone, blacklist, auth));
%>
</head>
<body>


	<%-- 
<%
if(mem != null && !mem.getPwd().equals("")){
	session.setAttribute("login", mem);
	session.setMaxInactiveInterval(30*60);
%>
 --%>
	<%
		if (mem != null) {
			session.setAttribute("login", mem);
			session.setMaxInactiveInterval(30 * 60);
	%>
	<script type="text/javascript">
	alert("<%=mem.getId()%> 님의 pwd는 :<%=mem.getPwd()%>
		입니다.");
		location.href = "loginview.jsp";
	</script>

	<%
		} else {
	%>
	<script type="text/javascript">
		alert("ID와 연락처를 확인 해 주세요");
		location.href = "Suchpwdview.jsp";
	</script>
	<%
		}
	%>



</body>
</html>