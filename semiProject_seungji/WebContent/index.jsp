<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index.jsp</title>
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
    <h1>Discover the house of today.</h1>
    <form  class="flex-form">
      <label for="from">
        <i class="ion-location"></i>
      </label>
      <input type="search" placeholder="Where do you want to go?">
      <input type="submit" value="Search">
    </form>
    <div id="madeby">
      <span>
        Photo by <a href="https://unsplash.com/@benblenner" target="_blank">Ben Blennerhassett</a>
      </span>
    </div>
  </div>

</div>

</body>
</html>