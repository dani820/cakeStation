package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
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


/**
 * Servlet implementation class ShopSearchServlet
 */
@WebServlet("/shop/search")
public class ShopSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int currentPage = 0;
		if(request.getParameter("currentPage")==null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		//지역, 정렬
		String shopLAdd = request.getParameter("area");
		String array = request.getParameter("array");
		
		PageData pageData = new ShopMemService().printSearchList(shopLAdd, array, currentPage);
		ArrayList<ShopAndImg> siList = pageData.getShopAndImg();
		String pageNavi = pageData.getPageNavi();
		
		//System.out.println("서블릿" + siList.get(0).toString());
		if(!siList.isEmpty()) {
			request.setAttribute("siList", siList);
			request.setAttribute("pageNavi", pageNavi);

			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/shopMem/shopMemList.jsp");
			view.forward(request, response);
		}else {
//			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/shopMem/shopMemError.html");
//			view.forward(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아직 해당 지역의 매장이 없습니다.'); location.href='/shop/list';</script>");
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
