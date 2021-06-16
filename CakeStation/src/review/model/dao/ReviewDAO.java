package review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.Board;
import review.model.vo.Review;
import review.model.vo.ReviewAndComm;
import common.JDBCTemplate;
import review.model.vo.Review;

public class ReviewDAO {

	// 등록
	public int insertReview(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO REVIEW VALUES(SEQ_REVIEW.NEXTVAL,?,SYSDATE,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, review.getReviewContent());

			pstmt.setFloat(2, review.getReviewScore());
			pstmt.setString(3, review.getMemberId());
			pstmt.setInt(4, review.getCakeNo());
			pstmt.setString(5, review.getShopId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 수정
	public int updateReview(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE REVIEW " + "SET REVIEW_CONTENT =? , REVIEW_SCORE = ? WHERE REVIEW_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, review.getReviewContent());
			pstmt.setFloat(2, review.getReviewScore());
			pstmt.setInt(3, review.getReviewNo());

			result = pstmt.executeUpdate();
			// executeUpdate() 안쓰면 쿼리문 실행안해요
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 삭제
	public int deleteReview(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM REVIEW WHERE REVIEW_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 한개 조회

	public Review selectOne(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null; // 위치홀더 사용함
		ResultSet rset = null;
		Review review = null;
		String query = "SELECT * FROM REVIEW WHERE REVIEW_NO = ?"; // 위치홀더는 물음표
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				// System.out.println("잘되나확인해보자");
				review = new Review();
				review.setReviewNo(rset.getInt("REVIEW_NO"));
				review.setReviewContent(rset.getString("REVIEW_CONTENT"));
				review.setReviewDate(rset.getDate("REVIEW_DATE"));
				review.setReviewScore(rset.getInt("REVIEW_SCORE"));
				review.setMemberId(rset.getString("MEMBER_ID"));
				review.setCakeNo(rset.getInt("CAKE_NO"));
				review.setShopId(rset.getString("SHOP_ID"));

				// 여기까진 널이 아님

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return review;
	}

	////////////////////// 선미///////////////////
	// 케이크 상세화면에 해당 후기를 뿌려주기 위해 작성된 메소드(cakeinfodetailservlet에서 불러옵니다.)
	public ArrayList<ReviewAndComm> selectSearchList(Connection conn, int cakeNo) { // currentPage삭제
		Statement stmt = null;
		ResultSet rset = null;
		// 쿼리: 제품번호, 업체아이디로 후기게시판에서 작성자, 평점, 내용, 작성일, 사진정보 가져오기
		String query = "SELECT * FROM REVIEW LEFT OUTER JOIN REVIEW_COMMENT USING(REVIEW_NO) WHERE CAKE_NO =" + cakeNo
				+ " ORDER BY REVIEW_DATE DESC";
		ArrayList<ReviewAndComm> rcList = null;
//			int recordCountPerPage = 5;
//			int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
//			int end = currentPage*recordCountPerPage;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			rcList = new ArrayList<ReviewAndComm>();

			// REVIEW_NO, REVIEW_CONTENT, REVIEW_DATE, REVIEW_SCORE, MEMBER_ID, CAKE_NO,
			// SHOP_ID
			while (rset.next()) {
				ReviewAndComm revAndComm = new ReviewAndComm();
				revAndComm.setReviewNo(rset.getInt("REVIEW_NO"));
				revAndComm.setReviewContent(rset.getString("REVIEW_CONTENT"));
				revAndComm.setReviewDate(rset.getDate("REVIEW_DATE"));
				revAndComm.setReviewScore(rset.getInt("REVIEW_SCORE"));
				revAndComm.setMemberId(rset.getString("MEMBER_ID"));
				revAndComm.setCakeNo(rset.getInt("CAKE_NO"));
				revAndComm.setShopId(rset.getString("SHOP_ID"));
				revAndComm.setReplyNo(rset.getInt("REPLY_NO"));
				revAndComm.setReplyContents(rset.getString("REPLY_CONTENTS"));
				revAndComm.setReplyWriter(rset.getString("REPLY_WRITER"));
				revAndComm.setReplyDate(rset.getDate("REPLY_DATE"));
				rcList.add(revAndComm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		System.out.println("dao: " + rcList.toString());
		return rcList;
	}

//		public String getSearchPageNavi(Connection conn, int cakeNo, int currentPage) {
//		int recordCountPerPage = 5;
//		int naviCountPerPage = 10;
//		int recordTotalCount = searchTotalCount(conn, cakeNo); // 전체 게시물의 갯수
//		//전체 페이지수
//		int pageTotalCount = 0;
//		
//		if(recordTotalCount % recordCountPerPage > 0) {
//			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
//		}else {
//			pageTotalCount = recordTotalCount / recordCountPerPage;
//		}
//		
//		if(currentPage < 1) {
//			currentPage = 1;
//		}else if(currentPage > pageTotalCount) {
//			currentPage = pageTotalCount;
//		}
//		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
//		int endNavi = startNavi + naviCountPerPage - 1;
//		if(endNavi > pageTotalCount) {
//			endNavi = pageTotalCount;
//		}
//		boolean needPrev = true;
//		boolean needNext = true;
//		if(startNavi == 1) {
//			needPrev = false;
//		}
//		if(endNavi == pageTotalCount) {
//			needNext = false;
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		if(needPrev) {
//			sb.append("<a href='/cake/detail?currentPage=" + (startNavi - 1) + "&searchKeyword=" + cakeNo + "'> < </a>");
//		}
//		for(int i = startNavi; i <= endNavi; i++) {
//			sb.append("<a href='/notice/search?currentPage=" + i + "&searchKeyword=" + cakeNo + "'> " + i + " </a>");
//			//searchKeyword도 Servlet에 같이 넘겨줘야 아래 searchTotalCount() 동작하여 sb 만들 수 있음
//		}
//		if(needNext) {
//			sb.append("<a href='/notice/search?currentPage=" + (endNavi + 1) + "&searchKeyword=" + cakeNo + "'> > </a>");
//		}
//		return sb.toString();
//
//	}
//	
//	public int searchTotalCount(Connection conn, int cakeNo) {
//		
//		return 0;
//	}

	// 업체 상세화면에 해당 후기를 뿌려주기 위해 작성된 메소드(shopdetailservlet에서 불러옵니다.)
	public ArrayList<ReviewAndComm> selectSearchList(Connection conn, String shopId) { // currentPage
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 쿼리: 제품번호, 업체아이디로 후기게시판에서 작성자, 평점, 내용, 작성일, 사진정보 가져오기
		String query = "SELECT * FROM REVIEW LEFT OUTER JOIN REVIEW_COMMENT USING(REVIEW_NO) WHERE SHOP_ID = ? ORDER BY REVIEW_DATE DESC";
		ArrayList<ReviewAndComm> rcList = null;
//			int recordCountPerPage = 5;
//			int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
//			int end = currentPage*recordCountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			rset = pstmt.executeQuery();
			rcList = new ArrayList<ReviewAndComm>();

			// REVIEW_NO, REVIEW_CONTENT, REVIEW_DATE, REVIEW_SCORE, MEMBER_ID, CAKE_NO,
			// SHOP_ID
			while (rset.next()) {
				ReviewAndComm revAndComm = new ReviewAndComm();
				revAndComm.setReviewNo(rset.getInt("REVIEW_NO"));
				revAndComm.setReviewContent(rset.getString("REVIEW_CONTENT"));
				revAndComm.setReviewDate(rset.getDate("REVIEW_DATE"));
				revAndComm.setReviewScore(rset.getInt("REVIEW_SCORE"));
				revAndComm.setMemberId(rset.getString("MEMBER_ID"));
				revAndComm.setCakeNo(rset.getInt("CAKE_NO"));
				revAndComm.setShopId(rset.getString("SHOP_ID"));
				revAndComm.setReplyNo(rset.getInt("REPLY_NO"));
				revAndComm.setReplyContents(rset.getString("REPLY_CONTENTS"));
				revAndComm.setReplyWriter(rset.getString("REPLY_WRITER"));
				revAndComm.setReplyDate(rset.getDate("REPLY_DATE"));

				rcList.add(revAndComm);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rcList;
	}

//	public String getSearchPageNavi(Connection conn, String shopId, int currentPage) {
//		
//		return null;
//	}
//	
//	public int searchTotalCount(Connection conn, String shopId) {
//		
//		return 0;
//	}

	public ArrayList<Review> selectAllReview(Connection conn, int currentPage, String shopId) {
		Statement stmt = null;
		ResultSet rset = null;
		String query;

		int recordCountPerPage = 5;
		int start;
		int end;

		ArrayList<Review> rList = new ArrayList<Review>();
		Review review = new Review();

		return rList;
	}

//
//		public String getPageNavi(Connection conn, int currentPage, String shopId) {
//		int recordTotalCount = totalCount(conn, shopId);
//		int recordCountPerPage = 5;
//		
//		int startNavi;
//		int endNavi;
//		
//		boolean needPrev = true;
//		boolean needNext = true;
//		
//		StringBuilder sb = new StringBuilder();
//		
//		return null;
//	}
//	
//	private int totalCount(Connection conn, String shopId) {
//		Statement stmt = null;
//		ResultSet rset = null;
//		String query;
//		
//		int recordTotalCount = 0;
//		
//		return recordTotalCount;
//	}

	// 케이크 상세화면에 해당 후기를 뿌려주기 위해 작성된 메소드(cakeinfodetailservlet에서 불러옵니다.)
	public ArrayList<Review> selectSearchList(Connection conn, int cakeNo, String shopId, int currentPage) {
		// 쿼리: 제품번호, 업체아이디로 후기게시판에서 작성자, 평점, 내용, 작성일, 사진정보 가져오기
		return null;
	}

	public String getSearchPageNavi(Connection conn, int cakeNo, String shopId, int currentPage) {

		return null;
	}

	public int searchTotalCount(Connection conn, int cakeNo, String shopId) {

		return 0;
	}

	// 업체 상세화면에 해당 후기를 뿌려주기 위해 작성된 메소드(shopdetailservlet에서 불러옵니다.)
	public ArrayList<Review> selectSearchList(Connection conn, String shopId, int currentPage) {

		return null;
	}

	public String getSearchPageNavi(Connection conn, String shopId, int currentPage) {

		return null;
	}

	public int searchTotalCount(Connection conn, String shopId) {

		return 0;
	}

	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM REVIEW";
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

	public String getPageNavi(Connection conn, int currentPage) {

		int recordTotalCount = totalCount(conn);
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
			sb.append("<a href='/shopMy/reviewList?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/shopMy/reviewList?currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append("<a href='/shopMy/reviewList?currentPage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}

	public ArrayList<Review> selectAllList(Connection conn, int currentPage) {
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> rList = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY REVIEW_NO DESC) AS NUM, REVIEW_NO, REVIEW_CONTENT,REVIEW_DATE,REVIEW_SCORE, MEMBER_ID,CAKE_NO FROM REVIEW) WHERE NUM BETWEEN ? AND ?"; // 숫자는

		// 현재 페이지 : 1, 한 페이지당 보여줄 게시물의 갯수 : 10, 5
		// start : 1, end : 10
		// start : 1, end : 5
		// 현재 페이지 : 2
		// start : 11, end : 20
		// start : 6, end : 10
		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			rList = new ArrayList<Review>();
			while (rset.next()) {
				Review review = new Review();
				review.setReviewNo(rset.getInt("REVIEW_NO"));
				review.setReviewContent(rset.getString("REVIEW_CONTENT"));
				review.setReviewDate(rset.getDate("REVIEW_DATE"));
				review.setReviewScore(rset.getInt("REVIEW_SCORE"));
				review.setMemberId(rset.getString("MEMBER_ID"));
				review.setCakeNo(rset.getInt("CAKE_NO"));
				// review.setShopId(rset.getString("SHOP_ID"));
				rList.add(review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rList; // null이면 무조건 실패 떠요
	}

	// 업체 마이페이지 후기리스트 페이지네비 다인
	public String getPageNavi(Connection conn, int currentPage, String shopId) {

		int recordTotalCount = totalCount(conn, shopId);
		int recordCountPerPage = 5;
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
			sb.append("<a href='/shopMy/reviewList?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/shopMy/reviewList?currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append("<a href='/shopMy/reviewList?currentPage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}

	// 업체 마이페이지 후기리스트 페이지네비 다인
	public int totalCount(Connection conn, String shopId) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM REVIEW LEFT OUTER JOIN CAKE_INFO ON (REVIEW.CAKE_NO = CAKE_INFO.CAKE_NO) WHERE REVIEW.SHOP_ID = '"
				+ shopId + "'";
		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();

			rset = stmt.executeQuery(query);
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
				System.out.println(recordTotalCount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return recordTotalCount;
	}

	// 업체 마이페이지 후기리스트 페이지네비 다인
	public ArrayList<Review> selectAllList(Connection conn, int currentPage, String shopId) {
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> rList = null;
//			String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY REVIEW_NO DESC) AS NUM, review.REVIEW_NO, review.REVIEW_CONTENT,review.REVIEW_DATE,review.REVIEW_SCORE, review.MEMBER_ID,review.CAKE_NO FROM REVIEW review JOIN CAKE_INFO cinfo ON (review.CAKE_NO = cinfo.CAKE_NO) WHERE cinfo.SHOP_ID = ?) WHERE NUM BETWEEN ? AND ?"; // 숫자는
		String query = "SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY REVIEW_NO ASC) AS NUM, REVIEW_NO, REVIEW_CONTENT, REVIEW_DATE, REVIEW_SCORE, MEMBER_ID, SHOP_ID, CAKE_NO, CAKE_NAME\r\n"
				+ "FROM (SELECT REVIEW.REVIEW_NO, REVIEW.REVIEW_CONTENT, REVIEW.REVIEW_DATE, REVIEW.REVIEW_SCORE, REVIEW.MEMBER_ID, REVIEW.SHOP_ID, REVIEW.CAKE_NO, CAKE_INFO.CAKE_NAME FROM REVIEW LEFT OUTER JOIN CAKE_INFO ON REVIEW.CAKE_NO = CAKE_INFO.CAKE_NO) WHERE SHOP_ID = ?) ORDER BY NUM DESC";

		// 현재 페이지 : 1, 한 페이지당 보여줄 게시물의 갯수 : 10, 5
		// start : 1, end : 10
		// start : 1, end : 5
		// 현재 페이지 : 2
		// start : 11, end : 20
		// start : 6, end : 10
		int recordCountPerPage = 5;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
//				pstmt.setInt(2, start);
//				pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			rList = new ArrayList<Review>();
			while (rset.next()) {
				Review review = new Review();
				review.setNumbering(rset.getInt("NUM"));
				review.setReviewNo(rset.getInt("REVIEW_NO"));
				review.setReviewContent(rset.getString("REVIEW_CONTENT"));
				review.setReviewDate(rset.getDate("REVIEW_DATE"));
				review.setReviewScore(rset.getInt("REVIEW_SCORE"));
				review.setMemberId(rset.getString("MEMBER_ID"));
				review.setCakeNo(rset.getInt("CAKE_NO"));
				// review.setShopId(rset.getString("SHOP_ID"));
				rList.add(review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rList; // null이면 무조건 실패 떠요
	}
}
