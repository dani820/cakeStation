<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/findId.css">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<script>
$(document).ready(function(){
	/* 셀렉트 박스 값 */
	$("select[name=domain]").change(function(){
		$("input[name=email-domain]").val($("select[name=domain] option:selected").val());
	});
	/* 이메일 작성x 시 알람창 */
	$("#btn").click(function(){
		if($("input[name=email-id]").val()=="" || $("input[name=email-domain]").val()==""){
			alert("이메일을 입력하세요!");
			return false;
		}
	});
});
</script>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>

	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<!-- 메인 -->
	<main>
		
		<section id="wrapper"> <!-- wrapper? 필ㅛ없나? -->
			
			<h1>아이디 찾기</h1>

			<section id="find-box">
			
				<!-- if 아이디값이 없으면(get) 찾기,, 디폴트? -->
				<% if(userId == null) { %>
				<form action="/shop/findId" method="post">
					<h2>회원 가입 시 등록한 이메일을 입력해주세요.</h2>
					<input type="text" name="email-id" class="insert email1" size="8"> &nbsp;@&nbsp; <input type="text" name="email-domain" class="insert email2" size="8"/>&nbsp;&nbsp;
					<select name="domain">
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hanmail.com">hanmail.com</option>
						<option value="nate.com">nate.com</option>
					</select><br>
					<input type="submit" id="btn" class="btn" value="찾기">
				</form>
				<% } %>
				
				<!-- if 아이디값이 있으면(get) 아이디 표시,, -->
				<% if(userId != null && userId != "") {%>
					<h2 id="h2Id">회원님의 아이디는 <span style="color:blue; font-weight:bolder;"><%= userId %></span> 입니다.</h2><br>
					<button type="button" class="btn" onclick="location.href='/index.jsp'">홈으로 이동</button>
				<% } %>
 
			</section>			
				

	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>	


<%-- 
	<h3>아이디 찾기</h3>
	<hr><br>
	<!-- if 아이디값이 없으면(get) 찾기,, 디폴트? -->
	<% if(userId == null) { %>
	<form action="/shop/findId" method="post">
	<fieldset>
		<legend>아이디 찾기</legend>
		<h5>회원 가입 시 등록한 이메일을 입력해주세요.</h5>
		이메일 : <input type="text" name="email-id"> @ <input type="text" name="email-domain">
		<select name="domain">
			<option value="naver.com">naver.com</option>
			<option value="gmail.com">gmail.com</option>
			<option value="hanmail.com">hanmail.com</option>
			<option value="nate.com">nate.com</option>
		</select><br>
		<input type="submit" value="찾기">
	</fieldset>
	</form>
	<% } %>
	
	<!-- if 아이디값이 있으면(get) 아이디 표시,, -->
	<% if(userId != null && userId != "") {%>
	<fieldset>
		<legend>아이디 찾기</legend>
		<h5>회원님의 아이디는 <span style="color:blue; font-weight:bolder;"><%= userId %></span> 입니다.</h5><br>
		<button type="button" onclick="location.href='/index.jsp'">홈으로 이동</button>
	</fieldset>
	<% } %> --%>
</body>
</html>