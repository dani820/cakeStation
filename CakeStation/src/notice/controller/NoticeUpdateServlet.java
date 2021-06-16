package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/notice/modify")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Notice_No = Integer.parseInt(request.getParameter("NOTICE_NO"));
		Notice notice = new NoticeService().printOne(Notice_No);
		if(notice != null) {
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/notice/noticeModify.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/notice/noticeError.html").forward(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		String Notice_Title = request.getParameter("NOTICE_TITLE"); 
		String Notice_Contents = request.getParameter("NOTICE_CONTENTS");
		int Notice_No = Integer.parseInt(request.getParameter("NOTICE_NO"));
		Notice notice = new Notice();
		notice.setNotice_Title(Notice_Title);
		notice.setNotice_Contents(Notice_Contents);
		notice.setNotice_No(Notice_No);
		int result = new NoticeService().modifyNotice(notice);
		if(result > 0) {
			response.sendRedirect("/notice/list");
		}else {
			request.getRequestDispatcher("/notice/noticeError.html").forward(request, response);
		}
	}

}