<%@page import="java.util.ArrayList"%>
<%@page import="cakeinfo.model.vo.CakeAndShop"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	ArrayList<CakeAndShop> csList = (ArrayList<CakeAndShop>)request.getAttribute("csList");
	String pageNavi = (String)request.getAttribute("pageNavi");
/* 	
	String cakeType = (String)request.getAttribute("kinds");
	String cakePrice = (String)request.getAttribute("price");
	String shopLAdd = (String)request.getAttribute("area");
	String dayRec = (String)request.getAttribute("pickup");
	String dayPick = (String)request.getAttribute("daypick");
	String array = (String)request.getAttribute("array"); */
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cake Station</title>
<link rel="stylesheet" href="/css/cakeStation.css">
<link rel="stylesheet" href="/css/cakeInfoList.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<!-- content -->
	<main id="main">
		<section id="blank">
			<h1>케이크 찾기</h1>
		</section>
		<section id= "search-bar">
			<div id="radio-set">
			<form action = "/cake/search" method="get">
				<span>종&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;류:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="kinds" value="all"> 전체&nbsp;&nbsp;&nbsp;
				<input type="radio" name="kinds" value="버터크림케이크"> 버터크림 케이크&nbsp;&nbsp;&nbsp;   
				<input type="radio" name="kinds" value="생크림케이크"> 생크림 케이크&nbsp;&nbsp;&nbsp;
				<input type="radio" name="kinds" value="꽃케이크"> 꽃 케이크&nbsp;&nbsp;&nbsp;
				<input type="radio" name="kinds" value="레터링케이크"> 레터링 케이크&nbsp;&nbsp;&nbsp;
				<input type="radio" name="kinds" value="떡케이크"> 떡 케이크&nbsp;&nbsp;&nbsp;
				<input type="radio" name="kinds" value="비건"> 비건&nbsp;&nbsp;&nbsp;
				<input type="radio" name="kinds" value="기타"> 기타   <br>
				<span>가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;격:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="price" value="all"> 전체&nbsp;&nbsp;&nbsp;
				<input type="radio" name="price" value="0"> 1만원  미만&nbsp;&nbsp;&nbsp;
				<input type="radio" name="price" value="10000"> 1만원대&nbsp;&nbsp;&nbsp;
				<input type="radio" name="price" value="20000"> 2만원대&nbsp;&nbsp;&nbsp;
				<input type="radio" name="price" value="30000"> 3만원대&nbsp;&nbsp;&nbsp;
				<input type="radio" name="price" value="40000"> 4만원대&nbsp;&nbsp;&nbsp;
				<input type="radio" name="price" value="50000"> 5만원대이상   <br>
				<span>지&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;역:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="area" value="all"> 전체&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="서울"> 서울&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="경기"> 경기&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="강원"> 강원&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="경상북도"> 경북&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="경상남도"> 경남&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="전라북도"> 전북&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="전라남도"> 전남&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="충청북도"> 충북&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="충청남도"> 충남&nbsp;&nbsp;&nbsp;
				<input type="radio" name="area" value="제주도"> 제주 <br>
				<span>수령방법:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="pickup" value="all"> 전체&nbsp;&nbsp;&nbsp;
				<input type="radio" name="pickup" value="픽업"> 픽업&nbsp;&nbsp;&nbsp;
				<input type="radio" name="pickup" value="배송"> 배송&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br><span>당일수령 서비스:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;전체 <input type="radio" name="daypick" value="all">&nbsp;&nbsp;&nbsp; 유 <input type="radio" name="daypick" value="Y">&nbsp;&nbsp;&nbsp;무 <input type="radio" name="daypick" value="N"><br><br>
				</div>
				<div id=array-select>
				<span style="font-weight:bold">정&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;렬:</span>&nbsp;&nbsp;&nbsp;<select name="array" id="array">
					<option value="CAKE_AVG_SCORE">평점 높은 순</option>
					<option value="RCOUNT">후기 많은 순</option>
				</select>
				</div>
				<div id=submit-btn>
				<input type="submit" value="찾 기">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="재 설 정"><br><br><br>
			</form>
			</div>
			<hr>
			<div id="allCake">
			<a href="/cake/list"><input type="button" value="모 든 케 이 크"></a>
			</div>
		</section>	
		<section id = "product-list">
			<table id="cake">
				<tr>
				<% int size = csList.size();
					int check=0;
					if(size%3==1) {
						for(int i=0;i<size;i++) { 
							CakeAndShop cake = csList.get(i); %>
						<td height="360px">
							<a href="/cake/detail?cakeNo=<%=cake.getCakeNo()%>&shopId=<%=cake.getShopId()%>">
							<input type="hidden" id="path" value="<%=cake.getCakeFilePath()%>">
							<img src = "<%=cake.getCakeFilePath() %>" height=300px width=300px> <br>
							<span style="font-weight:bold;font-size:18px;"><%=cake.getCakeName() %></span><br>
							<span><%=cake.getCakePrice() %> 원</span><br>
							<span><%=cake.getShopName() %></span></a>
						</td>
						<% check ++; 
						if(check%3==0) { %>
					</tr>
					<tr>			
						<% }
						}
						for(int i=0;i<2;i++) { %>
							<td></td>
					<% }

					}%> </tr>
					
					<% if(size%3==2) {
						for(int i=0;i<size;i++) { 
							CakeAndShop cake = csList.get(i); %>
						<td height="360px">
							<a href="/cake/detail?cakeNo=<%=cake.getCakeNo()%>&shopId=<%=cake.getShopId()%>">
							<input type="hidden" id="path" value="<%=cake.getCakeFilePath()%>">
							<img src = "<%=cake.getCakeFilePath() %>" width=300px> <br>
							<span style="font-weight:bold;font-size:18px;"><%=cake.getCakeName() %></span><br>
							<span><%=cake.getCakePrice() %> 원</span><br>
							<span><%=cake.getShopName() %></span></a>
						</td>
						<% check ++; 
						if(check%3==0) { %>
					</tr>
					<tr>			
						<% }
						} %>
						<td></td>
					<% }%> </tr>
					
					<% if(size%3==0) {
						for(int i=0;i<size;i++) { 
							CakeAndShop cake = csList.get(i); %>
						<td height="360px">
							<a href="/cake/detail?cakeNo=<%=cake.getCakeNo()%>&shopId=<%=cake.getShopId()%>">
							<input type="hidden" id="path" value="<%=cake.getCakeFilePath()%>">
							<img src = "<%=cake.getCakeFilePath() %>" width=300px> <br>
							<span style="font-weight:bold;font-size:18px;"><%=cake.getCakeName() %></span><br>
							<span><%=cake.getCakePrice() %> 원</span><br>
							<span><%=cake.getShopName() %></span></a>
						</td>
						<% check ++; 
						if(check%3==0) { %>
					</tr>
					<tr>			
						<% }
						}
					} %>
					</tr>
					<tr height="50px">
					<td colspan="3" align="center">
								<%=pageNavi %>
					</td>
				</tr>
			</table>
			<br>

		</section>
	</main>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
</html>