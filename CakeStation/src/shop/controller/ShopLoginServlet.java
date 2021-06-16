package shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.model.service.ShopMemService;
import shop.model.vo.ShopMember;

/**
 * Servlet implementation class ShopLoginServlet
 */
@WebServlet("/shop/login")
public class ShopLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("shop-userId");
		String userPwd = request.getParameter("shop-userPwd");
		// �븘�씠�뵒 ���옣(泥댄겕諛뺤뒪) "saveId"
		
		ShopMember sMember = new ShopMemService().selectOneMember(userId, userPwd);
		
		if(sMember!=null && sMember.getShopId()!=null && sMember.getShopPwd()!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("shopId", sMember.getShopId());
			response.sendRedirect("/member/loginSuccess.jsp");
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
