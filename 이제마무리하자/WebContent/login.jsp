<%@page import="model.member.iMemberManager"%>
<%@page import="dto.MemberDto"%>
<%@page import="model.member.MemberService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	MemberDto mem = (MemberDto) request.getAttribute("dto");
	System.out.println("check");

	String id = mem.getId();
	String pwd = mem.getPwd();

	/*  int blacklist = mem.getBlacklist();
	 int auth = mem.getAuth(); */

	mem.setId("id");
	mem.setPwd("pwd");
	/* mem.setBlacklist(blacklist);
	mem.setAuth(auth);
	 */
	/*
	String id = request.getParameter("id");
	System.out.println("login확인 id ="+id);
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	*/
%>
<%
	MemberService dao = MemberService.getInstance();
	mem = dao.manager.login(new MemberDto(id, pwd, null, null, null, 0, 0));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login.jsp</title>
</head>
<body>

	<%
		System.out.println("확인 mem = " + mem);
	%>
	<%
		
	%>
	<%
		if (mem == null) { // 로그인 실패
	%>
	<script type="text/javascript">
	alert("로그인 실패 ID 및 PASSWORD를 확인하세요");
	location.href = "loginview.jsp";
	</script>
	<%
		} else if (mem != null && !mem.getId().equals("")) { // 로그인 성공
			System.out.println("로그인 성공한 회원의 정보");
			// id, pwd, name, email, phone, blacklist, auth 총 7개 정보를 모두 가져옴.
			System.out.println("아이디 = " + mem.getId());
			System.out.println("패스워드 = " + mem.getPwd());
			System.out.println("이름 = " + mem.getName());
			System.out.println("이메일 = " + mem.getEmail());
			System.out.println("폰번호 = " + mem.getPhone());
			System.out.println("블랙리스트 = " + mem.getBlacklist());
			System.out.println("권한 = " + mem.getAuth());

			// 세션에 회원 정보 저장
			session.setAttribute("login", mem);
			session.setMaxInactiveInterval(30 * 60);
	%>
	<script type="text/javascript">
	alert("안녕하세요 <%=mem.getId()%> 님");
		location.href = "index.jsp";
	</script>
	<%
		} // 블랙리스트 회원도 로그인은 가능. 나중에 이 회원이 예약했을 경우,
			// 관리자가 호텔 예약내역에서 '이 회원은 블랙리스트에 등록된 이력이 있습니다' 라는 경고창정도로 처리해주기로 했었음.
	%>


</body>
</html>



