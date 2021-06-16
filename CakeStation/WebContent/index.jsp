<%@page import="cakeinfo.model.vo.MainCakeInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cakeinfo.model.vo.MainSearchCakeInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="0.1 url=/main/cakestation">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- <script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
</script> -->
<link rel="stylesheet" type="text/css" href="/css/index.css">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<title>메인(index.jsp)</title>
</head>
<body>

	<!-- 헤더 -->
	<header>
		<section class="wrapper">		
			<section id="header-user">
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
	
		<!-- 케이크 검색 -->
		<section id="main-search">
			<form action="/member/mainsearch" method="post"><!-- servlet 매핑명 정하기 -->
				<h2>케이크 검색</h2>
				<!-- <input type="button" class="searchBtn" name="search" value="지역"> <!-- 버튼이 체크되게(프론트?체크박스?) 
				<input type="button" class="searchBtn" name="search" value="케이크 종류"><br>
				<input type="button" class="searchBtn" name="search" value="테마">
				<input type="button" class="searchBtn" name="search" value="수령방법"><br><br> -->
				
				<label class="searchBtn"><input type="radio" name="search" value="지역"><span>지역</span></label>
				<label class="searchBtn"><input type="radio" name="search" value="케이크 종류"><span>케이크 종류</span></label><br>
				<label class="searchBtn"><input type="radio" name="search" value="테마"><span>테마</span></label>
				<label class="searchBtn"><input type="radio" name="search" value="수령방법"><span>수령방법</span></label>
				<input type="submit" id="searchNextBtn" value="NEXT >">
				
				<!-- <button>지역</button><button>케이크 종류</button><button>테마</button><button>수령방법</button> -->
			</form>
		</section>
		<br><br><br><br><br>
		

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