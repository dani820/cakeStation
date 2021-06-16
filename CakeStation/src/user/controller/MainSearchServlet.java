package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cakeinfo.model.service.CakeInfoService;
import cakeinfo.model.vo.MainCakeInfo;
import cakeinfo.model.vo.MainSearchCakeInfo;

/**
 * Servlet implementation class MainSearchServlet
 */
@WebServlet("/member/mainsearch")
public class MainSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String searchVal = request.getParameter("search");
		
		ArrayList<MainSearchCakeInfo> searchCake = new CakeInfoService().searchMain(searchVal);
		
		ArrayList<MainCakeInfo> avgCake = new CakeInfoService().mainAvgCake();// 별점(평점순)
		ArrayList<MainCakeInfo> reviewCake = new CakeInfoService().mainReviewCake();// 리뷰순
		ArrayList<MainCakeInfo> mdCake = new CakeInfoService().mainMDCake();// 관리자순(가격 낮은 순)
		
		
		if(!searchCake.isEmpty() && !avgCake.isEmpty() && !mdCake.isEmpty() && !reviewCake.isEmpty()) {
			request.setAttribute("searchVal", searchVal);
			request.setAttribute("searchCake", searchCake); // 조건검색순
			
			request.setAttribute("avgCake", avgCake);
			request.setAttribute("reviewCake", reviewCake);
			request.setAttribute("mdCake", mdCake);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
			view.forward(request, response);
		}else {
			response.sendRedirect("/member/errorPage.jsp"); // 실패
		}
		
		
		/*
		 // 별점(평점순)
		if(!avgCake.isEmpty() && !mdCake.isEmpty()) {
			request.setAttribute("avgCake", avgCake);
			request.setAttribute("reviewCake", reviewCake); // 리뷰순
			request.setAttribute("mdCake", mdCake);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
			view.forward(request, response);
		}else {
			response.sendRedirect("/member/memberError.html"); // 실패
		}
	*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
