package board.controller;

import java.io.IOException;
import java.util.ArrayList;

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
import board.model.vo.BoardCommentData;
import board.model.vo.BoardData;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/commentList")
public class BoardCommentListServlet extends HttpServlet  {
	 
	
	public BoardCommentListServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			BoardCommentData bcd = new BoardCommentService().printAllList(1);
			ArrayList<BoardComment> bcList = bcd.getBoardCommentList();
			
			if(!bcList.isEmpty()) {
				request.setAttribute("bcList", bcList);
				RequestDispatcher view = request.getRequestDispatcher("/board/boardDetail.jsp");
				view.forward(request, response);	
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
