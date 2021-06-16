package board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/board/boardWrite.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String csId = (String) session.getAttribute("userId");
		// 1. upload 폴더에 실제 파일을 저장하는 작업
		// MultipartRequest 객체 생성하면 파일이 저장됨.(완전편리!)
	//	String uploadFilePath = "D:\\home\\" + csId;
		String uploadFilePath = request.getServletContext().getRealPath("img");
		System.out.println(uploadFilePath);
		int uploadFileSizeLimit = 5 * 1024 * 1024; // 5백만바이트이면 5MB임.
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		Board board = new Board();
		
			String Cs_Title = multi.getParameter("CS_TITLE");
			String Cs_Contents = multi.getParameter("CS_CONTENTS");
			board.setCsTitle(Cs_Title);
			board.setCsContents(Cs_Contents);
		
			
		board.setCsId(csId);
		// 2. upload 폴더에 저장한 파일에 대한 정보를 DB에 저장하는 작업
		String fileName = multi.getFilesystemName("upFile");
		File uploadFile = multi.getFile("upFile");
		// multipartrequest의 인스턴스인 multi에는 upload.html에서 보낸 파일 정보가 존재하고
		// input 태그 name 속성의 값을 getFile() 메소드에 전달하면 여러 정보가 있는 파일 객체를 가져올 수 있음.
		if(fileName != null) {
			String filePath = uploadFile.getPath();
			long fileSize = uploadFile.length();
			// SimpleDateFormat, TimeStamp, Calendar 라는 세 개의 클래스 사용
			// 내가원하는 형식으로 바꿔주는 클래스, 타입을 맞춰주기 위해 사용, 오늘의 밀리세컨드까지의 정보를 가져오는 클래스
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); // 날짜데이터를 내가 원하는 형태로 바꿔줌
			Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));

			board.setCsFileName(fileName);
			board.setCsFilePath(filePath);
			board.setCsFileSize(fileSize);
			board.setCsUploadTime(uploadTime); // FILETBL의 UPLOADTIME 컬럼의 타입은 TIMESTAMP이고 시분초를 함께 저장함.
		}else {
			
		}
		
		

		int result = new BoardService().registerBoard(board);
		if (result > 0) {
			response.sendRedirect("/board/list");
		} else {
			// System.out.println("result 값이 "+result);
			RequestDispatcher view = request.getRequestDispatcher("/board/boardError.html");
			view.forward(request, response);
		}

	}

}
