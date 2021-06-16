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
 * Servlet implementation class CakeInfoRegisterServlet
 */
@WebServlet("/cakeInfo/enroll")
public class CakeInfoEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CakeInfoEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/shopMyPage/shopMyCake/shopMyCakeEnroll.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(); // shopId 사용 위해 세션을 가져온다. 
		String shopId = (String)session.getAttribute("shopId");
//		String shopId = "TEST01";
		
		CakeInfo cInfo = new CakeInfo();
		int result = 0;
		
		// 사진 파일 업로드
		String uploadFilePath = request.getServletContext().getRealPath("img"); // 사진파일 저장 폴더 지정
		System.out.println(uploadFilePath); // 파일 경로 확인용
		
		String encType = "UTF-8"; // 한글인식
		int uploadFileSizeLimit = 5*1024*1024; // 파일사이즈 지정 5mb
		
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		// upload 폴더에 저장한 파일에 대한 정보를 DB 에 저장하는 작업
		File cUploadFile = multi.getFile("upCake"); 
		String cFileName = multi.getFilesystemName("upCake");
		long cFileSize = cUploadFile.length();
		String cFilePath = cUploadFile.getPath();
		
		// 타입을 맞춰주기 위해 사용, 오늘의 밀리세컨드까지의 정보를 가져오는 클래스
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); // 날짜 데이터 원하는 형태로 변경 가능
		Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		// 작성한 케익 정보에 대한 입력값 가져오기 
		String cName = multi.getParameter("cakeName");
 		int cPrice = Integer.parseInt(multi.getParameter("cakePrice"));
		String dayRec = multi.getParameter("dayRec");
		String dayPickUp = multi.getParameter("dayPick");
		String cThema = multi.getParameter("themas");
		String cType = multi.getParameter("cType");
		String cChar = multi.getParameter("cakeChar");
		
		cInfo.setShopId(shopId);
		cInfo.setCakeName(cName);
		cInfo.setCakePrice(cPrice);
		cInfo.setCakeChar(cChar);
		cInfo.setDayRec(dayRec);
		cInfo.setDayPickUp(dayPickUp);
		cInfo.setCakeThema(cThema);
		cInfo.setCakeType(cType);
		cInfo.setCakeFilePath(cFilePath);
		cInfo.setCakeFileSize(cFileSize);
		cInfo.setCakeUploadTime(uploadTime);
		cInfo.setCakeFileName(cFileName);
		
		result = new CakeInfoService().registerCakeInfo(cInfo);

		if(result > 0) {
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>alert('입력하신 케이크 정보가 등록 되었습니다.'); location.href='/shopMy/cakeList';</script>");
			 
			out.flush();
		}else {
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);
		}
	}

}
