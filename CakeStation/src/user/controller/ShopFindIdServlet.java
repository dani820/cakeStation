package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.service.ShopMemService;
import shop.model.vo.ShopMember;

/**
 * Servlet implementation class FindIdServlet
 */
@WebServlet("/shop/findId")
public class ShopFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopFindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �̸��� �� �޾ƿ���
		String email = request.getParameter("email-id") + "@" + request.getParameter("email-domain");
		
		ShopMember shopMember = new ShopMemService().findId(email);
		System.out.println(email);
		
		if(shopMember != null) {
			// ������ ��� ����(������) ���̵� �̾Ƽ� ��� ������
			request.setAttribute("userId", shopMember.getShopId());
			
			RequestDispatcher view = request.getRequestDispatcher("/member/shopFindId.jsp");
			view.forward(request, response);
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
