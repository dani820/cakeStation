package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CustomerService;
import user.model.vo.CustomerMember;

/**
 * Servlet implementation class FindIdServlet
 */
@WebServlet("/member/findId")
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �̸��� �� �޾ƿ���
		String email = request.getParameter("email-id") + "@" + request.getParameter("email-domain");
		
		CustomerMember cMember = new CustomerService().findId(email);
		System.out.println(email);
		
		if(cMember != null) {
			request.setAttribute("userId", cMember.getMemberId());
			
			RequestDispatcher view = request.getRequestDispatcher("/member/findId.jsp");
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
