<%@page import="file.model.vo.CRNImg"%>
<%@page import="shop.model.vo.ShopMember"%>
<%@page import="cakeinfo.model.vo.CakeInfo"%>
<%@page import="order.model.vo.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ShopMember sMem = (ShopMember) request.getAttribute("sMember");
	CRNImg cImg = (CRNImg) request.getAttribute("crnImg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/shopMInfo.css">
<script src="/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script>
	$(document).ready(function() {

		shopPw = $('input[name=shopPw]');
		shopPwCheck = $('input[name=shopPwCheck]');
		messagePw = $("#messagePw");
		messagePwC = $("#messagePwC");

		shopPw.on("keyup", function() {

			var pwRegExp = /^[a-zA-Z0-9]{8,12}$/;

			if (!pwRegExp.test(shopPw.val())) {
				messagePw.html("영어 대소문자, 숫자로 8~12자리 입력해주세요.");
			} else {
				messagePw.html("사용 가능한 비밀번호입니다.");
			}
		});

		shopPwCheck.on("keyup", function() {
			if (!(shopPw.val() == shopPwCheck.val())) {
				messagePwC.html("비밀번호와 비밀번호 확인 값은 같아야 합니다");
			} else {
				messagePwC.html("");
			}

		});

		$("form").on("submit", function() {

			if (shopPwCheck.val() == "") {
				alert("비밀번호 확인란을 입력해주세요.");
				return false;
			} else if (!(shopPw.val() == shopPwCheck.val())) {
				alert("비밀번호와 비밀번호 확인 값은 같아야 합니다");
				return false;
			} else if ($("crnFile").val() == "") {
				alert("사진을 등록해주세요");
				return false;
			}
		});

	});
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
		<!-- 회원 정보 조회 -->
		<section id="shop-section">
			<div id=list-title>
				<h2>회원 정보 조회</h2>
			</div>
			<form action="/shopMy/updateMemInfo" method="post"
				enctype="multipart/form-data" id="shop-con">
				<div id="shop-info-con">
					<div id="sMem-Info">
						<p>아이디</p>
						<input type="text" class="form-control"
							value="<%=sMem.getShopId()%>" name="shopId" readonly><br>
						<p>업체명</p>
						<input type="text" class="form-control"
							value="<%=sMem.getShopName()%>" name="shopName"><br>
						<p>대표자명</p>
						<input type="text" class="form-control"
							value="<%=sMem.getCEOName()%>" name="shopCeo"><br>
						<p>비밀번호</p>
						<p id="messagePw" style="color: red;"></p>
						<input type="password" class="form-control"
							value="<%=sMem.getShopPwd()%>" name="shopPw"> <br>
						<p>비밀번호 확인</p>
						<p id="messagePwC" style="color: red;"></p>
						<input type="password" class="form-control" name="shopPwCheck"><br>
						<p>업체 주소</p>
						<select name="shopL">
							<option value="서울특별시"
								<%=sMem.getShopLAdd().equals("서울특별시") ? " selected" : ""%>>서울특별시</option>
							<option value="부산광역시"
								<%=sMem.getShopLAdd().equals("부산광역시") ? " selected" : ""%>>부산광역시</option>
							<option value="대구광역시"
								<%=sMem.getShopLAdd().equals("대구광역시") ? " selected" : ""%>>대구광역시</option>
							<option value="인천광역시"
								<%=sMem.getShopLAdd().equals("인천광역시") ? " selected" : ""%>>인천광역시</option>
							<option value="광주광역시"
								<%=sMem.getShopLAdd().equals("광주광역시") ? " selected" : ""%>>광주광역시</option>
							<option value="대전광역시"
								<%=sMem.getShopLAdd().equals("대전광역시") ? " selected" : ""%>>대전광역시</option>
							<option value="울산광역시"
								<%=sMem.getShopLAdd().equals("울산광역시") ? " selected" : ""%>>울산광역시</option>
							<option value="세종특별자치시"
								<%=sMem.getShopLAdd().equals("세종특별자치시") ? " selected" : ""%>>세종특별자치시</option>
							<option value="경기도"
								<%=sMem.getShopLAdd().equals("경기도") ? " selected" : ""%>>경기도</option>
							<option value="강원도"
								<%=sMem.getShopLAdd().equals("강원도") ? " selected" : ""%>>강원도</option>
							<option value="충청북도"
								<%=sMem.getShopLAdd().equals("충청북도") ? " selected" : ""%>>충청북도</option>
							<option value="충청남도"
								<%=sMem.getShopLAdd().equals("충청남도") ? " selected" : ""%>>충청남도</option>
							<option value="전라북도"
								<%=sMem.getShopLAdd().equals("전라북도") ? " selected" : ""%>>전라북도</option>
							<option value="전라남도"
								<%=sMem.getShopLAdd().equals("전라남도") ? " selected" : ""%>>전라남도</option>
							<option value="경상북도"
								<%=sMem.getShopLAdd().equals("경상북도") ? " selected" : ""%>>경상북도</option>
							<option value="경상남도"
								<%=sMem.getShopLAdd().equals("경상남도") ? " selected" : ""%>>경상남도</option>
							<option value="제주특별자치도"
								<%=sMem.getShopLAdd().equals("제주특별자치도") ? " selected" : ""%>>제주특별자치도</option>
						</select> <input type="text" class="form-control" placeholder="시/군/구"
							value="<%=sMem.getShopMAdd()%>" name="shopM"><input
							type="text" class="form-control" placeholder="이하 주소"
							value="<%=sMem.getShopSAdd()%>" name="shopS"><br>
						<p>전화번호</p>
						<input type="text" class="form-control"
							value="<%=sMem.getShopPhone()%>" name="shopPhone"><br>
						<p>이메일</p>
						<input type="text" class="form-control"
							value="<%=sMem.getShopEmail()%>" name="shopEmail"><br>
						<p>사업자등록증</p>
						<div id="shop-img-link">
							<input type="file" id="crnFile" name="crnFile">
							<%-- <%= cImg.getCrnFileName() %> --%>
						</div>
						<br> <br>
					</div>
					<div id="buttons">
						<input class="btn btn-danger" type="reset" value="취소">&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="btn btn-info" type="submit" value="수정">
					</div>
				</div>
			</form>
		</section>
	</main>

	<!-- 푸터 -->
	<%@include file="../../footer.jsp"%>


</body>
</html>