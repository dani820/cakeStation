<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/shopMyPageSidebar.css">
<title>Insert title here</title>
</head>
<body>
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
			<!-- <a href="/shopMy/registerShopInfo">업체정보등록</a> -->
		</ul>
		<h6>회원 정보 관리</h6>
		<ul class=shop-set>
			<li class="sets"><a href="/shopMy/shopMemInfo">회원 정보 조회</a></li>
		</ul>
		<ul class=shop-set>
			<li class="sets"><a href="/shopMy/MemDelete">회원 탈퇴</a></li>
		</ul>
	</aside>
	
</body>
</html>