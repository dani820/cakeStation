package customer.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.model.dao.AdminDAO;
import common.JDBCTemplate;
import customer.model.dao.CustomerDAO;
import order.model.vo.Order;
import user.model.vo.CustomerMember;
import user.model.vo.PageData;

public class CustomerService {

	private JDBCTemplate factory;

	public CustomerService() {
		factory = JDBCTemplate.getConnection();
	}

	public CustomerMember selectOneMember(String userId, String userPwd) {
		CustomerMember ctMember = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			ctMember = new CustomerDAO().selectOneMember(conn, userId, userPwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return ctMember;
	}

	// 고객 회원가입
	public int registerMember(CustomerMember cMember) {
		int result = 0;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			result = new CustomerDAO().insertMember(conn, cMember);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public CustomerMember findId(String email) {
		Connection conn = null;
		CustomerMember cMember = null;

		try {
			conn = factory.createConnection();
			cMember = new CustomerDAO().findId(conn, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return cMember;
	}

	// 비밀번호 재설정
	public int resetPwd(String id, String pwd) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new CustomerDAO().resetPwd(conn, id, pwd);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public CustomerMember selectOneById(String userId) {
		CustomerMember ctMember = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			ctMember = new CustomerDAO().selectOneById(conn, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return ctMember;
	}

	public int updateMember(CustomerMember ctMember) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new CustomerDAO().updateMember(conn, ctMember);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public int deleteMember(String userId, String userPwd) {
		int result = 0;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			result = new CustomerDAO().deleteMember(conn, userId, userPwd);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	public PageData showOrderList(String userId, int currentPage) {
		PageData pageData = new PageData();
		Connection conn = null;

		try {
			conn = factory.createConnection();
			pageData.setOrderList(new CustomerDAO().showOrderList(conn, userId, currentPage));
			pageData.setPageNavi(new CustomerDAO().getOrderPageNavi(conn, currentPage, userId));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pageData;
	}

	// 일반회원 아이디 중복 체크 검사
	public int checkId(String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new CustomerDAO().checkId(conn, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
}
