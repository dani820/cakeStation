package customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CustomerService;
import user.model.vo.CustomerMember;

/**
 * Servlet implementation class CustomerModifyServlet
 */
@WebServlet("/customer/update")
public class CtModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int result = 0;
		CustomerMember ctMember = new CustomerMember();
		ctMember.setMemberId(request.getParameter("user-id"));
		ctMember.setMemberPwd(request.getParameter("user-pwd"));
		ctMember.setMemberName(request.getParameter("user-name"));
		ctMember.setMemberEmail(request.getParameter("user-email"));
		ctMember.setMemberPhone(request.getParameter("user-phone"));
		ctMember.setMemberAddress(request.getParameter("user-address"));
		result = new CustomerService().updateMember(ctMember);
		if(result > 0) {
			request.setAttribute("ctMember", ctMember);
			request.getRequestDispatcher("/customer/customerMyPage.jsp").forward(request, response);
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
