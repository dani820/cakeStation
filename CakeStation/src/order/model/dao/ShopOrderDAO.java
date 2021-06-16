package order.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import order.model.vo.Order;

public class ShopOrderDAO {
//	public int orderYes(Connection conn, int orderNum) {
//	PreparedStatement pstmt = null;
//	int result = 0;
//	String query = "";
//	
//	return result;
//}

//	public int orderDelete(Connection conn, int orderNum) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String query = "";
//
//		return result;
//	}
//
//// 주문전체 리스트 가져오기
//	public ArrayList<Order> selectAllOrder(Connection conn, int currentPage) {
//
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		ArrayList<Order> orderAllList = null;
//		String query;
//
//		int recordCountPerPage = 10; // 한 페이지당 보여줄 게시물 갯수
//		int start; // 시작 페이지
//		int end; // 마지막 페이지
//
//		Order order = new Order(); // 주문 사항 담을 Order 객체 생성
//
//		return orderAllList; // orderAllList 에 담아 리턴
//	}
//
//// 페이지 네비게이션 구현
//	public String getPageNavi(Connection conn, int currentPage, String shopId) {
//
//		int recordTotalCount = totalCount(conn, shopId); // 전체 게시물 갯수
//		int recordCountPerPage = 10; // 한 페이지당 보여줄 게시물 갯수
//		int pageTotalCount = 0; // 전체 페이지 갯수
//
//		int naviCountPerPage = 10; // 현재페이지에서 보여질 페이지 네비 갯수
//		int startNavi;
//		int endNavi;
//
//		boolean needPrev = true;
//		boolean needNext = true;
//
//		StringBuilder sb = new StringBuilder(); // i 값(페이지 네비값) 누적을 위해 StringBuilder 사용
//
//		return sb.toString();
//	}
//
//	public int totalCount(Connection conn, String shopId) {
//		Statement stmt = null;
//		ResultSet rset = null;
//		String query = "";
//
//		int recordTotalCount = 0; // 전체 주문목록 갯수
//
//		return recordTotalCount;
//	}

	// 업체 마이페이지 - 주문 목록 전체 가져오기
	public ArrayList<Order> selectAllOrder(Connection conn, int currentPage, String shopId) {

		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
		ArrayList<Order> orderAllList = null;
		String query = "SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY ORDER_NO DESC) AS NUM, orderInfo.ORDER_NO, orderInfo.REQUEST, orderInfo.DELIVERY_TYPE, orderInfo.PAY_TYPE, orderInfo.DELIVERY_DATE, orderInfo.PICKUP_TIME, orderInfo.ORDER_DATE, orderInfo.DELIVERY_ADDR, orderInfo.ORDERER_NAME, orderInfo.ORDERER_PHONE, shopInfo.SHOP_ID, shopInfo.CHAT_URL, cakeInfo.CAKE_NO, orderInfo.CAKE_AMOUNT, cakeInfo.CAKE_NAME, cakeInfo.CAKE_FILEPATH, cakeInfo.CAKE_FILENAME FROM ORDER_INFO orderInfo JOIN CAKE_INFO cakeInfo ON (orderInfo.CAKE_NO = cakeInfo.CAKE_NO) JOIN SHOP_MEMBER shopInfo ON (orderInfo.SHOP_ID = shopInfo.SHOP_ID) WHERE shopInfo.SHOP_ID=?) WHERE NUM BETWEEN ? AND ?"; // 역순 정렬 ( 최신순 )
		
		int recordCountPerPage = 5; // 한 페이지당 보여줄 게시물 갯수
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1); // 시작 페이지
		int end = currentPage * recordCountPerPage; // 마지막 페이지

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			orderAllList = new ArrayList<Order>();
			
			while(rset.next()) {
				Order order = new Order(); // 주문 사항 담을 Order 객체 생성
				order.setOrderNo(rset.getInt("ORDER_NO"));
				order.setRequest(rset.getString("REQUEST"));
				order.setDeliveryType(rset.getString("DELIVERY_TYPE"));
				order.setPayType(rset.getString("PAY_TYPE"));
				order.setDeliveryDate(rset.getDate("DELIVERY_DATE"));
				order.setPickupTime(rset.getNString("PICKUP_TIME"));
				order.setOrderDate(rset.getDate("ORDER_DATE"));
				order.setCakeAmount(rset.getInt("CAKE_AMOUNT"));
				order.setDeliveryAddr(rset.getString("DELIVERY_ADDR"));
				order.setOrdererName(rset.getString("ORDERER_NAME"));
				order.setCakeName(rset.getString("CAKE_NAME"));
				order.setOrdererPhone(rset.getString("ORDERER_PHONE"));
				order.setChatUrl(rset.getString("CHAT_URL"));
				order.setCakeNo(rset.getInt("CAKE_NO"));
				order.setCakeFileName(rset.getString("CAKE_FILENAME"));
				order.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
				orderAllList.add(order);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return orderAllList; // orderAllList 에 담아 리턴 
	}
	
	
	// 업체 마이페이지 - 주문 목록 취소하기
	public int orderDelete(Connection conn, int orderNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM ORDER_INFO WHERE ORDER_NO = ?";
		System.out.println(orderNum);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
	// 페이지 네비게이션 구현 (페이지당 보여줄 게시물 갯수 5, 페이지 네비 10)
	public String getPageNavi(Connection conn, int currentPage, String shopId) {
		
		int recordTotalCount = totalCount(conn, shopId); // 전체 게시물 갯수
		int recordCountPerPage = 5; // 한 페이지당 보여줄 게시물 갯수
		int pageTotalCount = 0; // 전체 페이지 갯수
		
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		// 오류방지코드
		if(currentPage < 1) {
			currentPage = 1;
		}else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int naviCountPerPage = 10; // 현재페이지에서 보여질 페이지 네비 갯수
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		// currentPage 에 따라 endNavi 가 넘어갈 수 있기 때문에 제한
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder(); // i 값(페이지 네비값) 누적을 위해 StringBuilder 사용
		if(needPrev) {
			sb.append("<a href='/shopMy/orderList?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for(int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/shopMy/orderList?currentPage=" + i + "'> " + i + " </a>");
		}
		if(needNext) {
			sb.append("<a href='/shopMy/orderList?currentPage=" + (endNavi + 1) + "'> > </a>");
		}

		return sb.toString();
	}
	
	// 업체 주문 게시물 전체 갯수
	public int totalCount(Connection conn, String shopId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(ORDER_NO) AS TOTALCOUNT FROM ORDER_INFO WHERE SHOP_ID=?"; // 뷰 생성해서 가져와야될듯...
		
		int recordTotalCount = 0; // 전체 주문목록 갯수
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return recordTotalCount;
	}
	
	//주문삽입
	public int insertOrder(Connection conn, Order order) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO ORDER_INFO VALUES(SEQ_ORDER_INFO.NEXTVAL, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD'), ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, order.getRequest());
			pstmt.setString(2, order.getDeliveryType());
			pstmt.setString(3, order.getPayType());
			pstmt.setString(4, order.getDate());
			pstmt.setString(5, order.getPickupTime());
			pstmt.setString(6, order.getMemberId());
			pstmt.setInt(7, order.getCakeNo());
			pstmt.setString(8, order.getShopId());
			pstmt.setString(9, order.getOrdererName());
			pstmt.setString(10, order.getDeliveryAddr());
			pstmt.setInt(11, order.getCakeAmount());
			pstmt.setString(12, order.getOrdererPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
