package cakeinfo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cakeinfo.model.service.CakeInfoService;
import cakeinfo.model.vo.CakeAndShop;
import review.model.service.ReviewService;
import review.model.vo.ReviewAndComm;


/**
 * Servlet implementation class CakeInfoDetailServlet
 */
@WebServlet("/cake/detail")
public class CakeInfoDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CakeInfoDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		//int currentPage = 0;
		//if(request.getParameter("currentPage") == null) {
		//	currentPage = 1;
		//}else {
		//	currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//}
		
		//cakeinfolist.jsp에서 넘어옴
		int cakeNo = Integer.parseInt(request.getParameter("cakeNo")); //jsp arraylist값에서 가져옴
		String shopId = (String)(request.getParameter("shopId")); // 상동
		System.out.println(cakeNo + shopId);
		
		//케익정보
		CakeAndShop cakeAndShop = new CakeInfoService().printOne(cakeNo, shopId);
		//해당 케익의 후기
		ArrayList<ReviewAndComm> rcList = new ReviewService().printSearchList(cakeNo);
		int reviewFlag = 0;
		if(!rcList.isEmpty()) {
			reviewFlag = 1;
			request.setAttribute("rcList", rcList);
			request.setAttribute("reviewFlag", reviewFlag);
		} else {

			request.setAttribute("reviewFlag", reviewFlag);
		}
		
		//후기작성 버튼 없애기
		HttpSession session = request.getSession();
		int btnFlag = 0;
		if(session != null && (session.getAttribute("userId")) != null) {
			btnFlag = 1;
			request.setAttribute("btnFlag", btnFlag);
		} else {
			request.setAttribute("btnFlag", btnFlag);
		}
		
		//setAttribute로 다 넣어서 detail.jsp로 이동
		if(cakeAndShop != null) { //나머지는
			//확인용
			System.out.println("service_케익정보:" + cakeAndShop.toString());
			System.out.println("service_후기:" + rcList.toString());
			System.out.println("service_리뷰null버튼플래그:" + reviewFlag);
			System.out.println("service_후기버튼플래그:" + btnFlag);
			
			request.setAttribute("cakeAndShop", cakeAndShop);
			System.out.println("서블릿" + request.getAttribute("cakeAndShop").toString());
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/cakeInfo/cakeDetail.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/cakeInfo/cakeInfoError.html");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
