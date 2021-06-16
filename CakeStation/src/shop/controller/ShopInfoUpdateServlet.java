package shop.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
import file.model.vo.ShopImg;
import shop.model.service.ShopMemService;
import shop.model.vo.ShopMember;

/**
 * Servlet implementation class ShopMemInfoUpdateServlet
 */
@WebServlet("/shopMy/updateShopInfo")
public class ShopInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopInfoUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String shopId = (String)session.getAttribute("shopId");
		
		request.setCharacterEncoding("UTF-8");
//		String shopId = "TEST02";
		
		ShopMember shopInfo = new ShopMemService().selectOneMember(shopId);
		ShopImg shopImg = new ShopMemService().selectShopImg(shopId);
		request.setAttribute("shopInfo", shopInfo);
		request.setAttribute("shopImg", shopImg);
		
		request.getRequestDispatcher("/WEB-INF/views/shopMyPage/shopMyShopInfo/shopMyShopInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 폴더에 사진 저장
		String uploadFilePath = request.getServletContext().getRealPath("img");
		String encType = "UTF-8"; // 이름에 한글이 있을 수 있으므로
		int uploadFileSizeLimit = 5 * 1024 * 1024; // 파일 크기 제한
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		// DB 에 사진 정보 저장
		File uploadFile = multi.getFile("shopImg");
		String fileName = multi.getFilesystemName("shopImg");
		long fileSize = uploadFile.length();
		String filePath = uploadFile.getPath();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		// 업체가 입력한 정보 저장
		ShopMember sMember = new ShopMember();
		ShopImg sImg = new ShopImg();
		String shopId = multi.getParameter("shopId");
//		String shopId = "TEST02";
		
		sMember.setShopId(shopId);
		sMember.setShopName(multi.getParameter("shopName"));
		sMember.setShopLAdd(multi.getParameter("shopLAdd"));
		sMember.setShopMAdd(multi.getParameter("shopMAdd"));
		sMember.setShopSAdd(multi.getParameter("shopSAdd"));
		sMember.setOpeningStart(Integer.parseInt(multi.getParameter("openTime")));
		sMember.setOpeningEnd(Integer.parseInt(multi.getParameter("closeTime")));
		sMember.setClosed(multi.getParameter("closed"));
		sMember.setShopWebsite(multi.getParameter("shopWebsite"));
		sMember.setIntroduced(multi.getParameter("introduce"));
		sMember.setChatUrl(multi.getParameter("chatUrl"));
		
		sImg.setShopFileName(fileName);
		sImg.setShopFileUser(shopId);
		sImg.setShopFilePath(filePath);
		sImg.setShopFileSize(fileSize);
		sImg.setShopUploadTime(uploadTime);
		
		int resultMemInfo = new ShopMemService().updateShopInfo(sMember);
		int resultImgInfo = new ShopMemService().updateShopInfo(sImg);
		
		if(resultMemInfo == 0) {
			// 가게 정보 수정 성공
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>alert('텍스트 정보 수정 실패'); location.href='/shopMy/updateShopInfo';</script>");
			 
			out.flush();
		}else if(resultImgInfo == 0) {
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>alert('사진 정보 수정 실패'); location.href='/shopMy/updateShopInfo';</script>");
			 
			out.flush();
			
		}else if(resultMemInfo > 0 && resultImgInfo > 0) {
			// 가게 정보 수정 실패
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>alert('업체 정보가 수정 되었습니다.'); location.href='/shopMy/updateShopInfo';</script>");
			 
			out.flush();
		}else {
			// 가게 정보 수정 실패
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);
		}
	}

}
