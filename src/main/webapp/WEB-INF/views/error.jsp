<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!-- 에러 발생시 /home 으로 이동 -->	
<!DOCTYPE html>
<html>
<script type="text/javascript">
function errAlert(){
	window.location.href="/home";
	alert("올바른 형식이 아닙니다.");
}
</script>
<head>
<script>errAlert();</script>
</head>
<body>
</body>
</html>