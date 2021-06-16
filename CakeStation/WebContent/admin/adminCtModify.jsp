<%@page import="user.model.vo.CustomerMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CustomerMember cMember = (CustomerMember)request.getAttribute("cMember");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>관리자 마이페이지</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/css/cakeStation.css">
        <link rel="stylesheet" href="/css/adminMyPage.css">
	<head>
		<%@include file ="/WEB-INF/views/header.jsp" %>
	
		<main>
			<%@include file ="/WEB-INF/views/adminMyPageSidebar.jsp" %>	        
	        <section id="content-box">
	        	<h2 id="sub-title">일반회원 정보수정</h2>
				<form action="/admin/customerModify" method="post">
					<fieldset>
						<ul>
							<li>아이디<br><input type="text" value="<%= cMember.getMemberId() %>" readonly name="user-id"></li>
							<li>비밀번호<br><input type="password" value="<%= cMember.getMemberPwd() %>" name="user-pwd"></li>
							<li>이름<br><input type="text" value="<%= cMember.getMemberName() %>" name="user-name"></li>
							<li>이메일<br><input type="text" value="<%= cMember.getMemberEmail() %>" name="user-email"></li>
							<li>전화번호<br><input type="text" value="<%= cMember.getMemberPhone() %>" name="user-phone"></li>
							<li>주소<br><input type="text" value="<%= cMember.getMemberAddress() %>" name="user-address"></li>
							<br><input type="submit" value="수정">
							<input type="reset" value="취소">
						</ul>
					</fieldset>
				</form>
	        </section>
	    </main>
	    
	    <%@include file ="/WEB-INF/views/footer.jsp" %>
	</body>
</html>