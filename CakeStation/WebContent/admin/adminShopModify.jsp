<%@page import="shop.model.vo.ShopMember"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ShopMember sMember = (ShopMember)request.getAttribute("sMember");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>관리자 마이페이지</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="/css/cakeStation.css">
	    <link rel="stylesheet" href="/css/adminMyPage.css">
	</head>
	<body>
		<%@include file ="/WEB-INF/views/header.jsp" %>
	
		<main>
			<%@include file ="/WEB-INF/views/adminMyPageSidebar.jsp" %>
	        
	        <section id="content-box">
	        	<h2 id="sub-title">업체회원 정보수정</h2>
				<form action="/admin/shopModify" method="post">
					<fieldset>
						<ul>
							<li>아이디 : <input type="text" value="<%= sMember.getShopId() %>" name="shop-id" readonly></li>
							<li>비밀번호 : <input type="password" value="<%= sMember.getShopPwd() %>" name="shop-pwd"></li>
							<li>업체명 : <input type="text" value="<%= sMember.getShopName() %>" name="shop-name"></li>
							<li>대표자 : <input type="text" value="<%= sMember.getCEOName() %>" name="ceo-name"></li>
							<li>업체 주소 : <br>
							<input type="text" value="<%= sMember.getShopLAdd() %>" name="shop-l-add">
							<input type="text" value="<%= sMember.getShopMAdd() %>" name="shop-m-add">
							<input type="text" value="<%= sMember.getShopSAdd() %>" name="shop-s-add">
							</li>
							<li>전화번호 : <input type="text" value="<%= sMember.getShopPhone() %>" name="shop-phone"></li>
							<li>이메일 : <input type="text" value="<%= sMember.getShopEmail() %>" name="shop-email"></li>
							<li>사업자 등록증 : <input type="text" value="<%= sMember.getShopCRN() %>" name="shop-crn"></li>
							<input type="submit" value="수정">
							<input type="reset" value="취소">
						</ul>
					</fieldset>
				</form>
	        </section>
		</main>
		
		<%@include file ="/WEB-INF/views/footer.jsp" %>
	</body>
</html>