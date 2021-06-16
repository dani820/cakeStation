<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/boardPage.css">
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<main>
		<h1>문의 작성</h1>
		<form action="/board/write" method="post" enctype="multipart/form-data">
			<input type="text" size="100" placeholder="제목을 입력하세요" name="CS_TITLE">
			<textarea rows="30" cols="100" placeholder="내용을 입력하세요" name="CS_CONTENTS"></textarea>
		
			파일 지정하기 : <input type="file" name="upFile"><br>
			<input type="submit" value="업로드"><br>
			<input type="reset" value="취소">
		</form> 
	</main>
	
		
	<%@include file ="/WEB-INF/views/footer.jsp" %>
</body>
</html>