<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

   <title>Sign Up</title>
  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="css/Signup.css">

  
</head>
<body>
  <head>
      
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up Form</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>

      <form action="loginview.jsp" method="post">
      
        <h1>Join to us</h1>
        
        <fieldset>
          <legend><span class="number">1</span>기본정보</legend>
        
          <label for="name">ID:</label>
          <input type="text" id="name" name="user_name" required value="UserID" onBlur="if(this.value=='')this.value='UserID'" onFocus="if(this.value=='UserID')this.value='' ">
          <label id="idcheck" style="color: green;" ></label>		
		  <input type="button" id="btn" value="중복확인">
      
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
          
          <label for="password">비밀번호:</label>
          <input type="password" id="password" name="user_password" required value="Password" onBlur="if(this.value=='')this.value='Password'" onFocus="if(this.value=='Password')this.value='' ">
             
          <label for="name">Name:</label>
          <input type="text" id="name" name="user_name" required value="Name" onBlur="if(this.value=='')this.value='Name'" onFocus="if(this.value=='Name')this.value='' ">
          
              
          <label for="mail">Email:</label>
          <input type="email" id="mail" name="user_email" required value="E-mail" onBlur="if(this.value=='')this.value='E-mail'" onFocus="if(this.value=='E-mail')this.value='' ">
          
           <label for="phone">Phone:</label>
          <input type="text" id="phone" name="user_phone" required value="Phone" onBlur="if(this.value=='')this.value='Phone'" onFocus="if(this.value=='Phone')this.value='' ">
          
          
            </form>
        
		<from action="SignupAf.jsp" method="post">
        <button type="submit">회원가입</button>
      </form>
      
    </body>
</html>
  