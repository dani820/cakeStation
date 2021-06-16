<%@page import="cakeinfo.model.vo.CakeInfo"%>
<%@page import="order.model.vo.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CakeInfo cInfo = (CakeInfo)request.getAttribute("cakeInfo");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/shopMyCakeUp.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="js/jquery-3.5.1.min.js"></script>
<script>
	$(document).ready(function() {
		
		cakeName = $('input[name=cakeName]');
		cakePrice = $("input[name=cakePrice]");
		upCake = $("input[name=upCake]");
		dayRec = $("input[name=dayRec]");
		cakeChar = $("textarea[name=cakeChar]");
		
        $(".form-control").on("keyup", function() {
    
            var inLength = $(this).val().length;
            $("#noti").html("작성가능한 글자수 : " + (1000-inLength));
         
            if(inLength > 1000) {
            	alert('입력 가능한 글자 수가 초과되었습니다.(1000자 이내 작성)')
            }
        });
        
        $("form").on("submit", function() {
            
	           if(cakeName.val() == "") {
	        	   alert("케이크명을 입력해주세요");
	        	   return false;
	           }else if(cakePrice.val() == "") {
	        	   alert("가격을 입력해주세요");
	        	   return false;
	           }else if(upCake.val() == "") {
	        	   alert("사진을 업로드해주세요");
	        	   return false;
	           }else if(!dayRec.is(':checked')) {
	        	   alert("수령방법을 선택해주세요");
	        	   return false;
	           }else if(cakeChar.val() == "") {
	        	   alert("상세 내용을 입력해주세요");
	        	   return false;
	           }
		});
	})
</script>
<title>마이페이지</title>
</head>
<body>
<!-- 헤더, 네비 -->
	<%@include file ="../../header.jsp" %>
	
	<!-- 본문 -->
	<section id="shop-section">
	<!-- 사이드바 -->
		<aside id="side-bar">
			<h6>주문 내역 관리</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/orderList">예약목록</a></li>
			</ul>	
			<h6>케이크 정보 관리</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/cakeList">케이크목록</a></li>
			</ul>
			<h6>후기 게시판</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/reviewList">후기조회</a></li>
			</ul>
			<h6>업체 정보 관리</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/updateShopInfo">업체 정보 조회</a></li>
			</ul>
			<h6>회원 정보 관리</h6>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/shopMemInfo">회원 정보 조회</a></li>
			</ul>
			<ul class=shop-set>
				<li class="sets"><a href="/shopMy/MemDelete">회원 탈퇴</a></li>
			</ul>
		</aside>
		
		<!-- 본문 내용 -->
		<!-- 케이크 상세 -->
		<div id="con2" class="set-article">
			<div id=list-title>
				<h2>케이크 정보 수정</h2>
			</div>
			<div id="cake-con">
				<div id="cake-update">
		           	<form action="/cakeInfo/modify" method="post" enctype="multipart/form-data">
			           	<div id="cake-short">
			           		<div id="cake-left">
			           			<div id="cakePreview">
				           			<img id="preview-image" src="<%= cInfo.getCakeFilePath() %>" class="img-thumbnail" alt="cake_thumbnail" width="350" height="350">
				           		</div>
					           	<div id="cake-img-link">
				           			<input type="file" id="upCake" name="upCake" accept="image/*">
					           	</div>
				           	</div>
				           	<div id="cake-info">
				           		<br><br>
				           		<input type="hidden" value="<%= cInfo.getCakeNo() %>" name="cakeNo">
				                <input type="text" class="form-control" placeholder="케이크명" name="cakeName" value="<%= cInfo.getCakeName() %>">
								<br>
				                <input type="text" class="form-control" placeholder="가격" name="cakePrice" value="<%= cInfo.getCakePrice() %>">
				                <br><br>
				                <h5>수령방법</h5>
								  <input type="radio" id="delivery1" name="dayRec" value="전체"<%= cInfo.getDayRec().equals("전체")? "checked":"" %>> 배송/픽업
								&nbsp;&nbsp;&nbsp;
								  <input type="radio" id="delivery2" name="dayRec" value="배송"<%= cInfo.getDayRec().equals("배송")? "checked":"" %>> 배송
								&nbsp;&nbsp;&nbsp;
								  <input type="radio" id="delivery3" name="dayRec" value="픽업"<%= cInfo.getDayRec().equals("픽업")? "checked":"" %>> 픽업
								<br><br>
								  <input type="checkbox" id="dayPick" name="dayPick" value="Y"<%= cInfo.getDayPickUp().equals("Y")? "checked":"" %>> 당일 픽업
								&nbsp;&nbsp;&nbsp;
								<br><br>
								<select name="themas">
						            <option value="기타">테마</option>
						            <option value="축하"<%= cInfo.getCakeThema().equals("축하")?" selected":"" %>>축하</option>
						            <option value="연인"<%= cInfo.getCakeThema().equals("연인")?" selected":"" %>>연인</option>
						            <option value="생일"<%= cInfo.getCakeThema().equals("생일")?" selected":"" %>>생일</option>
						            <option value="감사"<%= cInfo.getCakeThema().equals("감사")?" selected":"" %>>감사</option>
						        </select>
						        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						        <select name="cType">
						            <option value="기타">종류</option>
						            <option value="버터크림케이크"<%= cInfo.getCakeType().equals("버터크림케이크")?" selected":"" %>>버터크림케이크</option>
						            <option value="생크림케이크"<%= cInfo.getCakeType().equals("생크림케이크")?" selected":"" %>>생크림케이크</option>
						            <option value="꽃케이크"<%= cInfo.getCakeType().equals("꽃케이크")?" selected":"" %>>꽃케이크</option>
						            <option value="떡케이크"<%= cInfo.getCakeType().equals("떡케이크")?" selected":"" %>>떡케이크</option>
						            <option value="비건"<%= cInfo.getCakeType().equals("비건")?" selected":"" %>>비건</option>
						        </select>
								<br>
			                </div>
		                </div>
		                <div id="cake-detail">
			                <h5>상세 설명</h5>
			                <textarea class="form-control" rows="15" name="cakeChar"><%= cInfo.getCakeChar() %></textarea>
			                 <!-- <h6 id="noti">작성 가능한 글자수 : 1000</h6> -->
		                </div>
		                <div id="buttons">
							<input class="btn btn-primary" type="submit" value="수정">&nbsp;&nbsp;
							<a class="btn btn-danger" href="/shopMy/cakeList" role="button">취소</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	
 	<!-- 푸터 -->
	<%@include file ="../../footer.jsp" %>
	
	 <script>
			
			function readImage(input) {
			 
			    if(input.files && input.files[0]) {
			      
			        const reader = new FileReader();
			       
			        reader.onload = e => {
			            const previewImage = document.getElementById("preview-image");
						previewImage.src = '';
			            previewImage.width = 350;
			            previewImage.height = 350;
			            previewImage.src = e.target.result;
			        }
			       
			        reader.readAsDataURL(input.files[0]);
			    }
			}
		
			const inputImage = document.getElementById("upCake");
		
			inputImage.addEventListener("change", e => {
			    readImage(e.target);
			});

	</script>
	
</body>
</html>