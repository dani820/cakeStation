package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CustomerService;
import user.model.vo.CustomerMember;

/**
 * Servlet implementation class EnrollServlet
 */
@WebServlet("/member/enroll")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
		CustomerMember cMember = new CustomerMember();
		cMember.setMemberId(request.getParameter("user-id"));
		cMember.setMemberPwd(request.getParameter("user-pwd"));
		cMember.setMemberName(request.getParameter("user-name"));
		String phone = request.getParameter("user-phone1") + "-" + request.getParameter("user-phone2") + "-" + request.getParameter("user-phone3");
		cMember.setMemberPhone(phone);
		String email = request.getParameter("email-id") + "@" + request.getParameter("email-domain");
		cMember.setMemberEmail(email);
		cMember.setMemberAddress(request.getParameter("user-address"));
		cMember.setMemberType(request.getParameter("user-type"));
		cMember.setMemberRecieve(request.getParameter("agree"));
		
		int result = new CustomerService().registerMember(cMember);
		if(result > 0) {
			response.sendRedirect("/member/enrollSuccess.jsp");
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
