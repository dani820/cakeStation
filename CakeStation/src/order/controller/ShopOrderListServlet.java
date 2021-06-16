package order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import order.model.service.ShopOrderService;
import order.model.vo.Order;
import order.model.vo.PageData;

/**
 * Servlet implementation class ShopOrderListServlet
 */
@WebServlet("/shopMy/orderList")
public class ShopOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopOrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); // 업체로그인 세션정보 가져오기 
		String shopId = (String)session.getAttribute("shopId");
		
		
		
//		String shopId = request.getParameter("shopId");
		
		// 로그인 했을 경우
		if(shopId != null) {
//			String shopId = "TEST05";
			
			// 현재 페이지 값 	
			int currentPage = 0; 
			if(request.getParameter("currentPage") == null) {
				currentPage = 1;
			}else {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			PageData pageData = new ShopOrderService().printAllOrder(currentPage, shopId); // 가져올 예약목록 전체와 페이지 네비 값
			
			ArrayList<Order> orderList = pageData.getOrderList(); // 예약 목록을 5개씩 가져옴
			String pageNavi = pageData.getPageNavi(); // 페이지 네비 1-10
			
			request.setAttribute("orderList", orderList);
			request.setAttribute("pageNavi", pageNavi);
			
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/shopMyPageMain.jsp").forward(request, response);
		
		
		}else {
		// 로그인을 하지 않았을 경우
			response.sendRedirect("/shop/login");
//			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
//