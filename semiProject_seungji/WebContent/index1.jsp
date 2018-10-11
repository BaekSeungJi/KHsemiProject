<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="range.css">
</head>
<body>

<label for="range">
      <input type="range" name="range" id="range" min="10000" max="100000" step="5" value="175"/>
</label>
 <output for="range" class="output"></output>
 
 
<script type="text/javascript">
$('#range').on("input", function() {
    $('.output').val(this.value +" 원" );
    }).trigger("change");
</script>

</body>
</html>