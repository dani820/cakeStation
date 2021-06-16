<%@page import="board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Board> bList = (ArrayList<Board>)request.getAttribute("bList");
	String pageNavi = (String)request.getAttribute("pageNavi");
	int currentPage = (int)request.getAttribute("currentPage");		
	int totalCount = (int)request.getAttribute("totalCount");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터 문의하기</title>
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
<h2 class="text-center">고객센터 </h2><p>&nbsp;</p>
	
	
	<div class="container">
	<div class="row">
	<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
		<tr>
			<th style="background-color: #eeeeee; text-align: center;">글번호</th>
			<th style="background-color: #eeeeee; text-align: center;">글제목</th>
			<th style="background-color: #eeeeee; text-align: center;">글쓴이</th>
			<th style="background-color: #eeeeee; text-align: center;">작성일</th>
		</tr>
			<% int count = totalCount; %>
			<% for(int i = bList.size(),j=0; i > 0; i--, j++)  { %>
			<tr>
				<td><%=(count--) - (currentPage-1)*10%></td>
				<td><a href="/board/detail?CS_NO=<%= bList.get(j).getCsNo()%>">
					<%=bList.get(j).getCsTitle() %></a>
				</td>
				<td><%=bList.get(j).getCsId() %></td>
				<td><%=bList.get(j).getCsDate() %></td>
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
	
	</div>
	</div>
			<!-- 부트스트랩 참조 영역 -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
	
	
</body>
</html>