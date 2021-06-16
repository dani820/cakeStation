package shop.controller;

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

import file.model.vo.CRNImg;
import shop.model.service.ShopMemService;
import shop.model.vo.ShopMember;

/**
 * Servlet implementation class ShopMemInfoUpdateServlet
 */
@WebServlet("/shopMy/shopMemInfo")
public class ShopMemInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopMemInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String shopId = (String)session.getAttribute("shopId");
//		String shopId = "TEST02";
		
		ShopMember sMember = new ShopMemService().selectOneMember(shopId);
		CRNImg crnImg = new ShopMemService().selectCrnImg(shopId);
		
		if(sMember != null) {
			request.setAttribute("sMember", sMember);
			request.setAttribute("crnImg", crnImg);
			
			
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/shopMyInfo/shopMyInfo.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);
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
