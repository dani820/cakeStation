package board.controller;

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

import board.model.service.BoardCommentService;
import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.model.vo.BoardCommentData;
import board.model.vo.BoardData;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int csNo = Integer.parseInt(request.getParameter("CS_NO"));
	
		Board board = new BoardService().printOne(csNo);
//		BoardComment bc = new BoardCommentService().printAllList();
		BoardCommentData bcd = new BoardCommentService().printAllList(csNo);
		ArrayList<BoardComment> bcList = bcd.getBoardCommentList();
		if(board != null) {
			request.setAttribute("board", board);
//			request.setAttribute("bcd", bcd);
			request.setAttribute("bcList", bcList);
			RequestDispatcher view = request.getRequestDispatcher("/board/boardDetail.jsp");
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/board/boardError.html");
			view.forward(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); // 넘어오는 데이터 한글 있을 수 있으니 인코딩
		
		String filePath = request.getParameter("file-path");
		
		File file = new File(filePath);
		// 페이지를 byte타입으로 설정하여 다운로드 페이지임을 명시해주고
		// 일반적인 html 페이지가 아닌 어플리케이션 파일이 리턴된다고 알려주기 위해
		// 셋팅이 필요합니다.
		response.setContentType("application/octet-stream"); // type, name은 고정이에요
		response.setContentLength((int)file.length()); // 파일의 크기에 따라 달라져요
		String fileName = new String(file.getName().getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName); // 파일의 이름에 따라 value가 달라져요
		// 파일을 보내기 위한 스트림 생성
		FileInputStream fileIn = new FileInputStream(file);
		ServletOutputStream output = response.getOutputStream();
		
		// inputstream -> read(), outstream -> write()
		byte [] outputByte = new byte[4096]; // 4 * 1024 -> 4KB
		while(fileIn.read(outputByte,0,4096) != -1) {
			// 읽은 것을 다운로드 되도록 함
			output.write(outputByte,0,4096);
		}
		fileIn.close();
		output.flush();
		output.close();
	}

}
