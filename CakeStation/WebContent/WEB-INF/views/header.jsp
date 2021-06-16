<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = (String)session.getAttribute("userId");
	String shopId = (String)session.getAttribute("shopId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/headerfooter.css">
</head>
<body>
	<!-- 헤더 -->
	<header>
		<section class="wrapper">		
			<section id="header-user">
				<% if(userId == null && shopId == null) { %>
					<a href="/member/login.jsp">로그인&nbsp;</a>
					<a href="/member/choiceEnroll.html">회원가입&nbsp;</a>
				<% } %>
				<% if(userId != null || shopId != null) { %>
					<% if(userId != null && userId != "") { %>
					<a>♥<%= userId %>♥ 님 환영합니다.  </a>
					<a href="/member/logout">로그아웃</a>
					<% } %>
					<% if(shopId != null && shopId != "") { %>
					<a class="hdlink">♥<%= shopId %>♥ 님 환영합니다.&nbsp;</a>
					<a href="/member/logout" class="hdlink">로그아웃&nbsp;</a>
					<% } %>
				<% } %>
				<a href="/board/list">고객센터</a>
			</section>
			<section id="header-logo">
				<a href="/index.jsp"><img src="/img/cakestation_logo.png" id="logo-img"/></a>
			</section>
		</section>
	</header>
	
	<!-- 네비 -->
	<nav>
		<ul>
			<li><a href="/index.jsp">HOME</a></li>
			<li><a href="/shop/list">매장</a></li>
			<li><a href="/cake/list">케이크</a></li>
			<li><a href="/member/map.html">내 주변 가게 찾기</a></li>
			<% if(userId == null && shopId == null) { %>
				<li><a href="/member/login.jsp">마이페이지</a></li>
			<% } %>
			<% if(userId != null || shopId != null) { %>
				<% if(userId != null && userId != "") { %>
					<% if(userId.equals("admin")) { %>
						<li><a href="/admin/joinList">마이페이지</a></li>
					<% } %>
					<% if(!userId.equals("admin")) { %>
						<li><a href="/customer/info">마이페이지</a></li>
					<% } %>
				<% } %>
				<% if(shopId != null && shopId != "") {%>	
						<li><a href="/shopMy/orderList">마이페이지</a></li>
				<% } %>
			<% } %>
		</ul>
	</nav>
</body>
</html>