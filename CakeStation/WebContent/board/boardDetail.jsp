<%@page import="board.model.vo.Board"%>
<%@page import="board.model.vo.BoardComment"%>
<%@page import="board.model.vo.BoardCommentData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<BoardComment> bcList = (ArrayList<BoardComment>)request.getAttribute("bcList");
	Board board = (Board)request.getAttribute("board");
	HttpSession session2 = request.getSession();
	String csId = (String)session2.getAttribute("userId");

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/boardPage.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
<div class="row">
<div class="col-xs-2 col-md-2"></div>
<div class="col-xs-8 col-md-8">
<h2 class="text-center">게시글 </h2><p>&nbsp;</p>
<table class="table">
	
	
		<table class="table">
<tr>
<td class="success">글번호</td>
<td><%=board.getCsNo() %></td>
</tr>

<tr>
<td class="success" >작성일</td>
<td><%=board.getCsDate() %></td>
</tr>

<tr>
<td class="success">제목</td>
<td><%=board.getCsTitle() %></td>
</tr>

<tr>
<td class="success">내용</td>
<td><%=board.getCsContents()%></td>
</tr>
</table>
		<% if (board.getCsId().equals(csId)){ %>
		
		
		<input type="button" class="btn btn-warning" value="수정" onclick="location.href='/board/modify?CS_NO=<%= board.getCsNo()%>'">
		
		<input type="button" class="btn btn-primary" value="목록" onclick="location.href='/board/list'">
		<input type="button" class="btn btn-danger" value="삭제" onclick="location.href='/board/delete?CS_NO=<%= board.getCsNo()%>'">
		<% }else{ %>
		<input type="button" class="btn btn-primary" value="목록" onclick="location.href='/board/list'">
		<%} %>
		
		<form action="/board/detail" method="post">
					<input type="hidden" name="file-path" value="<%= board.getCsFilePath() %>">
					<%if(board.getCsFilePath()!=null){ %>
					<div>파일 : <%=board.getCsFileName() %>
					<input type="submit" value="다운로드">
					</div>
					<%} else{%>
					
					<%} %>
		</form>
		
		
		<h3>댓글</h3>
		
		<% for(BoardComment bc : bcList)  { %>
			<tr>
				
				<td>작성자 : <%= bc.getCsReplyWriter() %></td>
				<td>날짜 : <%= bc.getCsReplyDate() %></td>
				<div>
				</div>
				<td><%= bc.getCsReplyContents() %></td>
				<%if(bc.getCsReplyWriter().equals(csId)) {%>
				<a href="/board/deleteComment?CS_REPLY_NO=<%= bc.getCsReplyNo()%>">삭제</a>
				<%} %>
				<div>
				</div>
			</tr>
			<% } %>
			
		
		<h3>댓글작성</h3>
		<form action="/board/writeComment" method="post">
			<input type="hidden" name="CS_NO" value="<%= board.getCsNo()%>">
			<input type="hidden" name="CS_REPLY_WRRITER" value="<%= csId%>">
			<textarea rows="5" cols="100" placeholder="내용을 입력하세요" name="CS_REPLY_CONTENTS"></textarea>
			<input type="submit" value="완료">
			<input type="reset" value="취소">
		</form>
	
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
	
</body>
</html>