package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import customer.model.service.CustomerService;
import shop.model.service.ShopMemService;
import shop.model.vo.ShopMember;

/**
 * Servlet implementation class AdminShopModifyServlet
 */
@WebServlet("/admin/shopModify")
public class AdminShopModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminShopModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int result = 0;
		ShopMember sMember = new ShopMember();
		sMember.setShopId(request.getParameter("shop-id"));
		sMember.setShopPwd(request.getParameter("shop-pwd"));
		sMember.setShopName(request.getParameter("shop-name"));
		sMember.setCEOName(request.getParameter("ceo-name"));
		sMember.setShopLAdd(request.getParameter("shop-l-add"));
		sMember.setShopMAdd(request.getParameter("shop-m-add"));
		sMember.setShopSAdd(request.getParameter("shop-s-add"));
		sMember.setShopPhone(request.getParameter("shop-phone"));
		sMember.setShopEmail(request.getParameter("shop-email"));
		sMember.setShopCRN(request.getParameter("shop-crn"));
		result = new ShopMemService().updateMember(sMember);
		if(result > 0) {
			response.sendRedirect("/admin/shopList");
		}else {
			response.sendRedirect("/member/memberError.html");
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
