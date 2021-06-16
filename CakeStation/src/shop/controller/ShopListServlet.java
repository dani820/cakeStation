package shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.service.ShopMemService;
import shop.model.vo.PageData;
import shop.model.vo.ShopAndImg;
import shop.model.vo.ShopMember;

/**
 * Servlet implementation class ShopListServlet
 */
@WebServlet("/shop/list")
public class ShopListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pageData = new ShopMemService().printAllList(currentPage);
		ArrayList<ShopAndImg> siList = pageData.getShopAndImg();
		String pageNavi = pageData.getPageNavi();

		System.out.println("111");
		System.out.println(siList.size());
		for(ShopAndImg si : siList) {
			System.out.println(si.toString());
		}
		//System.out.println(siList.get(0).toString());
		if(!siList.isEmpty()) {
			request.setAttribute("siList", siList);
			request.setAttribute("pageNavi", pageNavi);
			//System.out.println("서블릿 리스트:" + siList.get(0).toString());
			//System.out.println(request.getAttribute("siList"));
			System.out.println(request.getAttribute("pageNavi"));
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/shopMem/shopMemList.jsp");
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/shopMem/shopMemError.html");
			view.forward(request, response);
		}
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
