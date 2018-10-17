<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<title>Signup.jsp</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

<link rel="stylesheet" href="./css/Signup.css">

</head>

<body>

	<form action="MemberControl" method="post">

		<h1>Join to us</h1>

		<fieldset>
			<legend>
				<span class="number">1</span>기본정보
			</legend>

			<label for="user_id">ID:</label> 
				<input type="text" id="id"
				name="id" required value="User_ID"
				onBlur="if(this.value=='')this.value='User_ID'"
				onFocus="if(this.value=='User_ID')this.value='' "> 
				<tr>
				<td>
				<!-- id체크 -->
				
				<p id="idcheck" style="font-size: 10px"></p>
				<input type="button" id="btn" required value="id확인">
				</td>
				</tr>				
				<label for="password">password:</label> <input
				type="password" id="pwd" name="pwd" 
				required value="Password" onBlur="if(this.value=='')this.value='Password'"
				onFocus="if(this.value=='Password')this.value='' "> 
				
				<label
				for="name">Name:</label> <input type="name" id="name"
				name="name" required value="Name"
				onBlur="if(this.value=='')this.value='Name'"
				onFocus="if(this.value=='Name')this.value='' "> 
				
				<label
				for="mail">Email:</label> <input type="email" id="email"
				name="email" required  value="E-mail"
				onBlur="if(this.value=='')this.value='E-mail'"
				onFocus="if(this.value=='E-mail')this.value='' "> 
				
				<label
				for="phone">Phone:</label> <input type="text" id="phone"
				name="phone" required  value="Phone"
				onBlur="if(this.value=='')this.value='Phone'"
				onFocus="if(this.value=='Phone')this.value='' ">
			
	
			<!-- 	<form action="MemberControl">
				<input type="submit" name="command" id = "SignupAf" value="회원가입">
				<br>
			 	 -->
					
	
	 
			 <button type="submit" name = "command" id = "SignupAf" value = "회원가입">회원가입</button> 
			 <br>
			 			</form>	
		<!--  id체크   -->
			<script type="text/javascript">
					$(function () {	
						$("#btn").click(function () {	
						
							$.ajax({
								type:"get",
								url:"./idcheck.jsp",
								data:"id=" + $('#id').val(),
								success:function(data){				
									if(data.trim() == "OK"){
										$("#idcheck").css("color", "#0000ff");
										$("#idcheck").html("사용할 수 있는 id입니다");
									}else{
										$("#idcheck").css("color", "#0000ff");
										$("#idcheck").html("사용 중인 id입니다");		
										$("#id").val("");
									}
								}			
							});
							
						});
						
					});
					</script>


</body>
</html>




