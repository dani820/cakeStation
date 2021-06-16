<%@page import="review.model.vo.Review"%>
<%@page import="review.model.vo.ReviewAndComm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cakeinfo.model.vo.CakeAndShop"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//cake상세
	CakeAndShop cake = (CakeAndShop)request.getAttribute("cakeAndShop");
	//구매후기
	ArrayList<ReviewAndComm> rcList = (ArrayList<ReviewAndComm>)request.getAttribute("rcList");

	int reviewFlag = (Integer)request.getAttribute("reviewFlag");
	int btnFlag = (Integer)request.getAttribute("btnFlag");
	
	HttpSession session2 = request.getSession();
	Review review = (Review)request.getAttribute("review");
	
	String csId = (String)session.getAttribute("userId");

%>    
    <!-- 객체 두개 가져와야함. (cakeandshop, 구매후기+후기댓글)
    	버튼 사라지게 하기 위해 session 가져와야함.
    -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cake Station</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/cakeStation.css">
<link rel="stylesheet" href="/css/cakeDetail.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<%@include file ="/WEB-INF/views/header.jsp" %>
	
	<!-- content -->
	<main id="main">
		<section class=wrapper id=wrapper>
			<section id="blank"></section>
			<section id="subject">
				<section id="subject-left">
					<img src = "<%=cake.getCakeFilePath() %>" width=350px/>
				</section>
				<section id="subject-right">
					<span id="cake-info1"><%=cake.getCakeName() %></span>
					&nbsp;&nbsp;&nbsp;
					<a href="<%=cake.getChatUrl()%>"><img src="/img/kakaotalk.JPG" width="40px"/></a>
					<br>
					<div id=cake-info>
					<p id="cake-info2"><%=cake.getCakePrice() %> 원</span><br>
					<p id="cake-info3"><%=cake.getShopName() %></p>
					<p id="cake-info4"><%=cake.getShopLAdd() %></p>
					<%for(int i=0;i<cake.getCakeAvgScore();i++) {%>
					<img src="/img/star1.jpg" width="11px">
					<% } %>
					<span id="cake-info5"><%=cake.getCakeAvgScore() %></span>
					</div>
					<!-- 주문하기 -->
					<% if(userId != null) { %>
						<form action="/order/write" method="get" onsubmit="return radio_check()">
					<% } else { %>
						<form action="/member/login.jsp" method="get" onsubmit="return login_check()">
					<% } %>
						<div id="order-select">
							<span class="font-bold">수령방법 :</span>&nbsp;&nbsp;&nbsp;
							<% if(cake.getDayRec().equals("전체")) { %>
							<input type=radio name="pickup-method" value="픽업"> 픽업&nbsp;&nbsp;&nbsp; 
							<input type=radio name="pickup-method" value="배송"> 배송&nbsp;&nbsp;&nbsp;
							<% } %>
							<% if(cake.getDayRec().equals("픽업")) { %>
							<input type=radio name="pickup-method" value="픽업"> 픽업&nbsp;&nbsp;&nbsp;  
							<% } %>
							<% if(cake.getDayRec().equals("배송")) { %>
							<input type=radio name="pickup-method" value="배송"> 배송&nbsp;&nbsp;&nbsp; 
							<% } %>
							<% if(cake.getDayPickUp().equals("Y")) { %>
								<input type=radio name="pickup-method" value="당일 수령"> 당일 수령
							<% } %>
							<!-- 주문하기 클릭시: hidden으로 넘겨줘야하는 부분: 제품번호, 케이크명, 가격, 업체아이디, 업체명(객체에서 꺼내기)
							get으로 간다. -->
							<div>
								<span class="font-bold">수량 :&nbsp;&nbsp;&nbsp;</span><span class="minus"><a href="javascript:change_qty('minus')">-</a></span>&nbsp;&nbsp;&nbsp;
								<input type="text" name="qty" id="qty" value="1" style="width:30px;text-align:center" readonly="readonly">
								&nbsp;&nbsp;&nbsp;<span class="plus"><a href="javascript:change_qty('plus')">+</a></span>
							</div>
							<input type="hidden" name="cakeNo" value="<%=cake.getCakeNo() %>">
							<input type="hidden" name="cakeName" value="<%=cake.getCakeName() %>">
							<input type="hidden" name="cakePrice" value="<%=cake.getCakePrice() %>">
							<input type="hidden" name="currShopId" value="<%=cake.getShopId() %>">
							<input type="hidden" name="shopName" value="<%=cake.getShopName() %>">
							<input type="hidden" name="cakeFilePath" value="<%=cake.getCakeFilePath() %>">
							<input type="hidden" name="openingStart" value="<%=cake.getOpeningStart() %>">
							<input type="hidden" name="openingEnd" value="<%=cake.getOpeningEnd() %>">
							<input type="hidden" name="userId" value="<%=userId %>">
							<!-- <div>
							<select name="pickup" id="pickup">
								<option value="scoredesc">픽업</option>
								<option value="scoreasc">배송</option>
							</select>
							</div>	 -->
							
							<div id="order-btn"><input type="submit" id="cakedetail-order" value="주 문 하 기"></div>
						</div>
					</form>
				</section>
			</section>
			<hr>
			<section id="contents">
				<article id="cake-contents">
					<p id="ourCake">우리 케이크는 </p>
					<br>
					<article id="cakePR"><span><%=cake.getCakeChar() %></span></article>
				</article>
			</section>
			<hr>
			<section id="review">
				<% if(btnFlag==1) { %>
				<div id="review-btn"><a href="/review/write?cakeNo=<%=cake.getCakeNo() %>&currShopId=<%=cake.getShopId() %>&cakeName=<%=cake.getCakeName() %>&cakePrice=<%=cake.getCakePrice() %>&cakeFilePath=<%=cake.getCakeFilePath() %>" target="_blank" align="left"><input type="button" id="cakedetail-review" value="후기 작성" name="review"></a></div>
				<% } %>
				<section id="review-subject"><p>구매후기</p></section>
				<br><br><br>
				<section id="reivew-contents" style="-ms-overflow-style: none;">
				<% if(reviewFlag==1) {%>
				<table id="review-board">
					<tr><td colspan="8" height="15px"><hr></td></tr>
					<% for(ReviewAndComm rc : rcList) { %>
					<tr>
						<td width="100px" style="font-weight:bold;"><%=rc.getMemberId() %></td>
						<td width=100px><%for(int i=0;i<rc.getReviewScore();i++) {%>
						<img src="/img/star1.jpg" width="13px">
						<% } %></td>
						<td colspan="5" width="300px"></td>
						<td><%=rc.getReviewDate() %></td>
						<td><% if (rc.getMemberId().equals(csId)){ %>
                        <a href="/review/modify?REVIEW_NO=<%= rc.getReviewNo()%>&cakeName=<%=cake.getCakeName() %>&shopName=<%=cake.getShopName() %>&cakeFilePath=<%=cake.getCakeFilePath()%>&cakeNo=<%=cake.getCakeNo() %>&currShopId=<%=cake.getShopId() %>" target="_blank">수정</a>&nbsp;&nbsp;&nbsp;
                        <a href="/review/delete?REVIEW_NO=<%= rc.getReviewNo()%>&cakeName=<%=cake.getCakeName() %>&shopName=<%=cake.getShopName() %>&cakeNo=<%=cake.getCakeNo() %>&currShopId=<%=cake.getShopId() %>">삭제</a>
                  <%} %></td>
					</tr>
					<tr>
						<td colspan=8>&nbsp;&nbsp;&nbsp;<%=rc.getReviewContent() %></td>
					</tr>
						<%if(rc.getReplyContents()!=null) {%>
							<tr>
								<td></td>
								<td colspan="7">└ <%=rc.getReplyContents() %></td>
							</tr>
							<tr>
								<td colspan="6"></td>
								<td style="font-weight:bold;text-align:center;">사장님</td>
								<td><%=rc.getReplyDate() %></td>
							</tr>
						<% } %>
						<tr><td colspan="8" height="15px"><hr></td></tr>
					<% } %>
				</table>
				<% } else {%>
					<h3>작성된 후기가 없습니다.</h3>
				<% } %>
				</section>
			</section>
			<!-- <input type="button" id="more" value="더보기"> -->
		</section>
		
	</main>
	<%@include file ="/WEB-INF/views/footer.jsp" %>
	
<script>
/* 수량버튼 */
function change_qty(calc){
	var min_qty = 1;
	var this_qty = $("#qty").val()*1;
	var max_qty = 100;
	if(calc=="minus") {
		this_qty -=1;
		if(this_qty < min_qty) {
			//alert("수량은 1개 이상 입력해주세요.");
			return;
		}
	}else if(calc=="plus") {
		this_qty += 1;
		if(this_qty > max_qty) {
			//alert("재고가 부족합니다.");
			return;
		}
	}
	
	$("#qty").val(this_qty);
}

/* 주문서 라디오 버튼 체크 여부 확인 */
function radio_check() {
	if($("input:radio[name='pickup-method']").is(":checked") == false) {
		alert("배송방법을 선택해주세요.");
		return false;
	}
}

/* 주문 전 로그인 여부 확인 */
function login_check() {
	if(<%=userId%> == null) {
		alert("로그인이 필요합니다.");
		return false;
	}
}

$("#more").on("click", function(e){
    e.preventDefault();
    $("#review").height("auto");
});

</script>
</body>
</html>