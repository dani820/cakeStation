<%@page import="review.model.vo.Review"%>
<%@page import="review.model.vo.ReviewComment"%>
<%@page import="review.model.vo.ReviewCommentData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Review> rList = (ArrayList<Review>)request.getAttribute("rList");
	ArrayList<ReviewComment> rcList = (ArrayList<ReviewComment>)request.getAttribute("rcList");
	String pageNavi = (String)request.getAttribute("pageNavi");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>케이크 상세</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/boardPage.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<main>
		<h1>후기</h1>
		<table border="1">
			<tr>
				<th>글번호</th>
			</tr>
			<% for(int i = rList.size(),j=0; i > 0; i--, j++)  { %>
			<tr>
				<td><%= i %></td>
				<td><a href="/review/detail?REVIEW_NO=<%= rList.get(j).getReviewNo()%>">
					<%= rList.get(j).getMemberId() %></a>
				</td>
				<td><%= rList.get(j).getReviewScore() %></td>
				<td><%= rList.get(j).getReviewDate() %></td>
				<td><%= rList.get(j).getReviewContent() %></td>
				
			
	<%-- 		<% for(ReviewComment rc : rcList)  { %>
			<tr>
				
				<td>작성자 : <%= rc.getReplyWriter() %></td>
				<td>날짜 : <%= rc.getReplyDate() %></td>
				<div>
				</div>
				<td><%= rc.getReplyContents() %></td>
				
				<div>
				</div>
			</tr>
			<% } %> --%>
			
			<% } %>
			<tr>
				
				<td>
					<form action="/review/write" method="get">
						<input type="submit" value="후기작성">
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