<%@page import="cakeinfo.model.vo.CakeInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<CakeInfo> cList = (ArrayList<CakeInfo>)request.getAttribute("cList");
	String cakePageNavi = (String)request.getAttribute("cakePageNavi");
	String curId = (String)request.getAttribute("curId");
%>
<!DOCTYPE html>
<html>
<head>ㅋ
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/shopCakeList.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>마이페이지</title>
</head>

<body>
	<!-- 헤더 -->
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
			<!-- <a href="/shopMy/registerShopInfo">업체정보등록</a> -->
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
		<!-- 케이크 목록 -->
		<div id="con2" class="set-article">
			<div id=list-title>
				<h2>케이크 목록</h2>
			</div>
			<div id="cList-con">
				<% if(cList.isEmpty()) { %>
				<h4>등록하신 케이크가 없습니다.</h4>
				<div id="enrollNew">
				<a class="btn btn-primary" id="enrollNew" href="/cakeInfo/enroll" role="button">등록하러 가기</a>
				</div>
				<% }else { %>
                    <div id="cake-all">
                        <div id="cake-top">
                        	<a class="btn btn-primary" href="/cakeInfo/enroll" role="button">등록하기</a>					
                        </div>
                        <!-- 테이블 생성 -->
                        <% for(CakeInfo cake : cList) { %>
                        <div id="cake-bottom">
	                        <div id="cake-pic">
								<img src="<%= cake.getCakeFilePath() %>" class="img-thumbnail" alt="cake_thumbnail" width="250" height="250">
                    		</div>
                            <div id="cake-con">
                               	<!-- 사용자에게는 보여지지 않지만 넘겨줘야 하는 값 지정할 때  -->
								<input type="hidden" value="<%= cake.getCakeNo() %>" name="cakeNo">
                                   <p><b>케이크명</b><br><%= cake.getCakeName() %></p>
                                   <p><b>가격</b><br><%= cake.getCakePrice() %></p>
                                   <span id="cake-score"><b>평점</b></span><br>
                                   <% if(cake.getCakeAvgScore() == 0) { %>
					                  <img src="/img/cancel.png" width="13px"/><br><br>
                                   <% } else { %>
                                   <% for(int i = 0; i < cake.getCakeAvgScore(); i++) { %>
					                  <img src="/img/star1.jpg" width="13px"/>
				                  <% } %>
                                   <br><br>
    				              	<% } %>					
                                   <p><b>수령방법</b><br>
                                   <% if(cake.getDayRec().equals("전체")) { %>
	                                   <span>배송/픽업 가능</span>
                                   <% }else { %>
                                   		<span><%= cake.getDayRec() %> 가능 </span>
                                   <% } %>
                                   <% String pickup = "Y"; %>
                                   <% if(cake.getDayPickUp().equals(pickup)) { %>
                                   <span>,&nbsp;당일픽업 가능</span>
                                   <% } else { %>
                                   <span>,&nbsp;당일픽업 불가</span>
                                   <% } %>
                                   <p><b>테마</b><br><%= cake.getCakeThema() %></p>
                                   <p><b>종류</b><br><%= cake.getCakeType() %></p>
                                   <p><b>케이크 상세</b></p>
                                   <div id="cake_char">
                                   		<%= cake.getCakeChar() %>
	                               </div>    
                            </div> 
                            <div id="cake-buttons">
                                <a class="btn btn-info" href="/cake/detail?cakeNo=<%= cake.getCakeNo() %>&shopId=<%= curId %>" role="button">케이크 상세 바로가기</a>
                                <br>
                                <a class="btn btn-warning" href="/cakeInfo/modify?cakeNo=<%= cake.getCakeNo() %>" role="button">수정</a>&nbsp;&nbsp;
                                <a class="btn btn-danger" href="/cakeInfo/delete?cakeNo=<%= cake.getCakeNo() %>" role="button">삭제</a>
                            </div> 
                   		</div>
						<% } %>	
                   	</div>
		            <div id="cake-navi">
		                <span><%= cakePageNavi %></span>
		            </div>
              	<% } %>					
			</div>
		</div>
	</section>
	
	<!-- 푸터 -->
	<%@include file ="../../footer.jsp" %>
	
</body>
</html>