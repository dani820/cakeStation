<%@page import="java.sql.Connection"%>
<%@ page import="user.model.vo.CustomerMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	CustomerMember ctMember = (CustomerMember)request.getAttribute("ctMember");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>마이페이지</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="/css/reset.css">
		<link rel="stylesheet" href="/css/customerInfo.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="/js/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<%@include file ="/WEB-INF/views/header.jsp" %>
		
		<main>
			<%@include file ="/WEB-INF/views/CtMyPageSidebar.jsp" %>
			
			 <section id="content-box">
				<h2 id="sub-title">회원정보수정</h2>
				<form action="/customer/update" method="post" id="info-box">
					<section id="info-zone">
						<p>아이디</p><input type="text" value="<%= ctMember.getMemberId() %>" class="form-control" readonly name="user-id"><br>
						<p>비밀번호</p><input type="password" value="<%= ctMember.getMemberPwd() %>" class="form-control" name="user-pwd"><br>
						<p>이름</p><input type="text" value="<%= ctMember.getMemberName() %>" class="form-control" name="user-name"><br>
						<p>이메일</p><input type="text" value="<%= ctMember.getMemberEmail() %>" class="form-control" name="user-email"><br>
						<p>전화번호</p><input type="text" value="<%= ctMember.getMemberPhone() %>" class="form-control" name="user-phone"><br>
						<p>주소</p><input type="text" value="<%= ctMember.getMemberAddress() %>" class="form-control" name="user-address"><br>
						<section id="btn-zone">
							<input type="button" value="취소" class="btn btn-danger">
							<input type="submit" value="수정" class="btn btn-info">
						</section>
					</section>
				</form>
			</section> 
		</main>
		
		<%@include file ="/WEB-INF/views/footer.jsp" %>
	</body>
</html>