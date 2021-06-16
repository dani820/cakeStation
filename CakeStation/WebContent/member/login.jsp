<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<script src="${contextPath}/resources/js/js.cookie.js"></script>
<script type="text/javascript">

 	/* 일반/업체 로그인 선택 */
	function chLogBtn(index){
		if(index==1){
			$("#user-login").css('display','');
			$("#shop-login").css('display','none');
			$(".cust").css('background-color', '#fec5bb');
			$(".cust").css('border', 'none');
			$(".shop").css('border','3px solid #fec5bb');
			$(".shop").css('background-color','white');
		}else if(index=2){
			$("#user-login").css('display','none');
			$("#shop-login").css('display','');
			$(".cust").css('border','3px solid #fec5bb');
			$(".shop").css('border', 'none');
			$(".cust").css('background-color','white');
			$(".shop").css('background-color','#fec5bb');
		}else{
			$("#user-login").css('display','');
			$("#shop-login").css('display','none');
			$(".cust").css('background-color', '#fec5bb');
			$(".shop").css('border','3px solid #fec5bb');
			$(".shop").css('background-color','white');
		}
	}

	/* 아이디(쿠키) 저장 */
/* 	$("#userId").val(Cookies.get('key'));      
	    if($("#userId").val() != ""){
	        $("#saveId").attr("checked", true);
	    }
	    
	$("#saveId").change(function(){
	    if($("#saveId").is(":checked")){
	        Cookies.set('key', $("#userId").val(), { expires: 7 });
	    }else{
	          Cookies.remove('key');
	    }
	});
	     
	$("#userId").keyup(function(){
	    if($("#saveId").is(":checked")){
	        Cookies.set('key', $("#userId").val(), { expires: 7 });
	    }
	});	
	 */
</script>
<!-- <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<title>로그인</title>
</head>
<body>
<script>
	$(document).ready(function(){
		console.log("gkgk");
		// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
		var userInputId = getCookie("userInputId");
	
		$("input[name='userId']").val(userInputId);  
	 
		if($("input[name='userId']").val() != ""){ // 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
			$("#saveId").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
		}
	
		$("#saveId").change(function(){ // 체크박스에 변화가 있다면,
			if($("#saveId").is(":checked")){ // ID 저장하기 체크했을 때,
				var userInputId = $("input[name='userId']").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			}else{ // ID 저장하기 체크 해제 시,
				deleteCookie("userInputId");
			}
		});
	
		// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
		$("input[name='userId']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
			if($("#saveId").is(":checked")){ // ID 저장하기를 체크한 상태라면,
				var userInputId = $("input[name='userId']").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			}
		});
	});
	 
	  
	<%-- 쿠키 설정 --%>
	function setCookie(cookieName, value, exdays){
		var exdate = new Date();
	
		exdate.setDate(exdate.getDate() + exdays);
	
		var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	
		document.cookie = cookieName + "=" + cookieValue;
	}
	
	 
	<%-- 쿠키 삭제 --%>
	function deleteCookie(cookieName){
		var expireDate = new Date();
	
		expireDate.setDate(expireDate.getDate() - 1);
	
		document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	
	 
	<%-- 쿠키 호출 --%>
	function getCookie(cookieName) {
	
		cookieName = cookieName + '=';
	
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
	
		if(start != -1){
			start += cookieName.length;
	
			var end = cookieData.indexOf(';', start);
	
			if(end == -1)end = cookieData.length;
	
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
</script>
	<%@include file ="/WEB-INF/views/header.jsp" %>	
	
	<!-- 메인 -->
	<main>
		
		<section id="wrapper"> <!-- wrapper? 필ㅛ없나? -->
		
			<!-- 로그인하기 -->
			<h1>로그인</h1>
			
			<section id="login-box">

				<section class="btn-section">
					<button onclick="chLogBtn(1)" class="choiceBtn cust">일반</button>
					<button onclick="chLogBtn(2)" class="choiceBtn shop">업체</button>
				</section>
				<br>
				<section>
					<!-- 고객 로그인 -->
					<div id="user-login" class="tabcontent">
						<% if(userId == null) { %>
						<form action="/member/login" method="post">
							<div class="login">
								<img src="/img/user.png" class="login-img" width="45"/>
								<input type="text" id="userId" name="userId" class="insert" placeholder="&nbsp;&nbsp;아이디"><br>
							</div>
							
							<div class="login">
								<img src="/img/key.png" class="login-img" width="45"/>
								<input type="password" name="userPwd" class="insert" placeholder="&nbsp;&nbsp;비밀번호"><br>
							</div>
							<input type="submit" class="login btn" value="로그인"><br>
							<label><input type="checkbox" id="saveId" name="saveId"> 아이디 저장&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<a href="/member/findId.jsp">아이디 찾기&nbsp;&nbsp;|</a>
							<a href="/member/findPwd.jsp">&nbsp;&nbsp;비밀번호 찾기</a>
						</form>
						<% } %>
					</div>
					
					<!-- 업체 로그인 -->
					<div id="shop-login" style="display:none;">
						<% if(userId == null) { %>
						<form action="/shop/login" method="post">
							<div class="login">
								<img src="/img/user.png" class="login-img" width="45"/>
								<input type="text" name="shop-userId" class="insert" placeholder="&nbsp;&nbsp;업체 아이디"><br>
							</div>
							<div class="login">
								<img src="/img/key.png" class="login-img" width="45"/>
								<input type="password" name="shop-userPwd" class="insert" placeholder="&nbsp;&nbsp;업체 비밀번호"><br>
							</div>
							<input type="submit" class="login btn" value="로그인"><br>
							<label><input type="checkbox" name="saveId"> 아이디 저장&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<a href="/member/shopFindId.jsp">아이디 찾기&nbsp;&nbsp;|</a>
							<a href="/member/shopFindPwd.jsp">&nbsp;&nbsp;비밀번호 찾기</a>
						</form>
						<% } %>
					</div>
				</section>
				
			</section>
		
		</section>

		
		<!-- 고객 로그인 완료 -->
		<% if(userId != null && userId != "") { %>
		<%= userId %> 님 환영합니다.
		<a href="/member/logout">로그아웃</a>
		<br>
		<% } %>	
		<!-- 환영합니다 하고 index로 넘어가기 -->
		
		
		<!-- 업체 로그인 완료 -->
		<% if(userId != null && userId != "") { %>
		<%= userId %> 님 환영합니다.
		<a href="/member/logout">로그아웃</a>
		<br>
		<% } %>	
		<!-- 환영합니다 하고 index로 넘어가기 -->
		
	
	
	
<%-- 		<!-- 로그인하기 -->
		<% if(userId == null) { %>
		<h1>로그인 페이지!</h1>
		<form action="/member/login" method="post">
			ID : <input type="text" name="userId"><br>
			PW : <input type="password" name="userPwd"><br>
			<input type="submit" value="로그인">
			<input type="reset" value="취소">
			<a href="/member/choiceEnroll.html">회원가입</a>
		</form>
		<% } %>
 --%>

 
 

	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>	
	
</body>
</html>