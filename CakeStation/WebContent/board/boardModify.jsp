<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/boardPage.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<main>
		<h1>문의 수정</h1>
		<form action="/board/modify" method="post">
			<input type="text" value="<%= board.getCsTitle() %>" name="CS_TITLE"><br><br>
			<textarea rows="24" cols="140" name="CS_CONTENTS"><%= board.getCsContents() %></textarea><br><br>
			<input type="hidden" value="<%= board.getCsNo() %>" name="CS_NO">
			<input type="submit" value="수정완료">
			<input type="reset" value="취소">
			
			
		</form>
	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
</html>