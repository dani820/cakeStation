<%@page import="review.model.vo.ReviewAndComm"%>
<%@page import="cakeinfo.model.vo.CakeInfo"%>
<%@page import="shop.model.vo.ShopAndImg"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ShopAndImg shop = (ShopAndImg)request.getAttribute("shopAndimg");
	ArrayList<CakeInfo> cList = (ArrayList<CakeInfo>)request.getAttribute("cList");
	ArrayList<ReviewAndComm> rcList = (ArrayList<ReviewAndComm>)request.getAttribute("rcList");
	int listFlag = (Integer)(request.getAttribute("listFlag"));
	//int btnFlag = (Integer)(request.getAttribute("btnFlag"));
	int reviewFlag = (Integer)(request.getAttribute("reviewFlag"));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cake Station</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="/css/cakeStation.css">
<link rel="stylesheet" href="/css/shopDetail.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<!-- content -->
	<main id="main">

		<section id="blank"></section>
		<section id="subject">
			<div id="shopOV">
				<div id=shop-img>
					<img src="<%=shop.getShopFilePath()%>" width="250px" height="250px">
 				</div>
				<div id=shop-name>
					<span id="shopName"><%=shop.getShopName() %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href=<%=shop.getChatUrl() %>><img src="/img/kakaotalk.JPG" width="60px"/></a>
					<div id="score">
						<img src = "/img/star1.jpg" width="20px"/>
		 				&nbsp;<span id=avg style="font-weight:bold"><%=shop.getShopAvgScore() %></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</div>
			</div>
			<div id=navi>
				<ul>
					<li><a href="#shop-introduce">업체정보</a></li>
					<li><a href="#shop-cakelist">케이크</a></li>
					<li><a href="#review">구매후기</a></li>
				</ul>
			</div>
		</section>

		<hr>
		<section id="introduce">
			<div id=shop-detail>
				<article id=detail-introduce>
					<div id=detail-1><p><%=shop.getIntroduced() %></p></div>
					<div id=detail-2>
						<table id=shop-info>
							<tr>
								<td style="font-weight:bold;">주소</td>
								<td><%=shop.getAddrAll() %></td>
							</tr>
							<tr>
								<td style="font-weight:bold;">영업시간</td>
								<td><%=shop.getOpeningStart() %> : 00 ~ <%=shop.getOpeningEnd() %> : 00</td>
							</tr>
							<tr>
								<td style="font-weight:bold;">휴무</td>
								<td><%=shop.getClosed() %></td>
							</tr>
							<tr>
								<td style="font-weight:bold;">웹사이트</td>
								<td><a href=<%=shop.getShopWebsite() %>><%=shop.getShopWebsite() %></a></td>
							</tr>
						</table>
					</div>
				</article>
<!-- 카카오맵 API -->
				<div id="detail-map">
				<div id="map" style="width:300px;height:300px;"></div>
				</div>
			</div>
		</section>
		<br>
		<hr>
		<br>
<!-- 케이크리스트영역 시작 -->
		<section class="detailinfo showstep1" id="shop-cakelist">
			<section class="content1">
				<%if(listFlag == 1) {%>
					<table id="cake-list">
						<tr>
							<% int check = 0;
							for(CakeInfo cake : cList) { %>
							<td>
								<a href="/cake/detail?cakeNo=<%=cake.getCakeNo()%>&shopId=<%=cake.getShopId()%>"><img src = "<%=cake.getCakeFilePath() %>" width=100%> <br>
								<br><span style="font-size: 20px"><%=cake.getCakeName() %></span><br>
								<span style="font-size: 18px; line-height: 2em;"><%=cake.getCakePrice() %> 원</span><br></a>
							</td>
							<% check ++; 
								if((check%4==0)) { %>
						</tr>	
								<% } %>
						<% } %>
					</table>
				<% }else { %>
					<h1>현재 판매중인 케이크가 없습니다.</h1>
				<% } %>
			</section>
		</section>
		<!-- <section id=more-btn>
			<a href="#" class="btn-open"><span>더보기</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="btn-open"><span>감추기</span></a>
		</section> -->
		<br>
		<hr>
		<br>
<!-- 구매후기영역 시작 -->	
		<section class="detailinfo showstep1" id="review">
			<section id="review-subject"><h1>구매후기</h1></section>
			<section class="content" id="review-content" style="-ms-overflow-style: none;">	

				<br>
				<% if(reviewFlag==1) {%>
				<table id="review-board">
					<tr><td colspan="8" height="15px"><hr></td></tr>
					<% for(ReviewAndComm rc : rcList) { %>
					<tr>
						<td width="100px" style="font-weight:bold;"><%=rc.getMemberId() %></td>
						<td width=100px><%for(int i=0;i<rc.getReviewScore();i++) {%>
						<img src="/img/star1.jpg" width="13px">
						<% } %></td>
						<td colspan="5" width="300px"></td>
						<td><%=rc.getReviewDate() %></td>
					</tr>
					<tr>
						<td colspan=8>&nbsp;&nbsp;&nbsp;<%=rc.getReviewContent() %></td>
					</tr>
						<%if(rc.getReplyContents()!=null) {%>
							<tr>
								<td></td>
								<td colspan="7">└ <%=rc.getReplyContents() %></td>
							</tr>
							<tr>
								<td colspan="6"></td>
								<td style="font-weight:bold;text-align:center;">사장님</td>
								<td><%=rc.getReplyDate() %></td>
							</tr>
						<% } %>
						<tr><td colspan="8" height="15px"><hr></td></tr>
					<% } %>
				</table>
				<% } else {%>
					<h3>작성된 후기가 없습니다.</h3>
				<% } %>
			</section>
		</section>
<!-- if(rList != null) {
		목록 띄움
		
		<오픈채팅 url태그>
		
		 
		주문하기 클릭시: hidden으로 넘겨줘야하는 부분: 제품번호, 케이크명, 가격, 업체아이디, 업체명(객체에서 꺼내기)
		get으로 간다.
		
	else <h1>아직 판매중인 케이크가 없습니다.</h1> -->

	<!-- 후기작성버튼 누를때 hidden태그로 제품번호 등 넘김 -->
	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
	
<!-- 지도api -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=296678e027eb12bfe04eed545c6479c6&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        draggable: false,//지도이동,확대축소 막기
        //setZoomable: true,//지도 확대축소
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('<%=shop.getAddrMap()%>', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});
</script>

<!-- 더보기버튼 -->
<!-- <style>
    .showstep1{
        max-height: 600px;
        overflow: hidden;
    }
    .showstep2{
        max-height: 1200px;
        overflow: hidden;
    }
    .content{
        background-color: "red";
    }
    .hide{
        display: none;
    } -->
</style>

</script>
</body>
</html>