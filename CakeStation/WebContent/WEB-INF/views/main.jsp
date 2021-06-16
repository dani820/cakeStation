<%@page import="cakeinfo.model.vo.MainCakeInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cakeinfo.model.vo.MainSearchCakeInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<MainSearchCakeInfo> searchCake = (ArrayList<MainSearchCakeInfo>)request.getAttribute("searchCake");
	ArrayList<MainCakeInfo> avgCake = (ArrayList<MainCakeInfo>)request.getAttribute("avgCake"); 
	ArrayList<MainCakeInfo> reviewCake = (ArrayList<MainCakeInfo>)request.getAttribute("reviewCake");
	ArrayList<MainCakeInfo> mdCake = (ArrayList<MainCakeInfo>)request.getAttribute("mdCake");
	String searchVal = (String)request.getAttribute("searchVal");
%>
<%!int check=0; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link rel="stylesheet" type="text/css" href="/css/reset.css">
<title>메인(index.jsp)</title>
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
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
				<% check=0; %>
				<!-- <button>지역</button><button>케이크 종류</button><button>테마</button><button>수령방법</button> -->
			</form>
			<div id="searchResult">
				<table>
					<% if(searchCake != null) { %>
					<tr>
					<% for(MainSearchCakeInfo cake : searchCake) { %>
						<td> 
						<a href="/cake/detail?cakeNo=<%=cake.getCakeNo()%>&shopId=<%=cake.getShopId()%>">
							<img src="<%= cake.getCakeFilePath() %>" onerror="this.src='/img/cakeimg.png'" width="150"/><br>
							<%= searchVal %> : 
							<% if(searchVal.equals("지역")){ %>
		                     <%=cake.getShopLAdd()  %>
		                     <%}else if(searchVal.equals("케이크 종류")){ %>
		                     <%= cake.getCakeType() %>
		                     <%}else if(searchVal.equals("테마")){ %>
		                     <%= cake.getCakeThema() %>
		                     <%}else{ %>
		                     	<%= cake.getDayRec() %>
		                     <%} %>											
							<br><%= cake.getCakeName() %><br>
							<%= cake.getCakePrice() %><br></a>
						</td>
				<% check++;
				if(check%6==0) {%> 
					</tr>
					<tr>
					<% } %>
				<%} %>
			<%} %>
				</table>
			
				<%-- <% if(searchCake != null) {
				 	for(MainSearchCakeInfo cake : searchCake) { %>
					<article class="cake-article">
						<img src=<%= cake.getCakeFilePath() %> width="150" alt="케이크"/><br>
						<span><%= cake.getCakeName() %></span><br>
						<span><%= cake.getCakePrice() %></span>
						<!-- 별점 -->
					</article>
					<% }
				 }%> --%>
			
			</div>
		</section>
		<br><br><br><br><br>
		
		<section id="wrapper">
		
			<!-- 별점 순 노출 -->
			<section class="cake-section">
				<h3>별점 순 노출</h3>
				<section>
				<%-- <% if(avgCake != null) {
					for(MainCakeInfo cake : avgCake) { %>
					<%= cake.getCakeName() %>
				<% } 
				 } %>
				  --%>
				  
				 <% if(avgCake != null) {
				 	for(MainCakeInfo cake : avgCake) { %>
					<article class="cake-article">
						<a href="/cake/detail?cakeNo=<%=cake.getCakeNo()%>&shopId=<%=cake.getShopId()%>">
						<img src=<%= cake.getCakeFilePath() %> width="150" onerror="this.src='/img/cakeimg.png'" alt="케이크"/><br>
						<span><%= cake.getCakeName() %></span><br>
						<span><%= cake.getCakePrice() %></span><br>
						<%for(int i=0;i<cake.getCakeAvgScore();i++) {%>
						<img src="/img/star1.jpg" width="11px">
						<% } %></a>
						<!-- 별점 -->
					</article>
					<% }
				 	}%>
				</section>
			</section>
			<br><br><br><br><br>
			<!-- 후기 순 노출 -->
			<section class="cake-section">
				<h3>후기 순 노출</h3>
				<section>
				
				<% if(reviewCake != null) {
				 	for(MainCakeInfo cake : reviewCake) { %>
					<article class="cake-article">
					<a href="/cake/detail?cakeNo=<%=cake.getCakeNo()%>&shopId=<%=cake.getShopId()%>">
						<img src=<%= cake.getCakeFilePath() %> width="150" onerror="this.src='/img/cakeimg.png'" alt="케이크"/><br>
						<span><%= cake.getCakeName() %></span><br>
						<span><%= cake.getCakePrice() %></span><br>
						<span>후기 갯수 : <%= cake.getReviewCount() %></span>
						<%-- <%for(int i=0;i<cake.getCakeAvgScore();i++) { %>
						<img src="/img/star1.jpg" width="11px">
						<% } %><br>
						<span><%= cake.getReviewContent() %></span></a> --%>
					</article>
					<% }
				 	}%>
				
				</section>
			</section>
			<br><br><br><br><br>
			<!-- 관리자 추천 -->
			<section class="cake-section">
				<h3>관리자 추천</h3>
				<section>
				
				 <% if(mdCake != null) {
				 	for(MainCakeInfo cake : mdCake) { %>
					<article class="cake-article">
					<a href="/cake/detail?cakeNo=<%=cake.getCakeNo()%>&shopId=<%=cake.getShopId()%>">
						<img src=<%= cake.getCakeFilePath() %> onerror="this.src='/img/cakeimg.png'" width="150" alt="케이크"/><br>
						<span><%= cake.getCakeName() %></span><br>
						<span><%= cake.getCakePrice() %></span><br>
						<%for(int i=0;i<cake.getCakeAvgScore();i++) {%>
						<img src="/img/star1.jpg" width="11px">
						<% } %></a>
						<!-- 별점 -->
					</article>
					<% }
				 	}%>
				
				</section>
			</section>
		
		</section>

	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>	
</body>
</html>