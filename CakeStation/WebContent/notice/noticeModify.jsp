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
<title>공지사항 수정</title>
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<h1>공지 수정</h1>
	<form action="/notice/modify" method="post">
		글제목 <input type="text" value="<%= notice.getNotice_Title() %>" name="NOTICE_TITLE"><br><br>
		글 내용<textarea rows="30" cols="100" name="NOTICE_CONTENTS"><%= notice.getNotice_Contents() %></textarea><br><br>
		<input type="hidden" value="<%= notice.getNotice_No() %>" name="NOTICE_NO">
		<input type="submit" value="수정완료">
		<input type="reset" value="취소">
		
		
	</form>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
</html>