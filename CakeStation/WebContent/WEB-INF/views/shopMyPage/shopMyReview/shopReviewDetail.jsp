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
	/* String csId = (String)session2.getAttribute("userId"); */
	String csId = (String)request.getAttribute("shopId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기</title>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<link rel="stylesheet" type="text/css" href="/css/shopRDetail.css">
<script src="/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<!-- 헤더, 네비 -->
	<%@include file="../../header.jsp"%>
	
	<!-- 본문 -->
	<section id="shop-section">
	<!-- 사이드바 -->
		<aside id="side-bar">
			<h6>주문 내역 관리</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/orderList">예약목록</a></li>
			</ul>	
			<h6>케이크 정보 관리</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/cakeList">케이크목록</a></li>
			</ul>
			<h6>후기 게시판</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/reviewList">후기조회</a></li>
			</ul>
			<h6>업체 정보 관리</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/updateShopInfo">업체 정보 조회</a></li>
			</ul>
			<h6>회원 정보 관리</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/shopMemInfo">회원 정보 조회</a></li>
			</ul>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/MemDelete">회원 탈퇴</a></li>
			</ul>
		</aside>
		
		<!-- 본문 내용 -->
			<!-- 후기 게시판 -->
		<div id="con3" class="set-article">
			<div id=list-title>
				<h2>후기 조회</h2>
			</div>
				<div id="rList-con">
					<div id="review">
					    <div class="line-one">
					        <!-- <div class="line-one writer"><h5>글쓴이</h5></div> -->
					        <div class="line-one writerValue"><%= review.getMemberId() %></div>
					        <div class="line-one scoreValue"><%= review.getReviewScore() %></div>
					        <div class="line-one dateValue"><%= review.getReviewDate() %></div>
					    </div>
					    <div class="line-two reviewText">
					        <%= review.getReviewContent() %>
					    </div>
					    <% if(rcList.isEmpty()) { %>
					    <div class="line-three enrollTitle">
					        댓글달기
					    </div>
					    <div class="line-three enrollReply">
					        <form action="/reviewcomm/write" method="post">
					            <input type="hidden" name="REVIEW_NO" value="<%= review.getReviewNo()%>">
					            <input type="hidden" name="REPLY_WRITER" value="<%= csId%>">
					            <textarea placeholder="내용을 입력하세요" id="reply-con" name="REPLY_CONTENTS"></textarea><br>
					            <input class="btn btn-primary" type="submit" value="등록">&nbsp;&nbsp;
					            <input class="btn btn-danger" type="reset" value="취소">
					        </form>
					    </div>
					    <% } else { %>
						    <% for(ReviewComment rc : rcList)  { %>
						    	<div class="line-four reply">
								    <div class="line-four replyTitle">
								        내 댓글
								    </div>
								    <div>
								    </div>
						            <div class="line-four prevReplyDValue">
							           <%= rc.getReplyDate() %>
							        </div>
						        </div>
						        <div class="line-four prevReply">
						            <div class="line-four prevReplyCon">
						            	<%= rc.getReplyContents() %>
					            	</div>
						           		<a class="btn btn-danger" href="/reviewcomm/delete?REPLY_NO=<%= rc.getReplyNo()%>&REVIEW_NO=<%= review.getReviewNo()%>" role="button">삭제</a>
					            </div>
				        	<%} %>
					    <%} %>
			        </div>
					</div>
				</div>
			</div>
				
	</section>	
	
	
	<%@include file="../../footer.jsp"%>
	
</body>
</html>