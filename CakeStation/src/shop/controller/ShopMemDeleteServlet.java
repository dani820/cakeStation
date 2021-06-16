package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.service.ShopMemService;
import shop.model.vo.ShopMember;

/**
 * Servlet implementation class ShopMemDeleteServlet
 */
@WebServlet("/shopMy/MemDelete")
public class ShopMemDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopMemDeleteServlet() {
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
		
		ShopMember sMem = new ShopMember();
		
		sMem.setShopId(shopId);
		
		request.setAttribute("sMem", sMem);
		
		request.getRequestDispatcher("/WEB-INF/views/shopMyPage/shopMyWithdraw/shopMyWithdraw.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shopId = request.getParameter("shopId"); // readOnly 값으로 가져오기
		String shopPw = request.getParameter("shopPw");
		String withdrawY = request.getParameter("withdrawY");
		
		ShopMember sMember = new ShopMemService().checkShopPw(shopId); // 비밀번호 일치 여부 체크
		
		if(!shopPw.equals(sMember.getShopPwd())) {
			// 비밀번호 일치하지 않으니 다시 입력해주세요
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.'); location.href='/shopMy/MemDelete';</script>");
			 
			out.flush();
		}else {
			
			int result = new ShopMemService().removeShopMem(shopId, withdrawY);
			
			if(result > 0) {
				// 탈퇴 신청 성공. 마이페이지 첫 화면인 예약 목록 보이기.
				response.setContentType("text/html; charset=UTF-8");
				 
				PrintWriter out = response.getWriter();
				 
				out.println("<script>alert('탈퇴 신청이 완료 되었습니다.'); location.href='/shopMy/orderList';</script>");
				 
				out.flush();
			}else {
				// 탈퇴 신청 실패
				request.getRequestDispatcher("/WEB-INF/views/shopMyPage/serviceFailed.jsp").forward(request, response);
			}
		}
	}

}
