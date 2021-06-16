package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CustomerService;
import user.model.vo.CustomerMember;

/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/member/idcheck")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");
		request.setCharacterEncoding("UTF-8");
		
		int result = new CustomerService().checkId(userId);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			//사용 불가능
			out.println("<script charset='utf-8'>alert('Nooo!');location.href='/member/idCheckForm.jsp';</script>");
		}else {
			out.println("<script>alert('OK~!');window.close();</script>");
			//response.sendRedirect("/member/customerEnroll.jsp?checkResult=yes");
		}
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
