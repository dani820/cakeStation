<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" href="/css/ctMyPageSidebar.css">
<title>Insert title here</title>
</head>
<body>
	<!-- 사이드바 -->
	<aside class="side-bar">
		<ul>
			<li class="aside1">회원 정보 관리</li>
			<li class="aside2"><a href="/customer/info">회원정보수정</a></li>
			<li class="aside2" style="color: #6E6E6E"><a
				href="/customer/deleteCustomer.jsp">회원탈퇴</a></li>
			<li class="aside1">주문 내역 관리</li>
			<li class="aside2"><a href="/customer/orderList">주문내역 관리</a></li>
		</ul>
	</aside>
</body>
</html>