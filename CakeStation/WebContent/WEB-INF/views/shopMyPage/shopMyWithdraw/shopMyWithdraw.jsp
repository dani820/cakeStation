<%@page import="shop.model.vo.ShopMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ShopMember sMem = (ShopMember) request.getAttribute("sMem");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/shopWithdraw.css">
<script src="/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script>
	window.onload = function() {

		function checkSubmit() {

			var checkOk = confirm("정말 탈퇴하시겠습니까?");
			return;

		}

		function checkSubmit() {

			var shopPw = document.getElementsByName("shopPw");
			if (shopPw.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;
			}

		}

	}
</script>
<title>마이페이지</title>
</head>
<body>
	<!-- 헤더, 네비 -->
	<%@include file="../../header.jsp"%>

	<!-- 본문 -->
	<main>
		<!-- 사이드바 -->
		<%@include file="/WEB-INF/views/shopMyPageSidebar.jsp"%>
		
		<!-- 본문 내용 -->
		<!-- 회원 탈퇴 -->
		<section id="shop-section">
			<div id=list-title>
				<h2>회원 탈퇴</h2>
			</div>
			<div id="withdraw-con">
				<div id="out-con">
					<form action="/shopMy/MemDelete?withdrawY=Y" method="post"
						onsubmit="return checkSubmit();">
						<div id="out-top">
							<p>아이디</p>
							<input type="text" class="form-control" name="shopId"
								value="<%=sMem.getShopId()%>" readonly><br>
							<p>비밀번호</p>
							<input type="password" class="form-control" name="shopPw">
						</div>
						<div id="buttons">
							<input class="btn btn-primary" type="submit" value="탈퇴 신청"
								onsubmit="return checkSubmitAdd();">
						</div>
					</form>
				</div>
			</div>
		</section>
	</main>
	<!-- 푸터 -->
	<%@include file="../../footer.jsp"%>

</body>
</html>