<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/css/customerInfo.css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="/js/jquery-3.5.1.min.js"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<title>마이페이지</title>
	</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<main>
		<%@include file ="/WEB-INF/views/CtMyPageSidebar.jsp" %>
		
		<section id="content-box">
			<h2 id="sub-title">회원탈퇴</h2>
			<form action="/customer/delete" method="post" id="delete-box">
				<p>아이디</p><input type="text" value="<%=userId%>" class="form-control" readonly name="user-id"><br>
				<p>비밀번호</p><input type="password" value="비밀번호를 입력해주세요" name="userPwd" class="form-control"><br>
				<section id="btn">
					<input type="submit" value="회원탈퇴" class="btn btn-danger">
				</section>
			</form>
		</section>
	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
</html>