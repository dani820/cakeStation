<%@page import="board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Board> bList = (ArrayList<Board>)request.getAttribute("bList");
	String pageNavi = (String)request.getAttribute("pageNavi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/boardPage.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<main>
		<h1>문의</h1>
		<table border="1">
			<tr>
				<th>글번호</th><th>글제목</th>
				<th>글쓴이</th><th>작성일</th>
			</tr>
			<% for(Board board : bList)  { %>
			<tr>
				<td><%= board.getCsNo() %></td>
				<td><a href="/board/detail?CS_NO=<%= board.getCsNo()%>">
					<%= board.getCsTitle() %></a>
				</td>
				<td><%= board.getCsId() %></td>
				<td><%= board.getCsDate() %></td>
			</tr>
			<% } %>
			<tr>
				<td colspan="3" align="center">
					<form action="/board/search" method="get">
						<input type="text" name="searchKeyword">
						<input type="submit" value="검색">
					</form>
				</td>
				<td>
					<form action="/board/write" method="get">
						<input type="submit" value="글쓰기">
					</form>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<%= pageNavi %>
				</td>
			</tr>
		</table>
	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
</html>