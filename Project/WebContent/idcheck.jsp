<%@page import="javax.annotation.ManagedBean"%>
<%@page import="dto.MemberDto"%>
<%@page import="model.member.MemberManager"%>
<%@page import="model.member.iMemberManager"%>
<%@page import="model.member.MemberService" %>
<%
String id = request.getParameter("id");
System.out.println("Id = " + id);
%>
<%

MemberService service = MemberService.getInstance();

boolean isDup = service.isDupId(id);


if(isDup){
	out.print("NO");
}else{	
	out.print("OK");
}


%>