<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String checkResult = (String)request.getAttribute("checkResult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/customerEnroll.css">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<title>회원가입</title>
<script>
	$(document).ready(function(){
		
		/* 셀렉트 박스 값 */
		$("#domain-select").change(function(){
			$("input[name=email-domain]").val($("#domain-select option:selected").val());
		});
		
		// 회원가입 버튼 클릭 시 실행
		$("#submitBtn").click(function(){
			if($("#check-result").val() != "yes") {
				return false;
			}
			if($("input[name=user-id]").val()==""){
				alert("아이디를 입력하세요!");
				return false;
			}else if($("input[name=user-pwd]").val()=="" || $("input[name=user-pwd-re]").val()==""){
				alert("비밀번호를 입력하세요!");
				return false;
			}else if($("input[name=user-name]").val()==""){
				alert("이름을 입력하세요!");
				return false;
			}else if
			($("input[name=user-phone1]").val()=="" || $("input[name=user-phone2]").val()=="" || $("input[name=user-phone3]").val()==""){
				alert("전화번호를 입력하세요!");
				return false;
			}else if
			($("input[name=email-id]").val()=="" || $("input[name=email-domain]").val()==""){
				alert("이메일을 입력하세요!");
				return false;
			}else if
			($("input[name=user-address]").val()==""){
				alert("주소를 입력하세요!");
				return false;
			}
	
			// 약관 전체 체크
			if($("#checkAll").is(":checked")==true){
				return true;
			}else{
				alert("약관 동의 여부를 확인해주세요.");
				return false;
			}
		});
		
		/* 약관 전체/단일 체크 */
		$("[type=checkbox][name=recieve]").on("change", function(){
			var check = $(this).prop("checked");
			// 전체 체크
			if($(this).hasClass("allcheck")){
				$("[type=checkbox][name=recieve]").prop("checked", check);
				
			// 단일 체크	
			}else{
				var all = $("[type=checkbox][name=recieve].allcheck");
				var allcheck = all.prop("checked");
				if(check != allcheck){
					var len = $("[type=checkbox][name=recieve]").not(".allcheck").length;
					var ckLen = $("[type=checkbox][name=recieve]:checked").not(".allcheck").length;
					if(len === ckLen){
						all.prop("checked", true);
					}else{
						all.prop("checked", false)
					}
				}
			}
			
		});

	});
	


	// 아이디 중복확인 버튼 클릭 시 화면 open
	function openIdChk() {
		/* window.name = "parentForm";
		window.open("member/IdCheckForm.jsp", "chkForm",
				"width=500, height=300, resizable=no, scrollbars=no"); ////////////
		 */		
		//새창 만들기 
		window.open("idCheckForm.jsp", "idwin", "width=400, height=350");

	}

	// 아이디 입력창에 값 새로 입력 시 hidden에 inUncheck 세팅
	function inputIdChk() {
		document.userInfo.idDuplication.value = "idUncheck";
	}
	
	
</script>
</head>
<body>
<script>
	/* 비밀번호 일치 */
	function checkPwd(){
		var pwd = document.getElementById("user-pwd").value;
		var rePwd = document.getElementById("user-pwd-re").value;
		console.log(pwd); console.log(rePwd); console.log(document.getElementById("pwdCheck"));
		if(pwd!=rePwd){
			document.getElementById("pwdCheck").style.color = "red";
			document.getElementById("pwdCheck").innerHTML = "비밀번호가 일치하지 않습니다.";
		}else{
			document.getElementById("pwdCheck").style.color = "blue";
			document.getElementById("pwdCheck").innerHTML = "비밀번호가 일치합니다.";
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
				<a href="/index.jsp"><img src="/img/cakestation_logo.png"
					id="logo-img" /></a>
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
			<h1>Cake Station 회원가입</h1>

			<section id="enroll-box">
				<h1>정보 입력</h1>
				
				<form action="/member/enroll" method="post" name="userInfo">
				
					<section id="insert-1">
						<input type="hidden" id="check-result" value="<%=checkResult %>">
						<input type="text" name="user-id" onkeydown="inputIdChk()"
								class="insert" placeholder="아이디"> &nbsp;&nbsp;
							<input type="button" value="중복확인" onclick="openIdChk()" id="checkBtn"> <br>
							<input type="hidden" name="idDuplication" value="idUncheck">
							<input type="password" id="user-pwd" name="user-pwd" class="insert" placeholder="비밀번호"> <br>
							<input type="password" id="user-pwd-re" name="user-pwd-re" onkeyup="checkPwd()" class="insert" placeholder="비밀번호 확인">
							<div id="pwdCheck"></div><br>
							<input type="text" name="user-name" class="insert" placeholder="이름"> <br> 
							<div>
								<select name="user-phone1">
									<option selected>010</option>
									<option>011</option>
									<option>016</option>
									<option>017</option>
									<option>018</option>
									<option>031</option>
								</select>
								 - <input type="text" name="user-phone2" size="8" placeholder="1234">
								 - <input type="text" name="user-phone3" size="8" placeholder="5678"> <br>
								<!-- 타입 수정 -->
							</div>
							<div>
								<input type="text" name="email-id" size="13" placeholder="이메일"> @ <input type="text" name="email-domain" size="12" placeholder="직접 작성">
								&nbsp;&nbsp;
								<select id="domain-select">
									<option value="">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="hanmail.com">hanmail.com</option>
									<option value="nate.com">nate.com</option>
								</select><br>
								<!-- 타입 수정 -->
							</div>
							<input type="text" name="user-address" class="insert" placeholder="주소"> <br>
							<!-- 타입 수정 -->
							<!-- <li>가입일시 : </li> -->
							<input type="hidden" name="user-type" value="C">
							<!-- <li>회원 구분 : <input type="hidden" name="user-type" value="C"></li><br>  -->
					</section>
					<br><hr>
					
				<h1>약관 동의</h1>
				
				  <section id="insert-2">
					  	<label><input type="checkbox" id="checkAll" class="allcheck" name="recieve"> CakeStation 전체 약관 및 마케팅 수신에 동의합니다.</label><br><br>&nbsp;
						<label><input type="checkbox" name="recieve">&nbsp; 개인정보 수집 및 이용</label><br>&nbsp; 
						<label><input type="checkbox" name="recieve">&nbsp; 전자금융거래 약관</label><br>&nbsp; 
						<label><input type="checkbox" name="recieve">&nbsp; CakeStation 이용약관</label><br>&nbsp;
						<label><input type="checkbox" name="recieve">&nbsp; 이벤트정보 SMS 수신동의</label> <br> <br>
						<!-- 체크박스 전체 다 체크돼야 동의로 넘어감.아니면 팝업 -->
				  </section>
						<input type="hidden" name="agree" value="Y">
						<input type="submit" id="submitBtn" class="btn regist" value="회원가입">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" onclick="location.href='/index.jsp'" class="btn reset" value="취소">
					
				</form>

			</section>
		</section>

	</main>


	<!-- 푸터 -->
	<footer>
		<section class="footer-layer" id="footer-lay1">
			<p class="footer-bold">
				<b>Cake Station Company</b>
			</p>
			<p>서울특별시 중구 남대문로1가 남대문로 120 (대일빌딩2,3층)</p>
			<p>사업자등록번호 123-12-12345</p>
			<button>사업자정보확인</button>
		</section>
		<section class="footer-layer" id="footer-lay2">
			<a href=""><img src="/img/Instagram_logo.png" width="30" />
				<p>인스타 문의 : cakestation@iei_kh</p></a>
		</section>
		<section class="footer-layer" id="footer-lay3">
			<a href=""><p class="footer-bold">공지사항&nbsp;&nbsp;</p></a> <a href=""><p
					class="footer-bold">고객센터&nbsp;&nbsp;</p></a>
			<p>영업시간 (월~금)09:00 ~ 18:00&nbsp;&nbsp;&nbsp;&nbsp;점심시간 12:00 ~ 13
				: 00</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;토,일(공휴일휴무)</p>
		</section>
	</footer>




</body>
</html>