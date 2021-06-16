<%@page import="notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
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
<h2 class="text-center">공지사항 작성 </h2><p>&nbsp;</p>

	<form action="/notice/write" method="post" enctype="multipart/form-data">
	글제목 <input type="text" size="91" placeholder="제목을 입력하세요" name="NOTICE_TITLE"><br>
	<textarea rows="30" cols="100" placeholder="내용을 입력하세요" name="NOTICE_CONTENTS"></textarea><br><br>
		<input type="file" name="NOTICE_FILENAME"> 
		<input type="submit" value="완료">
		<input type="reset" value="취소">
	</form>
	
	<%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
</html>