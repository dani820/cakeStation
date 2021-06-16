package admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import shop.model.vo.ShopMember;
import user.model.vo.CustomerMember;
import user.model.vo.PageData;

public class AdminDAO {

	public ArrayList<CustomerMember> selectCustomerList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CustomerMember> list = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY MEMBER_ENROLL DESC) AS NUM, MEMBER_ID, MEMBER_NAME FROM CUSTOMER_MEMBER WHERE MEMBER_TYPE='C') WHERE NUM BETWEEN ? AND ?";
		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<CustomerMember>();
			while(rset.next()) {
				CustomerMember ctMember = new CustomerMember();
				ctMember.setMemberId(rset.getString("MEMBER_ID"));
				ctMember.setMemberName(rset.getString("MEMBER_NAME"));
				list.add(ctMember);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ShopMember> selectShopList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ShopMember> list = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY SHOP_ENROLL DESC) AS NUM, SHOP_ID, SHOP_NAME FROM SHOP_MEMBER WHERE APPROVAL_YN = 'Y' AND WITHDRAW_YN = 'N') WHERE NUM BETWEEN ? AND ?";
		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<ShopMember>();
			while(rset.next()) {
				ShopMember sMember = new ShopMember();
				sMember.setShopId(rset.getString("SHOP_ID"));
				sMember.setShopName(rset.getString("SHOP_NAME"));
				list.add(sMember);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public ArrayList<ShopMember> signUpList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ShopMember> list = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY SHOP_ENROLL DESC) AS NUM, SHOP_ID, SHOP_NAME FROM SHOP_MEMBER WHERE APPROVAL_YN = 'N') WHERE NUM BETWEEN ? AND ?";		
		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<ShopMember>();
			while(rset.next()) {
				ShopMember sMember = new ShopMember();
				sMember.setShopId(rset.getString("SHOP_ID"));
				sMember.setShopName(rset.getString("SHOP_NAME"));
				list.add(sMember);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ShopMember> dropMemList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ShopMember> list = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY SHOP_ENROLL DESC) AS NUM, SHOP_ID, SHOP_NAME FROM SHOP_MEMBER WHERE WITHDRAW_YN = 'Y') WHERE NUM BETWEEN ? AND ?";
		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<ShopMember>();
			while(rset.next()) {
				ShopMember sMember = new ShopMember();
				sMember.setShopId(rset.getString("SHOP_ID"));
				sMember.setShopName(rset.getString("SHOP_NAME"));
				list.add(sMember);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public int approvalMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SHOP_MEMBER SET APPROVAL_YN = 'Y' WHERE SHOP_ID = '" + userId + "'";
		
		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int rejectdropMem(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SHOP_MEMBER SET WITHDRAW_YN = 'N' WHERE SHOP_ID = '" + userId + "'";
		
		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteCtMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM CUSTOMER_MEMBER WHERE MEMBER_ID = '" + userId + "'";
		
		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteShopMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM SHOP_MEMBER WHERE SHOP_ID = '" + userId + "'";
		
		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateCtMember(Connection conn, CustomerMember ctMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE CUSTOMER_MEMBER SET MEMBER_PWD = '?', MEMBER_EMAIL = '?', MEMBER_PHONE = '?', MEMBER_ADDRESS = '?' WHERE MEMBER_ID = '" + ctMember.getMemberId() + "'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ctMember.getMemberPwd());
			pstmt.setString(2, ctMember.getMemberEmail());
			pstmt.setString(3, ctMember.getMemberPhone());
			pstmt.setString(4, ctMember.getMemberAddress());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateShopMember(Connection conn, ShopMember sMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SHOP_MEMBER SET SHOP_NAME = '?', CEO_NAME = '?', SHOP_PWD = '?', SHOP_L_ADD = '?', SHOP_M_ADD = '?', SHOP_S_ADD ='?', SHOP_EMAIL = '?', SHOP_PHONE  = '?', WHERE SHOP_ID = '" + sMember.getShopId() + "'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sMember.getShopName());
			pstmt.setString(2, sMember.getCEOName());
			pstmt.setString(3, sMember.getShopLAdd());
			pstmt.setString(4, sMember.getShopMAdd());
			pstmt.setString(5, sMember.getShopSAdd());
			pstmt.setString(6, sMember.getShopEmail());
			pstmt.setString(7, sMember.getShopPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public String getShopPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = shopTotalCount(conn);
		int recordCountPerPage = 10;
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 오류방지 코드
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		// currentPage는 사용자가 선택한 값이 넘어와야 되기 때문에 매개변수로 전달받아요
		int endNavi = startNavi + naviCountPerPage - 1;
		// 오류방지 코드
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/admin/shopList?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/admin/shopList?currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append("<a href='/admin/shopList?currentPage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}

	public int shopTotalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM SHOP_MEMBER";
		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return recordTotalCount;
	}

	public String getCtPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = CtTotalCount(conn);
		int recordCountPerPage = 10;
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 오류방지 코드
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		// currentPage는 사용자가 선택한 값이 넘어와야 되기 때문에 매개변수로 전달받아요
		int endNavi = startNavi + naviCountPerPage - 1;
		// 오류방지 코드
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/admin/customerList?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/admin/customerList?currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append("<a href='/admin/customerList?currentPage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}
	
	public int CtTotalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM CUSTOMER_MEMBER";
		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return recordTotalCount;
	}

	public String getSignupPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = SignupTotalCount(conn);
		int recordCountPerPage = 10;
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 오류방지 코드
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		// currentPage는 사용자가 선택한 값이 넘어와야 되기 때문에 매개변수로 전달받아요
		int endNavi = startNavi + naviCountPerPage - 1;
		// 오류방지 코드
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/admin/signupList?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/admin/signupList?currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append("<a href='/admin/signupList?currentPage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}

	private int SignupTotalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM SHOP_MEMBER WHERE APPROVAL_YN = 'N'";
		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return recordTotalCount;
	}

	public String getdropMemPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = DropMemTotalCount(conn);
		int recordCountPerPage = 10;
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 오류방지 코드
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		// currentPage는 사용자가 선택한 값이 넘어와야 되기 때문에 매개변수로 전달받아요
		int endNavi = startNavi + naviCountPerPage - 1;
		// 오류방지 코드
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/admin/dropmemList?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/admin/dropmemList?currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append("<a href='/admin/dropmemList?currentPage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}

	private int DropMemTotalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM SHOP_MEMBER WHERE WITHDRAW_YN = 'Y'";
		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return recordTotalCount;
	}
}
