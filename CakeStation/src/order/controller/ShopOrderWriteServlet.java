package order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import order.model.service.ShopOrderService;
import order.model.vo.Order;

/**
 * Servlet implementation class ShopOrderWriteServlet
 */
@WebServlet("/order/write")
public class ShopOrderWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopOrderWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//cakedetail.jsp에서 hidden으로 넘어온 정보와 수령방법 선택된 정보가 request에 담겨있음.
		request.setCharacterEncoding("UTF-8");

		String pickupMethod = request.getParameter("pickup-method");
		request.setAttribute("pickupMethod", pickupMethod);
		
		int qty = Integer.parseInt(request.getParameter("qty"));
		request.setAttribute("qty", qty);
		
		int cakeNo = Integer.parseInt(request.getParameter("cakeNo"));
		request.setAttribute("cakeNo", cakeNo);
		
		String cakeName = request.getParameter("cakeName");
		request.setAttribute("cakeName", cakeName);
		
		int cakePrice = Integer.parseInt(request.getParameter("cakePrice"));
		request.setAttribute("cakePrice", cakePrice);
		
		String currShopId = request.getParameter("currShopId");
		request.setAttribute("currShopId", currShopId);
		
		String shopName = request.getParameter("shopName");
		request.setAttribute("shopName", shopName);
		
		String cakeFilePath = request.getParameter("cakeFilePath");
		request.setAttribute("cakeFilePath", cakeFilePath);
		
		int openingStart = Integer.parseInt(request.getParameter("openingStart"));
		request.setAttribute("openingStart", openingStart);
		
		int openingEnd = Integer.parseInt(request.getParameter("openingEnd"));
		request.setAttribute("openingEnd", openingEnd);
		
		String userId = request.getParameter("userId");
		
		HttpSession session = request.getSession();
		//if(session != null && (session.getAttribute("userId")) != null) {
		if(session != null && userId!=null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/orderForm.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/serviceFailed.html");
			view.forward(request, response);			
		}
		//else "로그인이 필요합니다"
		// 위 방법 아니면 detail에서 세션체크
		System.out.println("오더서블릿:"+ currShopId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String contents = request.getParameter("contents");
		String pickupMethod = request.getParameter("pickupMethod");
		String payType = request.getParameter("payType");
		String deliveryDate = request.getParameter("pickup-date");
		String pickupTime = request.getParameter("pickup-time");
		int qty = Integer.parseInt(request.getParameter("qty"));
		int cakeNo = Integer.parseInt(request.getParameter("cakeNo"));
		String currShopId = request.getParameter("currShopId");
		String orderer = request.getParameter("orderer");
		String addr = request.getParameter("addr");
		String phone = request.getParameter("phone");
		String userId = request.getParameter("userId");
		HttpSession session = request.getSession();
		//if(session != null && (session.getAttribute("userId")) != null) {
		if(session != null && userId!=null) {
			Order order = new Order();
			order.setMemberId(userId);
			order.setRequest(contents);
			order.setDeliveryType(pickupMethod);
			order.setPayType(payType);
			
//			SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
//			Date deliyDate = formatter.parse(deliveryDate);

//			SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd"); // 날짜데이터를 내가 원하는 형태로 바꿔줌
//			Date deliyDate = Date.valueOf(formatter.format(deliveryDate));
			order.setDate(deliveryDate);
			order.setPickupTime(pickupTime);
			order.setCakeAmount(qty);
			order.setCakeNo(cakeNo);
			order.setShopId(currShopId);
			order.setOrdererName(orderer);
			order.setDeliveryAddr(addr);
			order.setOrdererPhone(phone);
			System.out.println(userId);
			System.out.println(currShopId);
			
			int result = new ShopOrderService().registerOrder(order);
			if (result>0) {
				//response.sendRedirect("/customer/orderList");
				//response.sendRedirect("/cake/list");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('주문 성공! 마이페이지 주문 목록을 확인해주세요.'); location.href='/cake/detail?cakeNo="+ cakeNo + "&shopId=" + currShopId + "';</script>");
				out.flush();
			}else {
//				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/orderError.html");
//				view.forward(request, response);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('주문을 실패하였습니다.'); location.href='/cake/detail?cakeNo="+ cakeNo + "&shopId=" + currShopId + "';</script>");
				out.flush();
			}

//			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
//			Date deliDate = transFormat.parse(format);

		}else {
//			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/serviceFailed.html");
//			view.forward(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후 주문해주세요.'); location.href='/cake/detail?cakeNo="+ cakeNo + "&shopId=" + currShopId + "';</script>");
			out.flush();
		}
		
	}

}
