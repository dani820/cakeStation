<%@page import="shop.model.vo.ShopMember"%>
<%@page import="cakeinfo.model.vo.CakeInfo"%>
<%@page import="order.model.vo.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Order> orderList = (ArrayList<Order>)request.getAttribute("orderList");
	String pageNavi = (String)request.getAttribute("pageNavi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/shopMyMain.css">
<script src="/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>마이페이지</title>
</head>

<body>
	<!-- 헤더, 네비 -->
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<!-- 본문 -->
	<main>
		<!-- 사이드바 -->
		<%@include file ="/WEB-INF/views/shopMyPageSidebar.jsp" %>
		
		<section id="shop-section">
			<!-- 본문 내용 -->
			<!-- 예약목록 -->
			<div id="con1" class="set-article">
				<div id=list-title>
					<h2>예약 목록</h2>
				</div>
				<div id="oList-con">
					<% if(orderList.isEmpty()) { %>
				<h3>예약 목록이 없습니다.</h3>					
				<% }else { %>
					<% for(Order order : orderList) { %>
						<div class="orders">
							<div class="order-date">
								<h5>주문일자&nbsp;&nbsp;&nbsp;&nbsp;<%= order.getOrderDate() %></h5>
							</div>
							<div class="olist-left">
								<div class="olist-left-top">
									<img src="<%= order.getCakeFilePath() %>" class="img-thumbnail" alt="cake_thumbnail" width="250" height="250">
								</div>
								<div class="olist-left-down text-center">
									<a class="btn btn-danger" href="/order/cancel?orderNo=<%= order.getOrderNo() %>" role="button">주문취소</a>
								</div>
							</div>
							<div class="olist-right">
								<span><b>주문번호</b>&nbsp;&nbsp;&nbsp;&nbsp;<%= order.getOrderNo() %></span><br>
								<span><b>케이크명</b>&nbsp;&nbsp;&nbsp;&nbsp;<%=	order.getCakeName() %></span><br>
								<span><b>케이크가격</b>&nbsp;&nbsp;&nbsp;&nbsp;<%= order.getCakePrice() %></span><br>
								<span><b>주문자명</b>&nbsp;&nbsp;&nbsp;&nbsp;<%= order.getOrdererName() %></span><br>
								<span><b>연락처</b>&nbsp;&nbsp;&nbsp;&nbsp;<%= order.getOrdererPhone() %></span><br>
								<%	if(order.getPayType().equals("C")) { %>
									<span><b>결제방식</b> &nbsp;&nbsp;&nbsp;&nbsp;카드 결제</span><br>
								<%	} else { %>
									<span><b>결제방식</b>&nbsp;&nbsp;&nbsp;&nbsp;계좌 이체</span><br>
								<%	} %>
								<%	if(order.getDeliveryType().equals("배송")) { %>
									<span><b>배송지</b>&nbsp;&nbsp;&nbsp;&nbsp;<%= order.getDeliveryAddr() %></span><br>
								<% }else { %>
									<span><b>픽업일</b>&nbsp;&nbsp;&nbsp;&nbsp;<%= order.getDeliveryDate() %>&nbsp;&nbsp;&nbsp;&nbsp;<%= order.getPickupTime() %></span><br>
								<% } %>
								<br>
								<span><b>요청사항</b></span><br>
								<% if(order.getRequest() == null) { %>
									<p> 없음 </p>
								<% } else { %>
									<input type="text" value="<%= order.getRequest() %>" readonly>
								<% } %>
							</div>
						</div>
						<br><br>
						<% } %>
	 				<div class="div-order pNavi">
						<span><%= pageNavi %></span>
					</div>
					<% } %>
				</div>
			</div>
		</section>
	</main>
	
 	<!-- 푸터 -->
	<%@include file ="/WEB-INF/views/footer.jsp" %>
	
	
</body>
</html>