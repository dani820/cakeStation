package review.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewDeleteServlet
 */
@WebServlet("/review/delete")
public class ReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reviewNo = Integer.parseInt(request.getParameter("REVIEW_NO"));
		int cakeNo = Integer.parseInt(request.getParameter("cakeNo"));
		String currShopId = request.getParameter("currShopId");
		int result = new ReviewService().deleteReview(reviewNo);
		if(result > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('후기 삭제 완료!'); location.href='/cake/detail?cakeNo="+ cakeNo + "&shopId=" + currShopId + "';</script>");
			out.flush();
		}else {
		
			request.getRequestDispatcher("/review/reviewError.html").forward(request, response);
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
