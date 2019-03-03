<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="rs.board.domain.BoardVO"%>
<%@ page import="rs.board.domain.PageVO"%>
<%@ page import = "javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RS-BOARD</title>
</head>
<body>
<h1 style="background-color:#FFA500;">게시판</h1>
<button style="float:right;" class="btn btn-warning" onclick="location.href='/setTestData'" >test데이터삽입</button>
<button style="float:right;" class="btn btn-success" onclick="location.href='/insert'" >게시글 작성</button>
<div>
	<table class="table table-hover" style="table-layout:fixed">
        <tr>
            <th width="10%">글번호</th>
            <th width="50%">제목</th>
            <th width="20%">작성자</th>
            <th width="20%">작성날짜</th>
        </tr>
            <c:forEach var="l" items="${list}">
          	  <tr>
                  <td>${l.bbs_no}</td>                                   
                  <td width="100" style="text-overflow:ellipsis; overflow:hidden" onclick="location.href='/detail/${l.bbs_no}'"><nobr>${l.bbs_tit}</td>                  
                  <td width="30" style="text-overflow:ellipsis; overflow:hidden"><nobr>${l.regist_nicknm}</td>
                  <td>${l.regist_dt}</td>
              </tr>
          </c:forEach>
    </table>
</div>
<div class="container" style="display: inline-block;">
<%  PageVO paging=(PageVO) request.getSession().getAttribute("pages");
	int curpage=paging.getCurPageno();
	int startpage=(curpage-1)/10+1;
	int endpage=(startpage)*10;
	int finalpage=paging.getFinalPageno();
	if(endpage>finalpage)
		endpage=finalpage;	%>
<%if(startpage>1){%>
	<form action="/home" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn-light center-block" type="submit" id="pages" name="pages" value=<%=1%>>첫페이지로</button>
	</form>
<%} %>
<%if(startpage>1){%>
	<form action="/home" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn-light center-block" type="submit" id="pages" name="pages" value=<%=(startpage-2)*10+1%>>이전</button>
	</form>
<%} %>
<%for(int i=(startpage-1)*10+1;i<=endpage;i++){ %>
	<form action="/home" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn-light center-block" type="submit" id="pages" name="pages" value=<%=i%>><%=i %></button>
	</form>
<%} %>
<%if(endpage<finalpage){%>
	<form action="/home" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn-light center-block" type="submit" id="pages" name="pages" value=<%=(startpage)*10+1%>>다음</button>
	</form>
<%} %>
<%if(endpage<finalpage){%>
	<form action="/home" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn btn-light center-block" type="submit" id="pages" name="pages" value=<%=finalpage%>>끝페이지로</button>
	</form>
<%} %>
	
	<br>
</div>
</body>
</html>