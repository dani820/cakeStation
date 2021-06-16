package review.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardCommentService;
import board.model.vo.BoardComment;
import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class ReviewWriteServlet
 */
@WebServlet("/review/write")
public class ReviewWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int cakeNo = Integer.parseInt(request.getParameter("cakeNo"));
    	request.setAttribute("cakeNo", cakeNo);
    	String currShopId = request.getParameter("currShopId");
    	request.setAttribute("currShopId", currShopId);
    	String cakeName = request.getParameter("cakeName");
    	request.setAttribute("cakeName", cakeName);
    	int cakePrice = Integer.parseInt(request.getParameter("cakePrice"));
    	request.setAttribute("cakePrice", cakePrice);
		String cakeFilePath = request.getParameter("cakeFilePath");
		request.setAttribute("cakeFilePath", cakeFilePath);
    	
    	RequestDispatcher view = request.getRequestDispatcher("/review/reviewWrite.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 

		HttpSession session = request.getSession();
		String csId = (String) session.getAttribute("userId");
		String currShopId = request.getParameter("currShopId"); 
		String Contents = request.getParameter("REVIEW_CONTENT"); 
		float reviewScore = Float.parseFloat(request.getParameter("REVIEW_SCORE"));
		int cakeNo = Integer.parseInt(request.getParameter("cakeNo"));
			Review review = new Review();
			review.setReviewScore(reviewScore);
			review.setReviewContent(Contents);
			review.setCakeNo(cakeNo);
			review.setMemberId(csId);
			System.out.println("샵아이디몇일까요 ? : "+currShopId);
			review.setShopId(currShopId);
			
			int result = new ReviewService().registerReview(review);
			if(result > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('후기 작성 완료!'); location.href='/cake/detail?cakeNo="+ cakeNo + "&shopId=" + currShopId + "';</script>");
			//out.println("<script>alert('주문 성공! 마이페이지 주문 목록을 확인해주세요.'); location.href='/cake/list';</script>");
			out.flush();
				
			}else {
			//	System.out.println("result 값이 "+result);
				RequestDispatcher view = request.getRequestDispatcher("/review/reviewError.html");
				view.forward(request, response);
			}
		
		
	}

}
