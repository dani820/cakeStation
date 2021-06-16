package review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardData;
//import cakeinfo.model.vo.PageData;
import review.model.service.ReviewCommService;
import review.model.service.ReviewService;
import review.model.vo.Review;
import review.model.vo.ReviewComment;
import review.model.vo.ReviewCommentData;
import review.model.vo.ReviewData;

/**
 * Servlet implementation class shopReviewList
 */
@WebServlet("/shopMy/reviewList")
public class ShopReviewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopReviewListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String shopId = (String)session.getAttribute("shopId");
		
//		String shopId = "TEST03";
		
		int currentPage = 0;
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		ReviewData reviewData = new ReviewService().printAllList(currentPage, shopId);
		ArrayList<Review> rList = reviewData.getReviewList();
		String pageNavi = reviewData.getPageNavi();
//		ReviewCommentData reviewCommentData = new ReviewCommService().printAllComm(currentPage);
//		ArrayList<ReviewComment> rcList = reviewCommentData.getReviewCommentList();
		int totalCount = reviewData.getTotalCount();
		System.out.println("totalCount 는? "+totalCount);
		System.out.println(shopId);
		System.out.println(rList.isEmpty());
		if(!rList.isEmpty()) {
			request.setAttribute("rList", rList);
//			request.setAttribute("rcList", rcList);
			request.setAttribute("currentPage",currentPage);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("pageNavi", pageNavi);
			request.setAttribute("shopId", shopId);
			
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/shopMyReview/shopMyReview.jsp").forward(request, response);;
		}else {
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);;
			
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
