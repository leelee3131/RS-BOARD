<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<head>
<script>
function deleteBoard(){
	if(confirm("삭제하시겠습니까?"))
		return true;
	else
		return false;
}
</script>
</head>
<body>
<h1 style="background-color:#FFA500;">게시글 수정</h1>
<form action="/delete/${boardVO.bbs_no}" id="deleteForm" name ="deleteForm" method="post" onsubmit="return deleteBoard()">
<button class="btn btn btn-danger" style="float :right;">삭제</button>
</form>
<div class="container">
	<form action="/updateProc" method="post">
		<div class="form-group">
			<label for="bbs_tit">제목</label>
			<input type="text" class="form-control" id="bbs_tit" name="bbs_tit" value="${boardVO.bbs_tit}" >
		</div>
		<div class="form-group">
			<label for="bbs_detail">내용</label>
			<textarea id="bbs_detail" name="bbs_detail" rows="10" >${boardVO.bbs_detail}</textarea>
		<br></br>	
		</div>
		
      	</div> 
		<input type="hidden" name="bbs_no" value="${boardVO.bbs_no}"/>
		<input type="hidden" name="regist_nicknm" value="${boardVO.regist_nicknm}"/>
		<input type="hidden" name="regist_dt" value="${boardVO.regist_dt}"/>
		<input type="hidden" name="bbs_pwd" value="${boardVO.bbs_pwd}"/>
		<button type="submit" class="btn btn-primary">수정</button>
	</form>
	<button type="submit" class="btn btn-secondary" onclick="location.href='/home'">목록으로</button>
</div>
</body>
</html>