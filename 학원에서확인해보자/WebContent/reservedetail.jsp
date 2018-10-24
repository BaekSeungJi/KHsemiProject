<%@page import="java.util.Calendar"%>
<%@page import="dto.ReserveDto"%>
<%@page import="dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
request.setCharacterEncoding("utf-8");
%>  

<%
MemberDto memdto = (MemberDto)session.getAttribute("login");

List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("list");

/* List<ReserveDto> list = (List<ReserveDto>)request.getAttribute("reserveList");
 */

 
MemberDto user= (MemberDto)session.getAttribute("login");

String id = memdto.getId();
String hotelname = request.getParameter("hotelname");
int year = Integer.parseInt(request.getParameter("year"));
int month = Integer.parseInt(request.getParameter("month"));
int day = Integer.parseInt(request.getParameter("day"));
 
System.out.println("id : " + id);
System.out.println("hotelname : " +hotelname);
System.out.println("year : " +year);
System.out.println("month : " +month);
System.out.println("day : " +day);
 
 
 
%> 

<%!
 
/* 	public static String updatego(String id, String checkin, String checkout,String hotelname){
		String s = "";
		
		s += String.format("<a href='%s?command=%s&hotelname=%s&year=%d&month=%d&day=%d'>", 
							"ReserveControl","reservedetail",hotelname, year, month, day);
		s += String.format("%2d", day);
		s += "</a>";

		return s;
	} */
%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reservedetail.jsp</title>
</head>
<body>

<form action="MemberControl">
	<input type="hidden" name="command" value="logout.jsp">
	<input type="submit" value="로그아웃"> 
</form>

<%!

public String toDates(String mdate){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
	
	// 201810021607	-> 2018-10-02 16:07 
	String s = mdate.substring(0, 4) + "-"	// yyyy 
			+ mdate.substring(4, 6) + "-"	// MM
			+ mdate.substring(6, 8) + " "	// dd
			+ mdate.substring(8, 10) + ":"	// hh
			+ mdate.substring(10, 12) + ":00";
	
	Timestamp d = Timestamp.valueOf(s);
	
	return sdf.format(d);
}

public String toOne(String msg){	// 08 -> 8
	return msg.charAt(0)=='0'?msg.charAt(1) + "": msg.trim();
}
%>

<div align="center">

<%-- <%= yyyymmdd%> --%>
<h2><%=year %>년 <%=month %>월 <%=day %>일 일정</h2>

<table border="1" style="text-align: center;">
<col width="80"><col width="150"><col width="150"><col width="400"><col width="100"><col width="50">

<tr bgcolor="#FA8072">
<td>아이디</td><td>체크인</td><td>체크아웃</td><td>요청사항</td><td>수정</td><td>삭제</td>
</tr>

<%
for(int i = 0;i < list.size(); i++ ){
	ReserveDto dto = list.get(i);
	
	if(dto.getId().equals(id)){
	%>
	
	<tr bgcolor="#FFFFFF" >	
		<td><%=dto.getId() %></td>
		<td><%=dto.getCheckin()%></td>
		<td><%=dto.getCheckout()%></td>
		<td>
			<%=dto.getRequest() %>		
		</td>
		<td>
			<form action="ReserveControl" method="post">
				<input type="hidden" name="command" value="reserveupdate">
				<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
				<input type="hidden" name="hotelname" value="<%=hotelname %>">
				<input type="hidden" name="id" value="<%=dto.getId() %>">
					
			
				<input type="submit" value="일정수정">
			</form>
		</td>
		<td>
			<form action="reserveupdate.jsp" method="post">
				<input type="hidden" name="command" value="reservedel">
				<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
				
				<input type="hidden" name="id" value="<%=dto.getId() %>">
				<input type="hidden" name="request1 " value="<%=dto.getRequest() %>">
				<input type="hidden" name="year1" value="<%=dto.getCheckin().substring(1, 4) %>">
				<input type="hidden" name="month1" value="<%=dto.getCheckin().substring(5, 6) %>">
				<input type="hidden" name="day1 " value="<%=dto.getCheckin().substring(7,8) %>">
				
				
				<input type="hidden" name="year2" value="<%=dto.getCheckout().substring(1, 4) %>">
				<input type="hidden" name="month2" value="<%=dto.getCheckout().substring(5, 6) %>">
				<input type="hidden" name="day2 " value="<%=dto.getCheckout().substring(7,8) %>">
				
				<input type="hidden" name="checkin " value="<%=dto.getCheckin() %>">
				<input type="hidden" name="checkout " value="<%=dto.getCheckout() %>">
				
				
				<input type="submit" value="일정삭제">
			</form>
		</td>	
	</tr>
	
	<%
	}
}
%>
</table>



</div>
					
		</div>
	</div>


</section>



</body>
</html>



