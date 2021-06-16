package review.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewService;
import review.model.vo.Review;
import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class ReviewUpdateServlet
 */
@WebServlet("/review/modify")
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("REVIEW_NO"));
		String cakeName = request.getParameter("cakeName");
		String shopName = request.getParameter("shopName");
		String cakeFilePath = request.getParameter("cakeFilePath");
		int cakeNo = Integer.parseInt(request.getParameter("cakeNo"));
		String currShopId = request.getParameter("currShopId");
		
		Review review = new ReviewService().printOne(reviewNo);
		if(review != null) {
			
			request.setAttribute("review", review);
			request.setAttribute("cakeName", cakeName);
			request.setAttribute("shopName", shopName);
			request.setAttribute("cakeFilePath", cakeFilePath);
			request.setAttribute("cakeNo", cakeNo);
			request.setAttribute("currShopId", currShopId);
			request.getRequestDispatcher("/review/reviewModify.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/review/reviewError.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		String reviewContents = request.getParameter("REVIEW_CONTENT");
		int reviewNo = Integer.parseInt(request.getParameter("REVIEW_NO"));
		float reviewScore = Float.parseFloat(request.getParameter("REVIEW_SCORE"));
		int cakeNo = Integer.parseInt(request.getParameter("cakeNo"));
		String currShopId = request.getParameter("currShopId");
		Review review = new Review();
		
		review.setReviewContent(reviewContents);
		review.setReviewNo(reviewNo);
		review.setReviewScore(reviewScore);
		int result = new ReviewService().modifyReview(review);
		if(result > 0) {
			//response.sendRedirect("/cake/list");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('후기 수정 완료!'); location.href='/cake/detail?cakeNo="+ cakeNo + "&shopId=" + currShopId + "';</script>");
			//out.println("<script>alert('후기 작성 완료!'); location.href='/cake/detail?cakeNo="+ cakeNo + "&shopId=" + currShopId + "';</script>");
			out.flush();
		}else {
			request.getRequestDispatcher("/review/reviewError.html").forward(request, response);
		}
	}


}
