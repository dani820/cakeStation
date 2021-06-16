package customer.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import order.model.vo.Order;
import user.model.vo.CustomerMember;

public class CustomerDAO {

	public CustomerMember selectOneMember(Connection conn, String userId, String userPwd) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER_MEMBER WHERE MEMBER_ID='" + userId + "' AND MEMBER_PWD = '" + userPwd
				+ "'";
		CustomerMember cMember = null;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				cMember = new CustomerMember();
				cMember.setMemberId(rset.getString("MEMBER_ID"));
				cMember.setMemberPwd(rset.getNString("MEMBER_PWD"));
				cMember.setMemberName(rset.getString("MEMBER_NAME"));
				cMember.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				cMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				cMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
				cMember.setMemberEnroll(rset.getDate("MEMBER_ENROLL"));
				cMember.setMemberType(rset.getString("MEMBER_TYPE"));
				cMember.setMemberRecieve(rset.getString("MEMBER_RECIEVE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return cMember;
	}

	// 고객 회원가입
	public int insertMember(Connection conn, CustomerMember cMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO CUSTOMER_MEMBER VALUES(?,?,?,?,?,?,SYSDATE,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cMember.getMemberId());
			pstmt.setString(2, cMember.getMemberPwd());
			pstmt.setString(3, cMember.getMemberName());
			pstmt.setString(4, cMember.getMemberEmail());
			pstmt.setString(5, cMember.getMemberAddress());
			pstmt.setString(6, cMember.getMemberPhone());
			pstmt.setString(7, cMember.getMemberType());
			pstmt.setString(8, cMember.getMemberRecieve());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public CustomerMember findId(Connection conn, String email) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER_MEMBER WHERE MEMBER_EMAIL='" + email + "'";
		CustomerMember cMember = null;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				cMember = new CustomerMember();
				cMember.setMemberId(rset.getString("MEMBER_ID"));
				cMember.setMemberPwd(rset.getString("MEMBER_PWD"));
				cMember.setMemberName(rset.getString("MEMBER_NAME"));
				cMember.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				cMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				cMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
				cMember.setMemberEnroll(rset.getDate("MEMBER_ENROLL"));
				cMember.setMemberType(rset.getString("MEMBER_TYPE"));
				cMember.setMemberRecieve(rset.getString("MEMBER_RECIEVE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return cMember;
	}

	// 비밀번호 재설정
	public int resetPwd(Connection conn, String id, String pwd) {
		Statement stmt = null;
		int result = 0;
		String query = "UPDATE CUSTOMER_MEMBER SET MEMBER_PWD='" + pwd + "' WHERE MEMBER_ID='" + id + "'";
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
		}
		return result;
	}

	public CustomerMember selectOneById(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER_MEMBER WHERE MEMBER_ID = ?";
		CustomerMember ctMember = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				ctMember = new CustomerMember();
				ctMember.setMemberId(rset.getString("MEMBER_ID"));
				ctMember.setMemberPwd(rset.getString("MEMBER_PWD"));
				ctMember.setMemberName(rset.getString("MEMBER_NAME"));
				ctMember.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				ctMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
				ctMember.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				ctMember.setMemberType(rset.getString("MEMBER_TYPE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return ctMember;
	}

	public int updateMember(Connection conn, CustomerMember ctMember) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE CUSTOMER_MEMBER SET MEMBER_PWD=?, MEMBER_NAME=?, MEMBER_EMAIL=?, MEMBER_ADDRESS=?, MEMBER_PHONE=? WHERE MEMBER_ID = '"
				+ ctMember.getMemberId() + "'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ctMember.getMemberPwd());
			pstmt.setString(2, ctMember.getMemberName());
			pstmt.setString(3, ctMember.getMemberEmail());
			pstmt.setString(4, ctMember.getMemberAddress());
			pstmt.setString(5, ctMember.getMemberPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int deleteMember(Connection conn, String userId, String userPwd) {
		int result = 0;
		Statement stmt = null;
		String query = "DELETE FROM CUSTOMER_MEMBER WHERE MEMBER_ID = '" + userId + "' AND MEMBER_PWD = '" + userPwd
				+ "'";

		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
		}

		return result;
	}

	public ArrayList<Order> showOrderList(Connection conn, String userId, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Order> list = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ORDER_DATE DESC) AS NUM, ORDER_DATE, ORDER_NO, CAKE_NAME, CAKE_PRICE, CAKE_AMOUNT, ORDERER_NAME, ORDERER_PHONE, DELIVERY_TYPE, PICKUP_TIME, DELIVERY_DATE, REQUEST, CAKE_FILEPATH, DELIVERY_ADDR, CAKE_NO, SHOP_ID FROM ORDER_INFO JOIN CAKE_INFO USING(CAKE_NO, SHOP_ID) WHERE MEMBER_ID = ?) WHERE NUM BETWEEN ? AND ?";
		int recordCountPerPage = 3;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Order>();
			while (rset.next()) {
				Order order = new Order();
				order.setOrderDate(rset.getDate("ORDER_DATE"));
				order.setOrderNo(rset.getInt("ORDER_NO"));
				order.setCakeName(rset.getString("CAKE_NAME"));
				order.setCakePrice(rset.getInt("CAKE_PRICE"));
				order.setCakeAmount(rset.getInt("CAKE_AMOUNT"));
				order.setOrdererName(rset.getString("ORDERER_NAME"));
				order.setOrdererPhone(rset.getString("ORDERER_PHONE"));
				order.setDeliveryType(rset.getString("DELIVERY_TYPE"));
				order.setPickupTime(rset.getString("PICKUP_TIME"));
				order.setDeliveryDate(rset.getDate("DELIVERY_DATE"));
				order.setRequest(rset.getString("REQUEST"));
				order.setCakeFilePath(rset.getString("CAKE_FILEPATH"));
				order.setDeliveryAddr(rset.getString("DELIVERY_ADDR"));
				order.setCakeNo(rset.getInt("CAKE_NO"));
				order.setShopId(rset.getString("SHOP_ID"));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public String getOrderPageNavi(Connection conn, int currentPage, String userId) {
		int recordTotalCount = OrderTotalCount(conn, userId);
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
			sb.append("<a href='/customer/orderList?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/customer/orderList?currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append("<a href='/customer/orderList?currentPage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}

	private int OrderTotalCount(Connection conn, String userId) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM ORDER_INFO WHERE MEMBER_ID = '" + userId + "'";
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

	// 아이디 중복 검사
	public int checkId(Connection conn, String userId) {
		Statement stmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "SELECT COUNT(MEMBER_ID) AS CNT FROM CUSTOMER_MEMBER WHERE MEMBER_ID = '" + userId + "'";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				result = rset.getInt("CNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return result;
	}

}
