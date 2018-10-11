<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오늘의 숙소</title>

<!-- <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> -->

<link rel="stylesheet" type="text/css" href="first_search.css">

</head>
<body>


<div class="container">

  <header>
    <h2><a href="#"><i class="ion-plane"></i> 오늘의 숙소</a></h2>
    <nav>
      <ul>
        <li>
          <a class="btn" href="#" title="Register">Register</a>
        </li>
        <li>
          <a class="btn" href="#" title="Login">Login</a>
        </li>
      </ul>
    </nav>
  </header>

  <div class="cover">
    <h1>Discover your house of today.</h1>
    <form  action="index1.jsp">
      <label for="from">
        <i class="ion-location"></i>
      </label>
      <input type="search" placeholder="Where do you want to go?" name="keyword">
      <input type="submit" value="Search">
            
    </form>
    <div id="madeby">
      <span>
        Photo by <a href="http://www.iei.or.kr/main/main.kh" target="_blank">KH CLASS_3 semi project 6조</a>
      </span>
    </div>
  </div>

</div>



</body>
</html>