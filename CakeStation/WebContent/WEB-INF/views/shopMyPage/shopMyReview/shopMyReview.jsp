<%@page import="review.model.vo.ReviewComment"%>
<%@page import="review.model.vo.Review"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Review> rList = (ArrayList<Review>) request.getAttribute("rList");
/* ArrayList<ReviewComment> rcList = (ArrayList<ReviewComment>)request.getAttribute("rcList"); */
String pageNavi = (String) request.getAttribute("pageNavi");
int totalCount = (int) request.getAttribute("totalCount");
int currentPage = (int) request.getAttribute("currentPage");
//String shopId = (String) request.getAttribute("shopId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/shopMyReview.css">
<script src="/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>마이페이지</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<!-- 헤더, 네비 -->
	<%@include file="../../header.jsp"%>

	<!-- 본문 -->
	<main>
		<!-- 사이드바 -->
		<%@include file="/WEB-INF/views/shopMyPageSidebar.jsp"%>
		<!-- 본문 내용 -->
		<!-- 후기 게시판 -->
		<section id="shop-section">
			<div class="row">
				<div id=list-title>
					<h2>후기 조회</h2>
				</div>
				<!-- <div class="container"> -->
				<div id="rList-con">
					<div class="row">
						<table class="table table-striped"
							style="text-align: center; border: 1px solid #dddddd">
							<tr>
								<th style="background-color: #eeeeee; text-align: center;">글번호</th>
								<th>후기</th>
								<th>작성자</th>
								<th>케익번호</th>
								<th>평점</th>
								<th>날짜</th>
							</tr>
							<%
									for (Review review : rList) {
								%>
							<%-- <% for(int i = rList.size(),j=0; i > 0; i--, j++)  { %> --%>
							<tr>
								<%-- <td><%=  (count--) - (currentPage-1)*10 %></td> --%>
								<%-- <%-- <% for(int i = rList.size(); i > 0; i--) { %> --%>

								<td><%=review.getNumbering()%></td>
								<td><a
									href="/review/detail?REVIEW_NO=<%=review.getReviewNo()%>"><%=review.getReviewContent()%></a></td>
								<td><%=review.getMemberId()%></td>
								<td><%=review.getCakeNo()%></td>
								<td><%=review.getReviewScore()%></td>
								<td><%=review.getReviewDate()%></td>
							</tr>
							<%-- <% } %> --%>
							<%
									}
								%>
							<tr>
								<td colspan="6" align="center"><%=pageNavi%></td>
							</tr>
						</table>
					</div>
		</section>
	</main>
	<!-- 부트스트랩 참조 영역 -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>

	<!-- 푸터 -->
	<%@include file="../../footer.jsp"%>
</body>
</html>