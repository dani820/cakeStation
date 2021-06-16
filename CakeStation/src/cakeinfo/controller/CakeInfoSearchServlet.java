package cakeinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cakeinfo.model.service.CakeInfoService;
import cakeinfo.model.vo.CakeAndShop;
import cakeinfo.model.vo.PageData;

/**
 * Servlet implementation class CakeInfoSearchServlet
 */
@WebServlet("/cake/search")
public class CakeInfoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CakeInfoSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //케이크검색 버튼 눌렀을 때
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("kinds"));
		System.out.println(request.getParameter("price"));
		System.out.println(request.getParameter("area"));
		System.out.println(request.getParameter("pickup"));
		System.out.println(request.getParameter("daypick"));
		System.out.println(request.getParameter("array"));
		
		int currentPage = 0;
		if(request.getParameter("currentPage")==null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
				
		String cakeType = request.getParameter("kinds");
		String cakePrice = request.getParameter("price");
		String shopLAdd = request.getParameter("area");
		String dayRec = request.getParameter("pickup");
		String dayPick = request.getParameter("daypick");
		String array = request.getParameter("array");
//		request.setAttribute("kinds", cakeType);
//		request.setAttribute("price", cakePrice);
//		request.setAttribute("area", shopLAdd);
//		request.setAttribute("pickup", dayRec);
//		request.setAttribute("daypick", dayPick);
//		request.setAttribute("array", array);
		
		if(cakeType==null)
			cakeType="all";
		if(cakePrice==null)
			cakePrice="all";
		if(shopLAdd==null)
			shopLAdd="all";
		if(dayRec==null)
			dayRec="all";
		if(dayPick==null)
			dayPick="all";
		// 
		PageData pageData = new CakeInfoService().printSearchList(cakeType, shopLAdd, cakePrice, dayRec, dayPick, array, currentPage);
		
		ArrayList<CakeAndShop> csList = pageData.getCakeAndShop();
		String pageNavi = pageData.getPageNavi();
		
		if(!csList.isEmpty()) {
			request.setAttribute("csList", csList);
			request.setAttribute("pageNavi", pageNavi);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/cakeInfo/cakeInfoList.jsp");
			view.forward(request, response);
		}else {
//			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/cakeInfo/cakeInfoError.html");
//			view.forward(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아직 해당하는 케이크가 없습니다.'); location.href='/cake/list';</script>");
			out.flush();
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
