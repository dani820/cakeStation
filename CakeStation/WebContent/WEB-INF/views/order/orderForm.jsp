<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//pickup daypick qty cakeNo cakeName cakePrice shopId shopName cakeFilePath 
	String pickupMethod = (String)request.getAttribute("pickupMethod");
	int qty = (Integer)request.getAttribute("qty");
	int cakeNo = (Integer)request.getAttribute("cakeNo");
	String cakeName = (String)request.getAttribute("cakeName");
	int cakePrice = (Integer)request.getAttribute("cakePrice");
	//String shopId = (String)request.getAttribute("shopId");
	String currShopId = (String)request.getAttribute("currShopId");
	String shopName = (String)request.getAttribute("shopName");
	String cakeFilePath = (String)request.getAttribute("cakeFilePath");
	int openingStart = (Integer)request.getAttribute("openingStart");
	int openingEnd = (Integer)request.getAttribute("openingEnd");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cake Station</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="/css/cakeStation.css">
<link rel="stylesheet" href="/css/orderForm.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<!-- main -->
	<main>
		<section id=wrapper>
		<section id="blank"></section>
		<section id=orderAll>
		<fieldset id="orderForm">
			<legend align="center"><h3>주 문 서</h3></legend>
			<form action="/order/write" method="post" onsubmit="return orderForm_check()">
			<section id="order-top">
				<section id="picture"><img src=<%=cakeFilePath %> width="250px"/></section>
				<section id="contents">
					<span style="font-weight:bold">케이크명 :</span>&nbsp; <span><%=cakeName %></span>
					<br><span style="font-weight:bold">매장명 :</span>&nbsp; <span><%=shopName %></span>
					<br><span style="font-weight:bold">가격 :</span>&nbsp; <span><%=cakePrice %> 원</span>
					<br><span style="font-weight:bold">수령방법 :</span>&nbsp; <span><%=pickupMethod %></span>
					<br><span style="font-weight:bold">수량 :</span>&nbsp; <span><%=qty %> 개</span>
				</section>
			</section>
			<section id="order-detail">
				<div id="name-box"><span id="name">주문자명</span><input type="text" name="orderer" id="orderer" placeholder="이름을 입력하세요"></div>
				<span>연락처</span>&nbsp;&nbsp;&nbsp;
				<input type="text" name="phone" id="phone" placeholder="010-123-1234">
				<br>
				<% if(pickupMethod.equals("픽업")) { %>
					<span>픽업일자</span>
					<select name="pickup-date" id="pickup-date">
						<!-- <option>날짜선택</option> -->
					</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span>픽업시간</span> 
					<select name="pickup-time" id="pickup-time1">
						<!-- <option>시간선택</option> -->
					</select>
				<% } %>
				<% if(pickupMethod.equals("당일 수령")) { %>
					<span>픽업시간</span> 
					<select name="pickup-time" id="pickup-time2">
						<!-- <option>시간선택</option> -->
					</select>
				<% } %>
				<% if(pickupMethod.equals("배송")) { %>
				<span>주소</span> &nbsp;&nbsp;&nbsp;
				<input type="text" name="addr" id="addr" style="width:350px" placeholder="서울시 중구 남대문로1가 남대문로 123 대일빌딩 2층">
				<% } %>
				<br>
				<span>요구사항</span><br>
				<textarea rows="5" cols="63" placeholder="내용을 입력해주세요" name="contents" id="request-text"></textarea>
				<div id="text-cnt">(0 / 200)</div>
				<br>
			</section>
			<section id="pay-type">
				<h3>결제방식</h3>
				<input type="radio" name="payType" value="T" id="transfer" onchange="setDisplay()">&nbsp; 계좌이체&nbsp;&nbsp;&nbsp; 
				<input type="radio" name="payType" value="C" id="card" onchange="setDisplay()">&nbsp; 카드
			</section>
			<section id="pay-Info">
				<p>예금주명: Cake Station
				<p>은행명: HK BANK
				<p>계좌번호: 1-23456789-12
			</section>
			<input type="hidden" name="cakeNo" value="<%=cakeNo %>">
			<input type="hidden" name="cakeName" value="<%=cakeName %>">
			<input type="hidden" name="cakePrice" value="<%=cakePrice %>">
			<input type="hidden" name="currShopId" value="<%=currShopId %>">
			<input type="hidden" name="shopName" value="<%=shopName %>">
			<input type="hidden" name="cakeNo" value="<%=pickupMethod %>">
			<input type="hidden" name="qty" value="<%=qty%>">
			<input type="hidden" name="cakeFilePath" value="<%=cakeFilePath %>">
			<input type="hidden" name="openingStart" value="<%=openingStart %>">
			<input type="hidden" name="openingEnd" value="<%=openingEnd %>">
			<input type="hidden" name="pickupMethod" value="<%=pickupMethod %>">
			<input type="hidden" name="userId" value="<%=userId %>">;
			<br>
			<br>
				<div id="pay-submit"><input type="submit" value="결제하기"></div>&nbsp;&nbsp;&nbsp; 
			</form>
		 <div id="pay-reset"><button onclick="history.back()">주문취소</button></div>
		</fieldset>
		</section>
	</section>
	
		<!-- if(수령방법=?)
			픽업일자/시간 다르게 뜨도록 태그 작성
			
			픽업일자... 어떻게... / 픽업시간
			-->
	           <!-- 만약에 계좌정보 뜨게 해야되면 cakeandshop에 해당 정보넣어야함 -->
		<!-- 결제하기 버튼 누르면 shoporderWriterServlet에 action = post -->
		<!-- 주문취소 버튼 누르면 뒤로가기(케이크 상세화면)으로 돌아가기(자바스크립트) -->
	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
<script>
	var checkFlag = '<%=pickupMethod %>';
	if(checkFlag=="픽업") {
		/* 픽업일자 */
		var date = new Date();
		
		var month = date.getMonth()+1;
		var year = date.getFullYear();
		var date = date.getDate();
		var yearChars = year.toString().split('');
		var monthChars = month.toString().split('');
		var dateChars;
		var option;
		 
		for(var i = date+3;i<=date+10;i++){
		dateChars = i.toString().split('');
		
		option = new Option();
		option.value = year + "/" + (monthChars[1]? month:"0"+month) + "/" + (dateChars[1]? i:"0"+i);
		option.text = year + "-" + (monthChars[1]? month:"0"+month) + "-" + (dateChars[1]? i:"0"+i);
		document.getElementById("pickup-date").options.add(option);
		}
		var selectValue = document.getElementById("pickup-time1"); 
		var optionIndex = 0;
		var option;
		for(var i=<%=openingStart%>;i<=<%=openingEnd%>;i++){
			timeChars = i.toString().split('');
			option = new Option();
			option.value = i+":00";
			option.text = i+":00";
			document.getElementById("pickup-time1").options.add(option);
		}
		
	}else {
		/* 시간 */
		var selectValue = document.getElementById("pickup-time2"); 
		var optionIndex = 0;
		var option;
		for(var i=<%=openingStart%>;i<=<%=openingEnd%>;i++){
			timeChars = i.toString().split('');
			option = new Option();
			option.value = i+":00";
			option.text = i+":00";
			document.getElementById("pickup-time2").options.add(option);
		}

	}

	/* 결제하기 누를 때 주문서 입력여부 확인 */
	function orderForm_check() {
		var nameRegExp = /^[가-힛]{2,4}$/
		var telRegExp = /[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/;
		var addrRegExp = /[가-힛][0-9]/;
		if(!nameRegExp.test($("#orderer").val())){
			alert("성함은 한글만 입력하실 수 있습니다.");
			return false;
		}else if(!telRegExp.test($("#phone").val())){
			alert("입력하신 전화번호를 확인해주세요.");
			return false;
		}else if($("#addr").val()=="") {
			alert("주소를 입력해주세요.")
			return false;
		}else if(($("input:radio[name='payType']").is(":checked")==false)) {
			alert("결제 방식을 선택해주세요.")
			return false;
		}
	}
	
	/* 결제방식에 따른 div 표시 */
	function setDisplay(){
		if($("input:radio[id=transfer]").is(':checked')){
    	$('#pay-Info').show();
		}else{
    		$('#pay-Info').hide();
		}
	}
	
	$("#request-text").on("keyup", function() {
		$("#text-cnt").html("(" + $(this).val().length + "/ 200)");
		if($(this).val().length > 200) {
			alert("최대 200자까지 입력가능합니다.");
			$(this).val($("#request-text").val().substring(0,200));
			$("#text-cnt").html("(200 / 200)");
		}
	});
	
</script>
</html>