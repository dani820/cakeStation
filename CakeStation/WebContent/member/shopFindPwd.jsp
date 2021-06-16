<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int checkCode = 0;
	if(request.getAttribute("checkCode") != null) {
		checkCode = (int)request.getAttribute("checkCode");
	}
%>
<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/findPwd.css">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<script>
$(document).ready(function(){
	/* 셀렉트 박스 값 */
	$("select[name=domain]").change(function(){
		$("input[name=email-domain]").val($("select[name=domain] option:selected").val());
	  /* console.log($(this).val()); //value값 가져오기
	  console.log($("select[name=location] option:selected").text()); //text값 가져오기 */
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
<title>비밀번호 찾기</title>
</head>
<body>
	<script>
		function checkCode(){
			var v1 = certification.code_check.value;
			var v2 = certification.code.value;
			if(v1!=v2){
				document.getElementById("checkCode").style.color = "red";
				document.getElementById("checkCode").innerHTML = "잘못된 인증번호";
				makeNull();
			}else{
				document.getElementById("checkCode").style.color = "blue";
				document.getElementById("checkCode").innerHTML = "인증 완료";
				makeReal();
			}
		}
		function makeNull(){
			var submitBtn = document.getElementById("submitBtn");
			submitBtn.type = "hidden";
		}
		function makeReal(){
			var submitBtn = document.getElementById("submitBtn");
			submitBtn.type = "submit";
		}
	</script>

	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	
	<!-- 메인 -->
	<main>
		
		<section id="wrapper">
		
			<h1>비밀번호 찾기</h1>
		
			<section id="find-box">
			
				<!-- if 인증번호 값이 없으면(get) 찾기,, 디폴트? -->
				<% if(checkCode == 0) { %>
				<form action="/shop/findPwd" method="post">
					<h2>아이디를 입력해주세요.</h2>
					<input type="text" name="userId" class="insert id" placeholder="&nbsp;&nbsp;아이디">
					<h2>회원 가입 시 등록한 이메일을 입력해주세요.</h2>
					<input type="text" name="email-id" class="insert email1" > &nbsp;@&nbsp; <input type="text" name="email-domain" class="insert email2" >&nbsp;&nbsp; 
					<select  name="domain">
						<option>naver.com</option>
						<option>gmail.com</option>
						<option>hanmail.com</option>
						<option>nate.com</option>
					</select><br>
					<input type="submit" id="btn" value="인증번호 발송">
				</form>
				<% } %>
				
				<!-- if 인증번호 값이 있으면(get) 아이디 표시,, -->
				<% if(checkCode != 0) {%>
				
				<form id="certification" action="/member/shopPwdReset.jsp" > <!-- 인증번호 일치하면(어떻게? 포스팅 보기) 재설정으로 넘어감 -->
					<h2>인증번호를 입력해주세요.</h2>
					<input type="text" name="code" id="code" class="insert" onkeyup="checkCode()" maxlength="5" placeholder="&nbsp;&nbsp;인증번호 입력"><br>
					<div id="checkCode"></div>
					<input type="hidden" readonly="readonly" name="code_check" id="code_check" value="<%= checkCode %>"/>
					<input id="submitBtn" type="hidden" value="인증하기"/>
				</form>
				<% } %>
			
			</section>
			
		</section>			
				

	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
	
</body>
</html>