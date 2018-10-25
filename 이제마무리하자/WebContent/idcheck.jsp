<%@page import="model.member.MemberManager"%>
<%@page import="model.member.iMemberManager"%>
<%@page import="model.member.MemberService"%>
<%
String id = request.getParameter("id");
System.out.println("Id = " + id);

%>
<%

MemberService dao = MemberService.getInstance();

boolean isDup = dao.manager.getId(id);


if(isDup){
	out.print("NO");

}else{	
	out.print("OK");

}
%>

