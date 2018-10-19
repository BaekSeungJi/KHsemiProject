<%@page import="model.member.iMemberManager"%>
<%@page import="dto.MemberDto"%>
<%@page import="model.member.MemberService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%
/* String id = request.getParameter("id");
System.out.println("login확인 id ="+id);
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
int blacklist = Integer.parseInt("0");
int auth = Integer.parseInt("3");
 */
 MemberDto mem = (MemberDto)request.getAttribute("dto");

 String id = mem.getId();
 String pwd = mem.getPwd();
 int blacklist = mem.getBlacklist();
 int auth = mem.getAuth();
 mem.setId("id");
 mem.setPwd("pwd");
 mem.setBlacklist(blacklist);
 mem.setAuth(auth);
 
%>
<% 
/* MemberService dao = MemberService.getInstance();
MemberDto mem = dao.manager.login(new MemberDto(id, pwd, null, null, null, blacklist, auth));
 */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login.jsp</title>
</head>
<body>

<%
System.out.println("확인 mem ="+mem);
%><% 

%>
<%

if(mem != null && !mem.getId().equals("")){
	session.setAttribute("login", mem);
	session.setMaxInactiveInterval(30*60);

%>
	<script type="text/javascript">
	alert("안녕하세요 <%=mem.getId() %> 님");
	location.href = "index.jsp";
	</script>	
<%
}else if(mem.getBlacklist()==1){
	%>
	<script type="text/javascript">
	alert("이 호빗같은 <%=mem.getId() %> 블랙리스트녀석");
	location.href = "index.jsp";
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert("ID나 Password를 확인하세요");
	location.href = "loginview.jsp";
	</script>
<%
}
%>
 
 
</body>
</html>



