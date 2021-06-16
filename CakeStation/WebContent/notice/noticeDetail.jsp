<%@page import="notice.model.vo.Notice"%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	Notice notice = (Notice)request.getAttribute("notice");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>상세 페이지</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file ="/WEB-INF/views/header.jsp" %>

<div class="row">
<div class="col-xs-2 col-md-2"></div>
<div class="col-xs-8 col-md-8">
<h2 class="text-center">게시글 </h2><p>&nbsp;</p>
<table class="table">
<tr>
<td class="success">글번호</td>
<td><%=notice.getNotice_No() %></td>
</tr>

<tr>
<td class="success" >작성일</td>
<td><%=notice.getNotice_Date() %></td>
</tr>

<tr>
<td class="success">제목</td>
<td><%=notice.getNotice_Title() %></td>
</tr>

<tr>
<td class="success">내용</td>
<td><%=notice.getNotice_Contents()%></td>
</tr>
</table>
<%-- 	 <a href="/notice/modify?NOTICE_NO=<%= notice.getNotice_No()%>">수정</a> --%>
	<input type="button" class="btn btn-warning" value="수정하기" onclick="location.href='/notice/modify?NOTICE_NO=<%= notice.getNotice_No()%>'">          
	<!-- <a href="/notice/list">목록</a> -->
	<input type="button" class="btn btn-primary" value="목록보기" onclick="location.href='/notice/list'">
	<%-- <a href="/notice/delete?NOTICE_NO=<%= notice.getNotice_No()%>">삭제</a> --%>
	<input type="button" class="btn btn-danger" value="삭제하기" onclick="location.href='/notice/delete?NOTICE_NO=<%= notice.getNotice_No()%>'">
	<%-- 첨부파일 <img src=../upload/<%=notice.getNotice_FileName() %>><%=notice.getNotice_FileName() %></a>
	 --%>
	 첨부파일 <a href=../upload/<%=notice.getNotice_FileName() %>><%=notice.getNotice_FileName() %></a>
	 
	 <%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
</html>