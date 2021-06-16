<%@page import="review.model.vo.Review"%>
<%@page import="review.model.vo.ReviewComment"%>
<%@page import="review.model.vo.ReviewCommentData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<ReviewComment> rcList = (ArrayList<ReviewComment>)request.getAttribute("rcList");
	Review review = (Review)request.getAttribute("review");
	HttpSession session2 = request.getSession();
	String csId = (String)session2.getAttribute("userId");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/reviewPage.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<main>
		<h2>후기</h2>
		<h6>글쓴이 : <%= review.getMemberId() %> / 
		작성일 : <%= review.getReviewDate() %></h6>
		<h3>글내용</h3>
		<div>
			<%= review.getReviewContent() %>
		</div>
		<% if (review.getMemberId().equals(csId)){ %>
		
		<a href="/review/modify?REVIEW_NO=<%= review.getReviewNo()%>">수정</a>
		<a href="/shopMy/reviewList">목록</a>
		<a href="/review/delete?REVIEW_NO=<%= review.getReviewNo()%>">삭제</a>
		<% }else{ %>
		<a href="/shopMy/reviewList">목록</a>
		<%} %>
		
		
		
		
		
		<h3>댓글</h3>
		
		<% for(ReviewComment rc : rcList)  { %>
			<tr>
				
				<td>작성자 : <%= rc.getReplyWriter() %></td>
				<td>날짜 : <%= rc.getReplyDate() %></td>
				<div>
				</div>
				<td><%= rc.getReplyContents() %></td>
				<%if(rc.getReplyWriter().equals(csId)) {%>
				
				<a href="/reviewcomm/delete?REPLY_NO=<%= rc.getReplyNo()%>">삭제</a>
				<%} %>
				<div>
				</div>
			</tr>
			<% } %>
			
		
		<h3>댓글작성</h3>
		<form action="/reviewcomm/write" method="post">
			<input type="hidden" name="REVIEW_NO" value="<%= review.getReviewNo()%>">
			<input type="hidden" name="REPLY_WRRITER" value="<%= csId%>">
			<textarea rows="5" cols="100" placeholder="내용을 입력하세요" name="REPLY_CONTENTS"></textarea>
			<input type="submit" value="완료">
			<input type="reset" value="취소">
		</form>
	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
	
</body>
</html>