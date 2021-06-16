<%@page import="order.model.vo.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Order> list = (ArrayList<Order>)request.getAttribute("oList");
	String pageNavi = (String)request.getAttribute("pageNavi");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="/css/reset.css">
		<link rel="stylesheet" href="/css/ctOrderList.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<title>마이페이지</title>
	</head>
	<body>
		<%@include file ="/WEB-INF/views/header.jsp" %>
		
		<main>
			<%@include file ="/WEB-INF/views/CtMyPageSidebar.jsp" %>
			
			<section id="content-box">
				<h2 id="sub-title">주문내역 관리</h2>
				<% if(list.isEmpty()) {%>
					<a id="list-null">주문 내역이 없습니다.</a>
				<% } %>
				<% if(!list.isEmpty()) {%>
					<% for(Order oOne : list) { %>
						<section id="order-info">
							<section id="img-zone">
			                    <li id="order-date"><%= oOne.getOrderDate() %></li>
			                    <img id="cake-img" src="<%= oOne.getCakeFilePath() %>">
			                    <li style="text-align: center"><button type="button" onclick="location.href='/review/write?cakeNo=<%= oOne.getCakeNo() %>&currShopId=<%=oOne.getShopId() %>&cakeName=<%=oOne.getCakeName() %>&cakePrice=<%=oOne.getCakePrice() %>&cakeFilePath=<%=oOne.getCakeFilePath()%>'" id="button">후기작성</button></li>
		                	</section>
			                <section id="info-zone">
			                	<br>
			                	<table>
			                		<tr>
			                			<td class="label">예약번호</td>
			                			<td><%= oOne.getOrderNo() %></td>
			                		</tr>
			                		<tr>
			                			<td class="label">케이크 이름</td>
			                			<td><%= oOne.getCakeName() %></td>
			                		</tr>
			                		<tr>
			                			<td class="label">가격</td>
			                			<td><%= oOne.getCakePrice() %>원</td>
			                		</tr>
			                		<tr>
			                			<td class="label">수량</td>
			                			<td><%= oOne.getCakeAmount() %></td>
			                		</tr>
			                		<tr>
			                			<td class="label">주문자</td>
			                			<td><%= oOne.getOrdererName() %></td>
			                		</tr>
			                		<tr>
			                			<td class="label">주문자 연락처</td>
			                			<td><%= oOne.getOrdererPhone() %></td>
			                		</tr>
			                		<% if(oOne.getDeliveryType().equals("픽업")) { %>
				                		<tr>
				                			<td class="label">픽업</td>
				                			<td><%= oOne.getDeliveryDate() %> <%= oOne.getPickupTime() %></td>
				                		</tr>
			                		<% } %>
			                		<% if(oOne.getDeliveryType().equals("배달")) { %>
				                		<tr>
					                		<td class="label">배달주소</td>
					                		<td><%= oOne.getDeliveryAddr() %></td>
					                	</tr>
			                		<% } %>
			                		<tr>
				                		<td colspan="2" class="label">▼ 요구사항</td>
				                	</tr>
				                	<tr>
					                	<td colspan="2"><input type="text" value="<%= oOne.getRequest() %>" readonly></td>
					                </tr>
			                	</table>
			                </section>
						</section>
					<% } %>
					<section id="pageNavi"><%= pageNavi %></section>
				<% } %>
			</section>
		</main>
		
		<%@include file ="/WEB-INF/views/footer.jsp" %>
	</body>
</html>