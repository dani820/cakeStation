package notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/notice/write")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/notice/noticeWrite.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 

		// 1. upload 폴더 생성이 안되어 있으면 생성 
				// 첨부파일 경로경로를 배꾸고 싶으면 수정하면 된다
				String saveDirectory = getServletContext().getRealPath("/upload");
				File saveDir = new File(saveDirectory);
				if (!saveDir.exists())
					saveDir.mkdirs();

				// 2. 최대크기 설정
				int maxPostSize = 1024 * 1024 * 10; // 10MB  단위 byte

				//3. 인코딩 방식 설정
				String encoding = "UTF-8";

				//4. 파일정책, 파일이름 충동시 덮어씌어짐으로 파일이름 뒤에 인덱스를 붙인다.
			  //a.txt
				//a1.txt 와 같은 형식으로 저장된다.
				FileRenamePolicy policy = new DefaultFileRenamePolicy();
				MultipartRequest mrequest 
				= new MultipartRequest(request //MultipartRequest를 만들기 위한 request
						, saveDirectory //저장 위치
						, maxPostSize //최대크기
						, encoding //인코딩 타입
						, policy); //파일 정책
				
				File uploadFile = mrequest.getFile("NOTICE_FILENAME");
				String originalFileName = mrequest.getOriginalFileName("NOTICE_FILENAME"); //기존 이름
				String filesystemName = mrequest.getFilesystemName("NOTICE_FILENAME"); //기존
		
		HttpSession session = request.getSession();
		/* 추후에 세션 값 받아서 수정
		 * 
		 * String Member_Id = (String)session.getAttribute("userId"); */
			Notice notice = new Notice();
			notice.setNotice_Title(mrequest.getParameter("NOTICE_TITLE"));
			notice.setNotice_Contents(mrequest.getParameter("NOTICE_CONTENTS"));
			notice.setNotice_FileName(originalFileName);
			notice.setNotice_FilePath(saveDirectory);
			notice.setMember_Id("TEST01");
			int result = new NoticeService().registerNotice(notice);
			if(result > 0) {
				response.sendRedirect("/notice/list"); 
			}else {
			//	System.out.println("result 값이 "+result);
				RequestDispatcher view = request.getRequestDispatcher("/customer/boardError.html");
				view.forward(request, response);
			}
		
		
	}


	}


