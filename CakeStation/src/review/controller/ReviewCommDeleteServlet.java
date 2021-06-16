package review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardCommentService;
import review.model.service.ReviewCommService;

/**
 * Servlet implementation class ReviewCommDeleteServlet
 */
@WebServlet("/reviewcomm/delete")
public class ReviewCommDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewCommDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shopId = "TEST03";
		int ReplyNo = Integer.parseInt(request.getParameter("REPLY_NO"));
		int REVIEW_NO = Integer.parseInt(request.getParameter("REVIEW_NO"));
		int result = new ReviewCommService().deleteReviewComm(ReplyNo);
		if(result > 0) {
			request.setAttribute("ReplyNo", ReplyNo);
			request.setAttribute("REVIEW_NO", REVIEW_NO);
			request.getRequestDispatcher("/review/detail?REVIEW_NO?=REVIEW_NO").forward(request, response);
		}else {
	
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);
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
