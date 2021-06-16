package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardCommentService;
import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/board/writeComment")
public class BoardCommentWriteServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/board/boardDetail.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8"); 
		
		int csNo = Integer.parseInt(request.getParameter("CS_NO"));
		String Contents = request.getParameter("CS_REPLY_CONTENTS"); 
		String Writer = request.getParameter("CS_REPLY_WRRITER");
			BoardComment bc = new BoardComment();
			bc.setCsNo(csNo);
			bc.setCsReplyContents(Contents);
			bc.setCsReplyWriter(Writer);
			
			int result = new BoardCommentService().registerBoard(bc);
			if(result > 0) {
				response.sendRedirect("/board/detail?CS_NO="+csNo); 
			}else {
			//	System.out.println("result 값이 "+result);
				RequestDispatcher view = request.getRequestDispatcher("/board/boardError.html");
				view.forward(request, response);
			}
		
		
	}
	
	
	
	
	
}
