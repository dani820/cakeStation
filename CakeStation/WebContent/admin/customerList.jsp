<%@page import="java.util.List"%>
<%@page import="user.model.vo.CustomerMember" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<CustomerMember> list = (ArrayList<CustomerMember>)request.getAttribute("cList");
	String pageNavi = (String)request.getAttribute("pageNavi");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>일반회원 관리</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="/css/cakeStation.css">
	    <link rel="stylesheet" href="/css/adminMyPage.css">
	</head>
	<body>
		<%@include file ="/WEB-INF/views/header.jsp" %>
	
		<main>
			<%@include file ="/WEB-INF/views/adminMyPageSidebar.jsp" %>
	        
			<section id="content-box">
				<h2 id="sub-title">일반 회원 관리</h2>
				<% if(list.isEmpty()) { %>
					<a id="list-null">회원 목록이 비어있습니다.</a>
				<% } %>
				<% if(!list.isEmpty()) { %>
					<table border="1" id="adminP-table" style="border-collapse:collapse">
						<tr>
							<th>회원 아이디</th>
							<th>회원 이름</th>
							<th>정보수정</th>
							<th>회원탈퇴</th>
						</tr>
						<% for(CustomerMember cOne : list) { %>
						<tr>
							<td><%= cOne.getMemberId() %></td>
							<td><%= cOne.getMemberName() %></td>
							<td><a href="/admin/customerInfo?user-id=<%= cOne.getMemberId() %>" >[수정]</a></td>
							<td><a href="/admin/customerDrop?user-id=<%= cOne.getMemberId() %>" >[탈퇴]</a></td>
						</tr>
						<% } %>
						<td colspan="4" align="center"><%= pageNavi %></td>
					</table>
				<% } %>
			</section>
		</main>
		
		<%@include file ="/WEB-INF/views/footer.jsp" %>
	</body>
</html>