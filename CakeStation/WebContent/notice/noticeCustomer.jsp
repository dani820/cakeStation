<%@page import="notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Notice> nList = (ArrayList<Notice>) request.getAttribute("nList");
	String pageNavi = (String) request.getAttribute("pageNavi");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항</title>
		<meta name="viewport" content="width-device-width", initial-scale="1">
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	</head>
	<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
		<main>
			<div class="row">
			<div class="col-xs-2 col-md-2"></div>
			<div class="col-xs-8 col-md-8"></div>
			<h2 class="text-center">공지사항 </h2><p>&nbsp;</p>
		
			<div class="container">
				<div class="row">
					<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
						<tr>
							<th style="background-color: #eeeeee; text-align: center;">글번호</th>
							<th style="background-color: #eeeeee; text-align: center;">글제목</th>
							<th style="background-color: #eeeeee; text-align: center;">글쓴이</th>
							<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						</tr>
						<%
							if (!nList.isEmpty()) {
							for (Notice notice : nList) {
						%>
						<tr>
							<td><%=notice.getNotice_No()%></td>
							<td><a href="/notice/detail?NOTICE_NO=<%=notice.getNotice_No()%>">
									<%=notice.getNotice_Title()%></a></td>
							<td><%=notice.getMember_Id()%></td>
							<td><%=notice.getNotice_Date()%></td>
						</tr>
						<%
							}
						} else {
						%>
						<tr>
							<td colspan="4">등록된 게시글이 없습니다</td>
						</tr>
						<%
							}
						%>
						<tr>
							<td colspan="3" align="center">
								<form action="/notice/search" method="get">
									<input type="text" name="searchKeyword"> <input
										type="submit" value="검색">
								</form>
							</td>
							<td>
								<form action="/notice/write" method="get">
									<input type="submit" value="글쓰기">
								</form>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center"><%=pageNavi%></td>
						</tr>
					</table>
				</div>
			</div>
		</main>
		<!-- 부트스트랩 참조 영역 -->
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="js/bootstrap.js"></script>
		<%@include file ="/WEB-INF/views/footer.jsp" %>
	</body>
</html>