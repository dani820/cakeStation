<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/pwdReset.css">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<script>
	$(document).ready(function(){
		/* 아이디, 비밀번호 알림창 */
		$("#btn").click(function(){
			if($("input[name=user-id]").val()==""){
				alert("아이디를 입력하세요!");
				return false;
			}else if($("#pwd").val()=="" || $("#pwd-re").val()==""){
				alert("비밀번호를 입력하세요!");
				return false;
			}
		});
	});
</script>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
</head>
<body>
<script>
	function checkPwd(){
		var pwd = document.getElementById("pwd").value;
		var rePwd = document.getElementById("pwd-re").value;

		if(pwd!=rePwd){
			document.getElementById("checkPwd").style.color = "red";
			document.getElementById("checkPwd").innerHTML = "비밀번호가 일치하지 않습니다.";
		}else{
			document.getElementById("checkPwd").style.color = "blue";
			document.getElementById("checkPwd").innerHTML = "비밀번호가 일치합니다.";
		}
	}
</script>

	<!-- 헤더 -->
	<header>
		<section class="wrapper">		
			<section id="header-user">
					<a href="/member/login.jsp">로그인&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
					<a href="/member/choiceEnroll.html">회원가입&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
				<a href="">고객센터&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</section>
			<section id="header-logo">
				<a href="/index.jsp"><img src="/img/cakestation_logo.png" id="logo-img"/></a>
			</section>
		</section>
	</header>
	
	
	<!-- 네비 -->
	<nav>
		<ul>
			<li><a href="">HOME</a></li>
			<li><a href="">매장</a></li>
			<li><a href="">케이크</a></li>
			<li><a href="">내 주변 가게 찾기</a></li>
			<li><a href="">마이페이지</a></li>
		</ul>
	</nav>
	
	
	<!-- 메인 -->
	<main>
		
		<section id="wrapper">
		
			<h1>비밀번호 재설정</h1>
	<!-- 유효성검사해야함/ -->
			<section id="find-box">
				<form action="/shop/resetPwd" method="post">
					<h2>아이디를 입력해주세요.</h2>
					<input type="text" name="user-id" class="insert" placeholder="&nbsp;&nbsp;아이디 입력"/>
					<h2>변경할 새 비밀번호를 입력해주세요.</h2>
					<input type="password" id="pwd" name="user-pwd" class="insert" placeholder="&nbsp;&nbsp;비밀번호 입력"/><br><br>
					<input type="password" id="pwd-re" onkeyup="checkPwd()" class="insert" placeholder="&nbsp;&nbsp;비밀번호 확인"/>
					<div id="checkPwd"></div>
					<input type="submit" id="btn" value="변경 완료"/>
				</form>
			</section>
		</section>
	</main>
	
	
	
	<!-- 푸터 -->
	<footer>
		<section class="footer-layer" id="footer-lay1">
			<p class="footer-bold"><b>Cake Station Company</b></p>
			<p>서울특별시 중구 남대문로1가 남대문로 120 (대일빌딩2,3층)</p>
			<p>사업자등록번호 123-12-12345</p>
			<button>사업자정보확인</button>
		</section>
		<section class="footer-layer" id="footer-lay2">
			<a href=""><img src="/img/Instagram_logo.png" width="30"/>
			<p>인스타 문의 : cakestation@iei_kh</p></a>
		</section>
		<section class="footer-layer" id="footer-lay3">
			<a href=""><p class="footer-bold">공지사항&nbsp;&nbsp;</p></a>
			<a href=""><p class="footer-bold">고객센터&nbsp;&nbsp;</p></a>
			<p>영업시간  (월~금)09:00 ~ 18:00&nbsp;&nbsp;&nbsp;&nbsp;점심시간 12:00 ~ 13 : 00</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;토,일(공휴일휴무)</p>
		</section>
	</footer>
	

	
</body>
</html>