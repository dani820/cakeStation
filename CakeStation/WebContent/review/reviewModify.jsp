<%@page import="review.model.vo.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Review review = (Review)request.getAttribute("review");
	String cakeName = (String)request.getAttribute("cakeName");
	String shopName = (String)request.getAttribute("shopName");
	String cakeFilePath = (String)request.getAttribute("cakeFilePath");
	int cakeNo = (Integer)request.getAttribute("cakeNo");
	String currShopId = (String)request.getAttribute("currShopId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <link rel="stylesheet" href="/css/reviewPage.css"> -->
<link rel="stylesheet" href="/css/cakeStation.css">
<link rel="stylesheet" href="/css/reviewWrite.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<main>
		<section id ="blank"></section>
		<div id="subject">
		<h1>문의 수정</h1>
		</div>
		<section id="contens">
		<div id="cake-img"><img src="<%=cakeFilePath %>" width=150px height=150px/></div>
		<div id="cake-detail">
		<h2><%=cakeName %></h2><br>
		<p><%=shopName %></p></div>
		<form action="/review/modify" method="post">
			<div id = "score"><select name="REVIEW_SCORE">
					<option value="5">★★★★★
					<option value="4">★★★★☆
					<option value="3">★★★☆☆
					<option value="2">★★☆☆☆
					<option value="1">★☆☆☆☆
			</select></div>
			<br>
			<textarea rows="10" cols="95" placeholder="내용을 입력하세요" name="REVIEW_CONTENT" id="review-text"><%= review.getReviewContent() %></textarea><br><br>
			<div id="text-cnt">(0 / 200)</div>
			<input type="hidden" value="<%= review.getReviewNo() %>" name="REVIEW_NO">
			<input type="hidden" value="<%=cakeNo %>" name="cakeNo">
			<input type="hidden" value=<%=currShopId %> name="currShopId">
			<div id= btn>
			<input type="submit" value="수정완료">
			<input type="reset" value="취소">
			</div>
	</form>
	</main>
		<script>
		var num = 0;
		$("#review-text").on("keyup", function() {
			num++;
			$('#text-cnt').html("(" + $(this).val().length + "/ 200)");
			if($(this).val().length > 200) {
				alert("최대 200자까지 입력가능합니다.");
				$(this).val($("#review-text").val().substring(0,200));
				$('#text-cnt').html("(200/ 200)");
			}
		});
	</script>
	<%@include file ="/WEB-INF//views/footer.jsp" %>
</body>
</html>