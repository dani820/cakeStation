package shop.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import file.model.vo.CRNImg;
import shop.model.service.ShopMemService;
import shop.model.vo.ShopMember;

/**
 * Servlet implementation class ShopEnrollServlet
 */
@WebServlet("/shop/enroll")
public class ShopEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		
		
		// CRN ����
		/*String uploadFilePath = request.getServletContext().getRealPath("upload");
		int uploadFileSizeLimit = 14*1024*1024; // 14MB
		String encType = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		HttpSession session = request.getSession();
		String fileUserId = (String)session.getAttribute("userId");
		String fileName = multi.getFilesystemName("upFile");
		File uploadFile = multi.getFile("upFile");
		String filePath = uploadFile.getPath();
		long fileSize = uploadFile.length();
		int fileNo = 1;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		CRNImg fileData = new CRNImg();
		fileData.setCrnFileName(fileName);
		fileData.setCrnFilePath(filePath);
		fileData.setCrnFileSize(fileSize);
		fileData.setCrnFileUser(fileUserId);
		fileData.setCrnUploadTime(uploadTime);
		fileData.setCrnFileNo(fileNo); // �ѹ� �������� ����..
		*/
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
        
        File uploadFile = mrequest.getFile("SHOP_CRN");
//        String originalFileName = mrequest.getOriginalFileName("SHOP_CRN"); //기존 이름
        String filesystemName = mrequest.getFilesystemName("SHOP_CRN"); //기존
        String filePath = uploadFile.getPath();
		long fileSize = uploadFile.length();
		HttpSession session = request.getSession();
		String fileUserId = mrequest.getParameter("user-id");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
//        System.out.println(originalFileName);
        System.out.println(filesystemName);
        
        CRNImg crnImgFile = new CRNImg();
        crnImgFile.setCrnFileName(filesystemName);
        crnImgFile.setCrnFileSize(fileSize);
        crnImgFile.setCrnUploadTime(uploadTime);
        crnImgFile.setCrnFileUser(fileUserId);
        crnImgFile.setCrnFilePath(filesystemName);
//        crnImgFile.setCrnFileNo(filesystemName);
        
  /////////////////////////////////////
        ShopMember shopMember = new ShopMember();
		shopMember.setShopId(mrequest.getParameter("user-id"));
		shopMember.setShopPwd(mrequest.getParameter("user-pwd"));
		shopMember.setShopName(mrequest.getParameter("user-shop-name"));
		shopMember.setCEOName(mrequest.getParameter("user-name"));
		shopMember.setShopCRN(filesystemName);
		
		String phone = mrequest.getParameter("user-phone1") + "-" + mrequest.getParameter("user-phone2") + "-" + mrequest.getParameter("user-phone3");
		shopMember.setShopPhone(phone);
		System.out.println(phone);
		
		String email = mrequest.getParameter("email-id") + "@" + mrequest.getParameter("email-domain");
		shopMember.setShopEmail(email);
		System.out.println(email);
		
		//String addr = mrequest.getParameter("user-L-add") + mrequest.getParameter("user-M-add") + mrequest.getParameter("user-S-add");
		shopMember.setShopLAdd(mrequest.getParameter("user-L-add"));
		shopMember.setShopMAdd(mrequest.getParameter("user-M-add"));
		shopMember.setShopSAdd(mrequest.getParameter("user-S-add"));
		
		shopMember.setShopWebsite(mrequest.getParameter("user-website"));
		shopMember.setChatUrl(mrequest.getParameter("user-chatting"));
		shopMember.setShopRecieve(mrequest.getParameter("agree"));
		
		
		int result = new ShopMemService().registerMember(shopMember);
		
		if(result > 0) {
			int imgResult = new ShopMemService().registerCrnFile(crnImgFile);
			response.sendRedirect("/index.jsp");
		}else {
			response.sendRedirect("/member/errorPage.jsp");
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
