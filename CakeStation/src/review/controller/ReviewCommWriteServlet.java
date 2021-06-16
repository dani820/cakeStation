package review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import review.model.service.ReviewCommService;
import review.model.vo.ReviewComment;

/**
 * Servlet implementation class ReviewCommWriteServlet
 */
@WebServlet("/reviewcomm/write")
public class ReviewCommWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewCommWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/review/reviewDetail.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8"); 
		
		int reviewNo = Integer.parseInt(request.getParameter("REVIEW_NO"));
		String Contents = request.getParameter("REPLY_CONTENTS"); 
		String Writer = request.getParameter("REPLY_WRITER");
			ReviewComment rc = new ReviewComment();
			rc.setReviewNo(reviewNo);
			rc.setReplyContents(Contents);
			rc.setReplyWriter(Writer);
			
			int result = new ReviewCommService().registerReviewComm(rc);
			if(result > 0) {
				response.sendRedirect("/review/detail?REVIEW_NO="+reviewNo); 
			}else {
			//	System.out.println("result 값이 "+result);
				request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);
			}
		
		
	}

}
