<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<head>
</head>
<body> 
<h1 style="background-color:#FFA500;">게시글 작성</h1>
<div class="container">
    <form action="/insertProc" name="insertForm" method="post">
      <div class="form-group">
        <label for="bbs_tit">제목</label>
     	<input type="text" class="form-control" id="bbs_tit" name="bbs_tit" placeholder="필수 입력사항입니다.">
    
      </div>
      <div class="form-group">
        <label for="regist_nicknm">작성자</label>
        <input type="text" class="form-control" id="regist_nicknm" name="regist_nicknm" placeholder="필수 입력사항입니다.">
      </div>
      <div class="form-group">
        <label for="regist_nicknm">비밀번호</label>
        <input type="password" class="form-control" id="bbs_pwd" name="bbs_pwd">
      </div>
      <div class="form-group">
        <label for="bbs_detail">내용</label>
     	<textarea id="bbs_detail" name="bbs_detail" rows="10"></textarea>
      </div>
      
      
      </div> 
      <button type="submit" name="btns" class="btn btn-primary" onclick="location.href='/home'">작성</button>  
    </form>
    <button type="submit" name="btns" class="btn btn-secondary" onclick="location.href='/home'">목록으로</button>
</div>
</body>
</html>