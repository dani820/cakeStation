package review.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewCommService;
import review.model.service.ReviewService;
import review.model.vo.Review;
import review.model.vo.ReviewComment;
import review.model.vo.ReviewCommentData;

/**
 * Servlet implementation class ReviewDetailServlet
 */
@WebServlet("/review/detail")
public class ReviewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("REVIEW_NO"));
		
		Review review = new ReviewService().printOne(reviewNo);
//		ReviewComment bc = new ReviewCommentService().printAllList();
		ReviewCommentData rcd = new ReviewCommService().printAllComm(reviewNo);
		ArrayList<ReviewComment> rcList = rcd.getReviewCommentList();
		
		if(review != null) {
			request.setAttribute("review", review);
//			request.setAttribute("bcd", bcd);
			request.setAttribute("rcList", rcList);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/shopMyPage/shopMyReview/shopReviewDetail.jsp");
			view.forward(request, response);
		}else {
		//	System.out.println("리뷰가널인듯");
			RequestDispatcher view = request.getRequestDispatcher("/review/reviewError.html");
			view.forward(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); // 넘어오는 데이터 한글 있을 수 있으니 인코딩
		
		doGet(request, response);;
	}

}
