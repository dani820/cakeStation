<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/shopMyFail.css">
<!-- <script src="//code.jquery.com/jquery-1.11.0.min.js"></script> -->
<script src="/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>마이페이지</title>
</head>

<body>
	<!-- 헤더, 네비 -->
	<%@include file="/WEB-INF/views/header.jsp"%>

	<!-- 본문 -->
	<main>
		<!-- 사이드바 -->
		<%@include file="/WEB-INF/views/shopMyPageSidebar.jsp"%>

		<!-- 본문 내용 -->
		<!-- 예약목록 -->
		<section id="shop-section">
			<div id=list-title>
				<h2></h2>
			</div>
			<div id="fail">
				<h1>서비스를 요청할 수 없습니다.</h1>
				<h5>다시 이용해주세요.</h5>
			</div>
			</div>
		</section>
	</main>

	<!-- 푸터 -->
	<%@include file="/WEB-INF/views/footer.jsp"%>


</body>
</html>