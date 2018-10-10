<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="ko">
<head>
<meta charset="UTF-8" />
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>index.jsp</title>

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="//cdn.poesis.kr/post/popup.min.js" charset="UTF-8"></script>
<link rel="stylesheet" type="text/css" href="first_search.css">

</head>
<body>

<!--
 *  Postcodify - 도로명주소 우편번호 검색 프로그램 (코딩 예제)
 * 
 *  Copyright (c) 2014-2015, Poesis <root@poesis.kr>
 * 
 *  이 프로그램은 자유 소프트웨어입니다. 이 소프트웨어의 피양도자는 자유
 *  소프트웨어 재단이 공표한 GNU 약소 일반 공중 사용 허가서 (GNU LGPL) 제3판
 *  또는 그 이후의 판을 임의로 선택하여, 그 규정에 따라 이 프로그램을
 *  개작하거나 재배포할 수 있습니다.
 * 
 *  이 프로그램은 유용하게 사용될 수 있으리라는 희망에서 배포되고 있지만,
 *  특정한 목적에 맞는 적합성 여부나 판매용으로 사용할 수 있으리라는 묵시적인
 *  보증을 포함한 어떠한 형태의 보증도 제공하지 않습니다. 보다 자세한 사항에
 *  대해서는 GNU 약소 일반 공중 사용 허가서를 참고하시기 바랍니다.
 * 
 *  GNU 약소 일반 공중 사용 허가서는 이 프로그램과 함께 제공됩니다.
 *  만약 허가서가 누락되어 있다면 자유 소프트웨어 재단으로 문의하시기 바랍니다.
 *
 *-->


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
    <form  class="flex-form">
      <label for="from">
        <i class="ion-location"></i>
      </label>
      <input type="search" placeholder="Where do you want to go?">
      <input type="submit" value="Search" id="search_button">
      
    </form>
    <div id="madeby">
      <span>
        Photo by <a href="http://www.iei.or.kr/main/main.kh" target="_blank">KH CLASS_3 semi project 6조</a>
      </span>
    </div>
  </div>

</div>


<script type="text/javascript">
    $("#search_button").postcodifyPopUp();
</script>


</body>
</html>