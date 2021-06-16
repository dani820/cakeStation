<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="file.model.vo.ShopImg"%>
<%@page import="shop.model.vo.ShopMember"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ShopMember sInfo = (ShopMember) request.getAttribute("shopInfo");
ShopImg sImg = (ShopImg) request.getAttribute("shopImg");
//String fileName = URLEncoder.encode(sImg.getShopFileName(), "UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/shopShopInfo.css">
<script src="/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script>
	$(document).ready(function() {
		
		$("#closeTime").change(function() {
            
	           if($("#closeTime").val() < $("#openTime").val()) {
	            	alert("영업시간을 다시 선택해주세요.");
	           }
		});
		
        $("form").on("submit", function() {
            
	           if($("#shopImg").val() == "") {
	        	   alert("사진을 등록해주세요");
	        	   return false;
	           }
		});
		
        $(".form-control").on("keyup", function() {
            // keyup 해야 숫자가 바로 떨어짐
            // keypress는 눌린 후 값을 입력을 받으므로 바로 떨어지지 않음
            // inLength++;
            // $("#noti").html("작성가능한 글자수 : " + (150-inLength));

            var inLength = $(this).val().length;
            $("#noti").html("작성가능한 글자수 : " + (100-inLength));
            // 아스키코드만 지원해서 한글 작성시 keypress만! 적용 안됨
            // keyup, keydown 은 적용됨
            
            if(inLength > 100) {
            	alert('입력 가능한 글자 수가 초과되었습니다.(100자 이내 작성)')
            }
        });
        
        
	});
</script>
<title>마이페이지</title>
</head>
<body>
	<!-- 헤더, 네비 -->
	<%@include file="../../header.jsp"%>

	<!-- 본문 -->

	<main>
		<!-- 사이드바 -->
		<%@include file="/WEB-INF/views/shopMyPageSidebar.jsp"%>
		<!-- 업체 정보 조회 -->
		<section id="shop-section">
			<div id=list-title>
				<h2>가게 정보 보기</h2>
			</div>
			<div id="sInfo-con">
				<% if(sInfo == null) { %>
					<span>등록하신 가게가 없습니다.</span>
					<!-- <a class="btn btn-primary" href="/WEB-INF/views/shopMyPage/shopMyShopInfo/shopMyShopEnroll.jsp" role="button">등록하기</a> -->				
				<% }else { %>
				<div id="shop-detail">
					<form action="/shopMy/updateShopInfo" method="post"
						enctype="multipart/form-data">
						<div id="shop-img-thumbnail">
							<img id="preview-image" src="<%= sImg.getShopFileName() %>" class="img-thumbnail" alt="shop_thumbnail" width="200" height="200">
						</div>
						<div id="shop-img-link">
							<input type="file" id="shopImg" name="shopImg">
							<!-- <input type="file" id="shopImg" name="shopImg" accept="image/*"> -->
						</div>
						<div id="shop-introduce">
							<%-- <input type="hidden" value="<%=sInfo.getShopId()%>"	name="shopId"> --%>
							<p>업체명</p>
							<input type="text" class="form-control" name="shopName"
								value=""> <br>
							<p>업체주소</p>
							<input type="text" class="form-control" placeholder="시/도"
								name="shopLAdd" value=""><input
								type="text" class="form-control" placeholder="시/군/구"
								name="shopMAdd" value=""><input
								type="text" class="form-control" placeholder="이하 주소"
								name="shopSAdd" value=""> <br>
							<span>영업시간</span>&nbsp;&nbsp;&nbsp;&nbsp; <select id="openTime"
								name="openTime">
								<option value="9"
									>9 :
									00</option>
								<option value="10"
									>10
									: 00</option>
								<option value="11"
									>11
									: 00</option>
								<option value="12"
									>12
									: 00</option>
								<option value="13"
									>13
									: 00</option>
								<option value="14"
									>14
									: 00</option>
								<option value="15"
									>15
									: 00</option>
								<option value="16"
									>16
									: 00</option>
								<option value="17"
									>17
									: 00</option>
								<option value="18"
									>18
									: 00</option>
								<option value="19"
									>19
									: 00</option>
								<option value="20"
									>20
									: 00</option>
								<option value="21"
									>21
									: 00</option>
								<option value="22"
									>22
									: 00</option>
							</select> <span>-</span> <select id="closeTime" name="closeTime">
								<option value="9" >9
									: 00</option>
								<option value="10"
									>10 : 00</option>
								<option value="11"
									>11 : 00</option>
								<option value="12"
									>12 : 00</option>
								<option value="13"
									>13 : 00</option>
								<option value="14"
									>14 : 00</option>
								<option value="15"
									>15 : 00</option>
								<option value="16"
									>16 : 00</option>
								<option value="17"
									>17 : 00</option>
								<option value="18"
									>18 : 00</option>
								<option value="19"
									>19 : 00</option>
								<option value="20"
									>20 : 00</option>
								<option value="21"
									>21 : 00</option>
								<option value="22"
									>22 : 00</option>
							</select> <br>
							<br>
							<p>휴무</p>
							<input type="text" class="form-control" name="closed"
								value=""> <br>
							<p>사이트</p>
							<input type="text" class="form-control" name="shopWebsite"
								value=""> <br>
							<p>오픈채팅</p>
							<input type="text" class="form-control" name="chatUrl"
								value=""> <br>
							<p>업체소개</p>
							<textarea class="form-control" rows="5" name="introduce"></textarea>
							<!-- <h6 id="noti">작성 가능한 글자수 : 100</h6> -->
						</div>
						<div class="shop-buttons">
							<input class="btn btn-info" type="submit" value="수정">&nbsp;&nbsp;
							<input class="btn btn-danger" type="reset" value="취소">
						</div>
					</form>
				</div>
				<%
					}
				%>
			</div>
		</section>
	</main>

	<!-- 푸터 -->
	<%@include file="../../footer.jsp"%>

	<script>
			
			function readImage(input) {
			    // 인풋 태그에 파일이 있는 경우
			    if(input.files && input.files[0]) {
			        // 이미지 파일인지 검사 (생략)
			        // FileReader 인스턴스 생성
			        const reader = new FileReader();
			        // 이미지가 로드가 된 경우
			        reader.onload = e => {
			            const previewImage = document.getElementById("preview-image");
						previewImage.src = '';
			            previewImage.width = 200;
			            previewImage.height = 200;
			            previewImage.src = e.target.result;
			        }
			        // reader가 이미지 읽도록 하기
			        reader.readAsDataURL(input.files[0]);
			    }
			}
			// input file에 change 이벤트 부여
			const inputImage = document.getElementById("shopImg")
			inputImage.addEventListener("change", e => {
			    readImage(e.target);
			});

	</script>

</body>
</html>