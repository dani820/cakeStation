<%@page import="shop.model.vo.ShopAndImg"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ArrayList<ShopAndImg> siList = (ArrayList<ShopAndImg>)request.getAttribute("siList");
	String pageNavi = (String)request.getAttribute("pageNavi");
%>
    <!-- cakeAndShop -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cake Station</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/cakeStation.css">
<link rel="stylesheet" href="/css/shopMemList.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<!-- content -->
	<main id="main">
		<section id= "subject">
			<h1>매장 찾기</h1>
			<br><br>
		</section>	
		<section id = "filter">
			<form action=/shop/search method="get">
				<select name="area" id="area">
					<option value="all"> 전체&nbsp;&nbsp;&nbsp;
					<option value="서울"> 서울&nbsp;&nbsp;&nbsp;
					<option value="경기"> 경기&nbsp;&nbsp;&nbsp;
					<option value="강원"> 강원&nbsp;&nbsp;&nbsp;
					<option value="경상북도"> 경북&nbsp;&nbsp;&nbsp;
					<option value="경상남도"> 경남&nbsp;&nbsp;&nbsp;
					<option value="전라북도"> 전북&nbsp;&nbsp;&nbsp;
					<option value="전라남도"> 전남&nbsp;&nbsp;&nbsp;
					<option value="충청북도"> 충북&nbsp;&nbsp;&nbsp;
					<option value="충청남도"> 충남&nbsp;&nbsp;&nbsp;
					<option value="제주도"> 제주 <br>
						<!-- 서블릿에서 if문으로 메소드 서로 다르게 호출 -->
				</select>&nbsp;&nbsp;&nbsp;
				<select name="array" id="array">
					<option value="SHOP_AVG_SCORE">평점 높은 순</option>
					<option value="RCOUNT">후기 많은 순</option>
				</select>&nbsp;&nbsp;&nbsp;
				<input type=submit value="찾 기">
			</form>
		</section>
		<section id = "product-list">
 	 		<table id="shop">
	 			<%for(ShopAndImg shop : siList) { %>
	 			<tr>
	 				<td><a href="/shop/detail?shopId=<%=shop.getShopId()%>"><img id="shop-img" src = "<%=shop.getShopFilePath() %>" width=300px height=300px></a></td>
	 				<td width="360px">
	 					<div id="shop-name">
	 						<a href="/shop/detail?shopId=<%=shop.getShopId()%>"><h2><%=shop.getShopName() %></h3></a>
			 			</div>	
	 					<article id=shop-article>
			 				<a href="/shop/detail?shopId=<%=shop.getShopId()%>"><%=shop.getIntroduced() %></a>
			 				<%-- <img src = "/img/star1.jpg" width="14px"/> &nbsp;
			 				<span style="font-weight:bold"><%=shop.getShopAvgScore() %></span> --%>
		 				</article>
		 			</td>
				</tr>
				<tr><td colspan=2 height=50px></td></tr>
				<% } %>
				<tr height="50px">
					<td colspan="2" align="center">
						<%=pageNavi %>
					</td>					
				</tr>
	 		</table>
		</section>
	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
</html>