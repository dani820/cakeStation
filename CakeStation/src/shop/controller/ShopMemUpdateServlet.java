package shop.controller;

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

import file.model.vo.CRNImg;
import shop.model.service.ShopMemService;
import shop.model.vo.ShopMember;

/**
 * Servlet implementation class ShopMemInfoUpdateServlet
 */
@WebServlet("/shopMy/updateMemInfo")
public class ShopMemUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopMemUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 폴더에 사진 저장
		String uploadFilePath = request.getServletContext().getRealPath("img");
		System.out.println(uploadFilePath);
		
		String encType = "UTF-8"; // 이름에 한글이 있을 수 있으므로
		int uploadFileSizeLimit = 5 * 1024 * 1024; // 파일 크기 제한 5mb
		
		// com.oreilly.servlet 패키지의 MultipartRequest 객체
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		// DB 에 사진 정보 저장
		File uploadFile = multi.getFile("crnFile"); // upFile 은 파일 버튼 네임 속성값으로 여러 정보가 있는 파일 객체를 가져올 수 있음. ( html 에서 보낸 파일 정보 )
		String fileName = multi.getFilesystemName("crnFile");
		long fileSize = uploadFile.length();
		String filePath = uploadFile.getPath();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		// 업체가 입력한 정보 저장
		ShopMember sMember = new ShopMember();
		CRNImg crnImg = new CRNImg();
		
		int resultMemInfo = 0;
		int resultImgInfo = 0;
		
		String shopId = multi.getParameter("shopId"); 
		
		// 업체 회원 정보
		sMember.setShopId(multi.getParameter("shopId")); // 입력창에서 readonly 값 같이 받기
		sMember.setShopPwd(multi.getParameter("shopPw"));
		sMember.setCEOName(multi.getParameter("shopCeo"));
		sMember.setShopName(multi.getParameter("shopName"));
		sMember.setShopLAdd(multi.getParameter("shopL"));
		sMember.setShopMAdd(multi.getParameter("shopM"));
		sMember.setShopSAdd(multi.getParameter("shopS"));
		sMember.setShopPhone(multi.getParameter("shopPhone"));
		sMember.setShopEmail(multi.getParameter("shopEmail"));
//		sMember.setShopWebsite(multi.getParameter("shopWeb"));
//		sMember.setChatUrl(multi.getParameter("chatUrl"));
		
		// 사업자등록증 파일
		crnImg.setCrnFileName(fileName);
		crnImg.setCrnFileUser(multi.getParameter("shopId"));
		crnImg.setCrnFilePath(filePath);
		crnImg.setCrnFileSize(fileSize);
		crnImg.setCrnUploadTime(uploadTime);
		
		resultMemInfo = new ShopMemService().modifyShop(sMember);
		resultImgInfo = new ShopMemService().modifyShop(crnImg);
		
		if(resultMemInfo > 0 && resultImgInfo > 0) {
			request.setAttribute("shopId", shopId);
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>alert('입력하신 회원 정보가 수정 되었습니다.'); location.href='/shopMy/shopMemInfo?shopId';</script>");
			 
			out.flush();
		}else {
			// 회원정보 수정 실패
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);
		}
		
	}

}
