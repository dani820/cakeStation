package cakeinfo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import cakeinfo.model.service.CakeInfoService;
import cakeinfo.model.vo.CakeInfo;

/**
 * Servlet implementation class CakeInfoUpdateServlet
 */
@WebServlet("/cakeInfo/modify")
public class CakeInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CakeInfoUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cakeNo = Integer.parseInt(request.getParameter("cakeNo"));
		CakeInfo cakeInfo = new CakeInfoService().printCakeOne(cakeNo);
		
		request.setAttribute("cakeInfo", cakeInfo);
		
		if(cakeInfo != null) {
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/shopMyCake/shopMyCakeUpdate.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		CakeInfo cInfo = new CakeInfo();
		int result = 0;
		
		String uploadFilePath = request.getServletContext().getRealPath("img"); // cake 폴더
		String encType = "UTF-8"; // 파일 경로나 이름 등 한글 있을 수 있으므로
		int uploadFileSizeLimit = 5*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		File reloadFile = multi.getFile("upCake");
		String cFileName = multi.getFilesystemName("upCake");
		String cFilePath = reloadFile.getPath();
		long cFileSize = reloadFile.length();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp cFileTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		int cNo = Integer.parseInt(multi.getParameter("cakeNo"));
		String cName = multi.getParameter("cakeName");
		int cPrice = Integer.parseInt(multi.getParameter("cakePrice"));
		String cChar = multi.getParameter("cakeChar");
		String dayRec = multi.getParameter("dayRec");
		String dayPickUp = multi.getParameter("dayPick");
		String cThema = multi.getParameter("themas");
		String cType = multi.getParameter("cType");
				
		cInfo.setCakeNo(cNo);
		cInfo.setCakeName(cName);
		cInfo.setCakePrice(cPrice);
		cInfo.setCakeChar(cChar);
		cInfo.setDayRec(dayRec);
		cInfo.setDayPickUp(dayPickUp);
		cInfo.setCakeThema(cThema);
		cInfo.setCakeType(cType);
		cInfo.setCakeFilePath(cFilePath);
		cInfo.setCakeFileSize(cFileSize);
		cInfo.setCakeUploadTime(cFileTime);
		cInfo.setCakeFileName(cFileName);
		
		result = new CakeInfoService().updateCakeInfo(cInfo);
		
		if(result > 0) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>alert('입력하신 케이크 정보가 수정 되었습니다.'); location.href='/shopMy/cakeList';</script>");
			 
			out.flush();
		}else {
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);
		}
	}

}
