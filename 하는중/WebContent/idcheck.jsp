<%@page import="model.member.MemberManager"%>
<%@page import="model.member.iMemberManager"%>
<%@page import="model.member.MemberService" %>
<%
String id = request.getParameter("ID");
System.out.println("ID = " + id);
%>
<%
iMemberManager dao = MemberManager.getInstance();
boolean isS = dao.getId(id);

if(isS){
	out.print("NO");
}else{	
	out.print("OK");
}
%>