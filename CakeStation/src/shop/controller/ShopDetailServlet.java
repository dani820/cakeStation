package shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cakeinfo.model.service.CakeInfoService;
import cakeinfo.model.vo.CakeInfo;
import review.model.service.ReviewService;
import review.model.vo.ReviewAndComm;
import shop.model.service.ShopMemService;
import shop.model.vo.ShopAndImg;

/**
 * Servlet implementation class ShopDetailServlet
 */
@WebServlet("/shop/detail")
public class ShopDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int currentPage = 0;
//		if(request.getParameter("currentPage") == null) {
//			currentPage = 1;
//		}else {
//			currentPage = Integer.parseInt(request.getParameter("currentPage"));
//		}
		
		// 업체 정보
		String shopId = (String)request.getParameter("shopId");
		ShopAndImg shopAndImg = new ShopMemService().printOne(shopId);
		
		// 해당업체의 케이크 목록
		ArrayList<CakeInfo> cList = new CakeInfoService().printSearchList(shopId); //currentPage변수삭제
		
		int listFlag = 0;
		if(!cList.isEmpty()) {
			listFlag = 1;
			request.setAttribute("cList", cList);
			request.setAttribute("listFlag", listFlag);
		} else {
			request.setAttribute("listFlag", listFlag);
		}
		
//		//후기 작성 버튼 없애기
//		HttpSession session = request.getSession();
//		int btnFlag = 0;
//		if(session != null && (session.getAttribute("userId")) != null) {
//			btnFlag = 1;
//			request.setAttribute("btnFlag", btnFlag);
//		} else {
//			request.setAttribute("btnFlag", btnFlag);
//		}
		
		// 해당업체의 구매후기 목록
		ArrayList<ReviewAndComm> rcList = new ReviewService().printSearchList(shopId); //currentPage변수삭제
		int reviewFlag = 0;
		if(!rcList.isEmpty()) {
			reviewFlag = 1;
			request.setAttribute("rcList", rcList);
			request.setAttribute("reviewFlag", reviewFlag);
		} else {
			request.setAttribute("reviewFlag", reviewFlag);
		}
		
		// 해당업체의 후기댓글
//			int reviewNo = rList.get(0).getReviewNo();
//			ReviewComment reviewComm = new ReviewCommService().printOne(reviewNo);

		if(shopAndImg != null) {
			//확인용
			System.out.println("service_업체정보:" + shopAndImg.toString());
			System.out.println("service_후기:" + rcList.toString());
			System.out.println("service_리뷰null버튼플래그:" + reviewFlag);
			System.out.println("service_케이크목록플래그:" + listFlag);
			System.out.println("service_맵주소: " + shopAndImg.getAddrMap());
			System.out.println("service_맵주소: " + shopAndImg.getAddrAll());
			request.setAttribute("shopAndimg", shopAndImg);
			request.getRequestDispatcher("/WEB-INF/views/shopMem/shopDetail.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/shopMem/shopMemError.jsp").forward(request, response);
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
