package admin.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.model.dao.AdminDAO;
import common.JDBCTemplate;
import shop.model.vo.ShopMember;
import user.model.vo.CustomerMember;
import user.model.vo.PageData;

public class AdminService {
	
	private JDBCTemplate factory;
	
	public AdminService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public PageData selectCustomerList(int currentPage) {
		PageData pageData = new PageData();
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			pageData.setCtMemberList(new AdminDAO().selectCustomerList(conn, currentPage));
			pageData.setPageNavi(new AdminDAO().getCtPageNavi(conn, currentPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pageData;
	}
	
	public PageData selectShopList(int currentPage) {
		PageData pageData = new PageData();
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			pageData.setShopMemberList(new AdminDAO().selectShopList(conn, currentPage));
			pageData.setPageNavi(new AdminDAO().getShopPageNavi(conn, currentPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return pageData;
	}
	
	public PageData signUpList(int currentPage) {
		PageData pageData = new PageData();
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			pageData.setShopMemberList(new AdminDAO().signUpList(conn, currentPage));
			pageData.setPageNavi(new AdminDAO().getSignupPageNavi(conn, currentPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return pageData;
	}
	
	public PageData dropMemList(int currentPage) {
		PageData pageData = new PageData();
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			pageData.setShopMemberList(new AdminDAO().dropMemList(conn, currentPage));
			pageData.setPageNavi(new AdminDAO().getdropMemPageNavi(conn, currentPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return pageData;
	}
	
	public int approvalMember(String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new AdminDAO().approvalMember(conn, userId);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int rejectSignUp(String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new AdminDAO().deleteShopMember(conn, userId);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int dropMember(String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new AdminDAO().deleteShopMember(conn, userId);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int rejectdropMem(String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new AdminDAO().rejectdropMem(conn, userId);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int deleteCtMember(String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new AdminDAO().deleteCtMember(conn, userId);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int deleteShopMember(String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new AdminDAO().deleteShopMember(conn, userId);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int updateCtMember(CustomerMember ctMember) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new AdminDAO().updateCtMember(conn, ctMember);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int updateShopMember(ShopMember sMember) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new AdminDAO().updateShopMember(conn, sMember);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
}
