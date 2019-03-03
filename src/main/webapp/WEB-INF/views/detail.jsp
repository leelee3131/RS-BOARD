<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>	
<%@ page import="rs.board.domain.BoardVO"%>
<%@ page import="rs.board.domain.PageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
<script type="text/javascript">

var pwd="";
function updateBoard(bbs_pwd){
 pwd = document.getElementById("pwd").value;
 if(bbs_pwd==pwd){
 	 return ture;
 }
 else{
	 alert("비밀번호가 다릅니다.");
 	 return false;
 }
}
</script>
</head>
<body>
<h1 style="background-color:#FFA500;">게시글 상세보기</h1>
<div style="display: inline-block; float:right">
<form action="/update/${boardVO.bbs_no}" method="post" onsubmit="return updateBoard('${boardVO.bbs_pwd}')">
	<label for="password">Password</label>
	<input type="text" name="pwd" id = "pwd">
	<button class="btn btn-primary" style="float :right;">수정</button>
</form>
</div>


<div id="div_con" >
	<td>번호 : ${boardVO.bbs_no}</td>
	<td>제목 : ${boardVO.bbs_tit}</td>
	<hr>
	<td>${boardVO.bbs_detail}</td>
	<hr>
	<td>작성날짜 : ${boardVO.regist_dt}</td>
	<hr>
	<td>최종 수정날짜 : ${boardVO.update_dt}</td>
</div>

<div>
<button class="btn btn-primary" style="float :right;" id="pages" onclick="location.href='/home'">목록으로</button>
	
</div>
</body>
</html>